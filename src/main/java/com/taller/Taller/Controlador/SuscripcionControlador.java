package com.taller.Taller.Controlador;

import com.taller.Taller.Entidad.Factura;
import com.taller.Taller.Entidad.Suscripcion;
import com.taller.Taller.Entidad.Usuarios;
import com.taller.Taller.Repositorio.FacturaRepositorio;
import com.taller.Taller.Repositorio.SuscripcionRepositorio;
import com.taller.Taller.Repositorio.UsuarioRepositorio;
import com.taller.Taller.Roles.Rol;
import com.taller.Taller.Servicio.SuscripcionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class SuscripcionControlador {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private SuscripcionRepositorio suscripcionRepositorio;

    @Autowired
    private SuscripcionServicio suscripcionServicio;

    @Autowired
    private FacturaRepositorio facturaRepositorio;

    // Mostrar la página de suscripciones con filtro
    @GetMapping("/suscripciones")
    public String showSubscriptionPage(Model model, Principal principal) {
        // Obtener la lista de suscripciones
        List<Suscripcion> suscripciones = suscripcionRepositorio.findAll();
        model.addAttribute("suscripciones", suscripciones);

        // Obtener el usuario actual
        String username = principal.getName();
        Usuarios usuario = usuarioRepositorio.buscarUsuarioByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Enviar el rol del usuario al modelo
        model.addAttribute("nombreUsuario", usuario.getNombre());
        model.addAttribute("rolUsuario", usuario.getRol().toString());
        model.addAttribute("isAdmin", usuario.getRol() == Rol.ADMIN);

        return "suscripciones";
    }

    // Mostrar la página de administración de suscripciones con filtro
    @GetMapping("/admin/suscripciones")
    public String listarSuscripciones(Model model, @RequestParam(value = "buscarSuscripcion", required = false) String buscarSuscripcion) {
        List<Suscripcion> suscripciones;
        if (buscarSuscripcion != null && !buscarSuscripcion.isEmpty()) {
            try {
                Long id = Long.parseLong(buscarSuscripcion);
                Suscripcion suscripcion = suscripcionServicio.obtenerSuscripcionPorId(id);
                suscripciones = suscripcion != null ? List.of(suscripcion) : List.of();
            } catch (NumberFormatException e) {
                // Si no es un número, buscar por tipo
                suscripciones = suscripcionServicio.buscarSuscripciones(buscarSuscripcion);
            }
        } else {
            suscripciones = suscripcionServicio.obtenerTodasSuscripciones();
        }
        model.addAttribute("suscripciones", suscripciones);
        model.addAttribute("buscarSuscripcion", buscarSuscripcion);
        return "suscripcionForm"; // Asegúrate de que este es el nombre correcto de la vista
    }

    // Mostrar el formulario de asignación de suscripción
    @GetMapping("/suscripcion")
    public String showSubscriptionForm(Model model, Principal principal) {
        // Obtener el usuario actual
        String username = principal.getName();
        Usuarios usuario = usuarioRepositorio.buscarUsuarioByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        model.addAttribute("usuario", usuario);

        // Obtener el rol del usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .findFirst().orElse("USER");

        model.addAttribute("role", role);
        model.addAttribute("nombreUsuario", usuario.getNombre());
        model.addAttribute("rolUsuario", usuario.getRol().toString());
        model.addAttribute("isAdmin", usuario.getRol() == Rol.ADMIN);

        return "suscripciones"; // Nombre del archivo HTML del formulario
    }

    // Procesar la asignación de una nueva suscripción
    @PostMapping("/procesarAsignacion")
    public String procesarAsignacionFactura(
            @RequestParam("tipo") String tipo,
            @RequestParam("precio") Double precio,
            @RequestParam("duracion") String duracion,
            Principal principal) {

        try {
            // Obtener el usuario actual
            String username = principal.getName();
            Usuarios usuario = usuarioRepositorio.buscarUsuarioByEmail(username)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

            // Crear una nueva suscripción
            Suscripcion suscripcion = new Suscripcion();
            suscripcion.setTipo(tipo);
            suscripcion.setPrecio(precio);
            suscripcion.setDuracion(duracion);
            suscripcion.setUsuario(usuario);

            // Guardar la suscripción en la base de datos
            Suscripcion nuevaSuscripcion = suscripcionRepositorio.save(suscripcion);

            // Crear y guardar la factura
            Factura factura = new Factura();
            factura.setSuscripcion(nuevaSuscripcion);
            factura.setTipo(tipo);
            factura.setPrecio(precio);
            factura.setDuracion(duracion);
            factura.setUsuario(usuario);

            facturaRepositorio.save(factura);

            // Redirigir a la página de agradecimiento
            return"/agradecimiento";
        } catch (Exception e) {
            // Manejar el error, por ejemplo, registrar el error y mostrar un mensaje al usuario
            e.printStackTrace();
            return "error"; // Redirigir a una página de error si es necesario
        }
    }


    // Mostrar la página de edición de suscripción
    @GetMapping("/editar/suscripcion/{id}")
    public String editarSuscripcion(@PathVariable Long id, Model model) {
        Suscripcion suscripcion = suscripcionRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Suscripción no encontrada"));

        List<Usuarios> usuarios = usuarioRepositorio.findAll();
        model.addAttribute("suscripcion", suscripcion);
        model.addAttribute("usuarios", usuarios);

        return "editarSuscripcion"; // Nombre del archivo HTML para editar suscripción
    }

    // Procesar la actualización de una suscripción
    @PostMapping("/actualizar/suscripcion")
    public String actualizarSuscripcion(
            @RequestParam("id") Long id,
            @RequestParam("tipo") String tipo,
            @RequestParam("precio") Double precio,
            @RequestParam("duracion") String duracion,
            @RequestParam("usuario") Long usuarioId) {

        // Buscar la suscripción por ID
        Suscripcion suscripcion = suscripcionRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Suscripción no encontrada"));

        // Buscar el usuario por ID
        Usuarios usuario = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Actualizar la suscripción
        suscripcion.setTipo(tipo);
        suscripcion.setPrecio(precio);
        suscripcion.setDuracion(duracion);
        suscripcion.setUsuario(usuario);

        suscripcionRepositorio.save(suscripcion);

        // Redirigir a la página de administración de suscripciones
        return "redirect:/suscripciones";
    }

    // Eliminar una suscripción
    @GetMapping("/eliminar/suscripcion/{id}")
    public String eliminarSuscripcion(@PathVariable Long id) {
        suscripcionRepositorio.deleteById(id);
        return "redirect:/suscripciones";
    }
}
