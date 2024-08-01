package com.taller.Taller.Controlador;

import com.taller.Taller.Entidad.VUser;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioControlador {

    @GetMapping("/registro")
    public String formularioRegistro(Model model) {
        model.addAttribute("usuario", new VUser()); // Cambiado a VUser
        return "register"; // Cambiado a "register" para coincidir con la plantilla
    }

    @PostMapping("/registrar")
    public String registrar(@Valid @ModelAttribute("usuario") VUser vuser, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "register"; // Asegúrate de que esta vista esté en /src/main/resources/templates
        } else {
            return "index"; // Asegúrate de que esta vista esté en /src/main/resources/templates
        }
    }
}
