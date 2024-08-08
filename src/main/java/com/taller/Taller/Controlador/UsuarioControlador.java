package com.taller.Taller.Controlador;

import com.taller.Taller.Entidad.Usuarios;
import com.taller.Taller.Servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UsuarioControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;

    public UsuarioControlador() {
    }

    @GetMapping({"/registro"})
    public String mostrarFormularioDeRegistro(Model model) {
        model.addAttribute("usuario", new Usuarios());
        return "registroUsuario";
    }

    @PostMapping({"/registrar"})
    public String registrar(@Validated @ModelAttribute("usuario") Usuarios usuario, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registroUsuario";
        } else {
            this.usuarioServicio.guardarUsuario(usuario);
            return "index";
        }
    }

    @GetMapping({"/iniciarsesion"})
    public String mostrarFormularioDeInicioSesion(Model model) {
        model.addAttribute("usuario", new Usuarios());
        return "inicioseccion";
    }

    @PostMapping({"/login"})
    public String iniciarSesion(@Validated @ModelAttribute("usuario") Usuarios usuario, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "inicioseccion";
        }

        Optional<Usuarios> usuarioExistente = usuarioServicio.buscarPorEmail(usuario.getEmail());

        if (usuarioExistente.isEmpty() || !usuarioExistente.get().getPassword().equals(usuario.getPassword())) {
            model.addAttribute("error", "Email o contraseña incorrectos");
            return "inicioseccion";
        }

        return "index";
    }
}