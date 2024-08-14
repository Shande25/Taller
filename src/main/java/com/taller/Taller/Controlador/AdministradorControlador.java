package com.taller.Taller.Controlador;

import com.taller.Taller.Entidad.Administrador;
import com.taller.Taller.Entidad.Usuarios;
import com.taller.Taller.Repositorio.AdministradorRepositorio;
import com.taller.Taller.Roles.Rol;
import com.taller.Taller.Servicio.AdministradorServicio;
import com.taller.Taller.Servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class AdministradorControlador {

    private final AdministradorServicio solicitudServicio;
    private final UsuarioServicio usuarioServicio;
    private final AdministradorRepositorio administradorRepositorio;

    @Autowired
    public AdministradorControlador(AdministradorServicio solicitudServicio, UsuarioServicio usuarioServicio, AdministradorRepositorio administradorRepositorio) {
        this.solicitudServicio = solicitudServicio;
        this.usuarioServicio = usuarioServicio;
        this.administradorRepositorio = administradorRepositorio;
    }

    @GetMapping("/solicitud-administrador")
    public String mostrarFormularioSolicitud() {
        return "formularioAdmin"; // Nombre del archivo HTML del formulario
    }

    @PostMapping("/solicitud-agradecimiento")
    public String procesarSolicitud(@RequestParam String nombre,
                                    @RequestParam String email,
                                    @RequestParam String motivo,
                                    @AuthenticationPrincipal UserDetails userDetails) {
        Administrador solicitud = new Administrador();
        solicitud.setNombre(nombre);
        solicitud.setEmail(email);
        solicitud.setMotivo(motivo);

        // Obtener el usuario autenticado
        Usuarios usuario = usuarioServicio.buscarUsuarioByEmail(userDetails.getUsername())
                .orElse(null); // Puedes manejar el caso donde el usuario no es encontrado

        solicitud.setUsuario(usuario);

        solicitudServicio.guardarSolicitud(solicitud);

        return "solicitud"; // Redirige a una página de agradecimiento o similar
    }

    @GetMapping("/solicitudes")
    public String mostrarSolicitudes(Model model) {
        List<Administrador> solicitudes = administradorRepositorio.findAll(); // Obtén todas las solicitudes
        model.addAttribute("solicitudes", solicitudes);
        return "solicitudes"; // Nombre del archivo Thymeleaf, sin la extensión .html
    }

    @GetMapping("/aprobar-solicitud")
    public String aprobarSolicitud(@RequestParam Long idSolicitud) {
        Optional<Administrador> solicitudOpt = administradorRepositorio.findById(idSolicitud);
        if (solicitudOpt.isPresent()) {
            Administrador solicitud = solicitudOpt.get();
            Usuarios usuario = solicitud.getUsuario();
            if (usuario != null) {
                // Cambia el rol del usuario a ADMIN
                usuario.setRol(Rol.ADMIN); // Asegúrate de que el método setRol existe y Rol.ADMIN es correcto
                usuarioServicio.guardarUsuario(usuario); // Guardar el usuario actualizado
            }
            administradorRepositorio.delete(solicitud); // Opcional: Elimina la solicitud después de aprobarla
        }
        return "redirect:/solicitudes"; // Redirige a la lista de solicitudes
    }
}
