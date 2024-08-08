package com.taller.Taller.Controlador;

import com.taller.Taller.Entidad.Suscripcion;
import com.taller.Taller.Entidad.Usuarios;
import com.taller.Taller.Repositorio.SuscripcionRepositorio;
import com.taller.Taller.Repositorio.UsuarioRepositorio;
import com.taller.Taller.Servicio.SuscripcionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SuscripcionControlador {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private SuscripcionRepositorio suscripcionRepositorio;

    @Autowired
    private SuscripcionServicio suscripcionServicio;

    // Mostrar la página de suscripciones con filtro
    @GetMapping("/suscripciones")
    public String showSubscriptionPage(Model model) {
        List<Suscripcion> suscripciones = suscripcionRepositorio.findAll();
        model.addAttribute("suscripciones", suscripciones);
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
        return "suscripcionForm";
    }

    // Mostrar el formulario de asignación de suscripción
    @GetMapping("/suscripcion")
    public String showSubscriptionForm(Model model) {
        List<Usuarios> usuarios = usuarioRepositorio.findAll();
        model.addAttribute("usuarios", usuarios);
        return "suscripciones"; // Nombre del archivo HTML del formulario
    }

    // Procesar la asignación de una nueva suscripción
    @PostMapping("/procesarAsignacion")
    public String procesarAsignacion(
            @RequestParam("tipo") String tipo,
            @RequestParam("precio") Double precio,
            @RequestParam("duracion") String duracion,
            @RequestParam("usuario") Long usuarioId) {

        // Buscar el usuario por ID
        Usuarios usuario = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Crear y guardar la suscripción
        Suscripcion suscripcion = new Suscripcion();
        suscripcion.setTipo(tipo);
        suscripcion.setPrecio(precio);
        suscripcion.setDuracion(duracion);
        suscripcion.setUsuario(usuario);

        suscripcionRepositorio.save(suscripcion);

        // Redirigir a la página de administración de suscripciones
        return "redirect:/suscripciones";
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
