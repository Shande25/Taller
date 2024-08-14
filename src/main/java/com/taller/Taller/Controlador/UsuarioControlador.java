package com.taller.Taller.Controlador;

import com.taller.Taller.Entidad.Usuarios;
import com.taller.Taller.Repositorio.UsuarioRepositorio;
import com.taller.Taller.Roles.Rol;
import com.taller.Taller.Servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class UsuarioControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/registro")
    public String mostrarFormularioDeRegistro(Model model) {
        model.addAttribute("usuario", new Usuarios());
        return "registroUsuario"; // Asegúrate de que esta vista exista
    }

    @PostMapping("/registrar")
    public String registrar(@Validated @ModelAttribute("usuario") Usuarios usuario, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registroUsuario"; // Regresa a la vista de registro si hay errores
        }
        try {
            usuarioServicio.registrar(usuario.getNombre(), usuario.getEmail(), usuario.getPassword(), usuario.getFechaNacimiento(), usuario.getTelefono());
            return "redirect:/iniciarsesion"; // Redirige a la página de inicio de sesión
        } catch (Exception e) {
            model.addAttribute("error", "Error al registrar el usuario: " + e.getMessage());
            return "registroUsuario"; // Regresa a la vista de registro en caso de error
        }
    }

    @GetMapping("/iniciarsesion")
    public String mostrarFormularioDeInicioSesion(Model model) {
        model.addAttribute("usuario", new Usuarios());
        return "index"; // Cambia el nombre de la vista según tu estructura
    }

    @GetMapping("/home")
    public String mostrarInicio(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                Optional<Usuarios> usuarioOpt = usuarioServicio.buscarUsuarioByEmail(userDetails.getUsername());
                if (usuarioOpt.isPresent()) {
                    Usuarios usuario = usuarioOpt.get();
                    model.addAttribute("nombreUsuario", usuario.getNombre());
                    model.addAttribute("rolUsuario", usuario.getRol().toString());
                    model.addAttribute("isAdmin", usuario.getRol() == Rol.ADMIN);
                }
            }
        }
        return "home"; // Asegúrate de tener una vista llamada "home"
    }



}
