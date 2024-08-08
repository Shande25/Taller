package com.taller.Taller.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdministrarSuscripcionControlador {

    @GetMapping("/administrarSuscripciones")
    public String showManageSubscriptionsPage(Model model) {
        // Aquí puedes agregar datos al modelo si es necesario, por ejemplo:
        // model.addAttribute("algunaData", valor);

        // Devuelve el nombre del archivo HTML en src/main/resources/templates
        return "suscripcionForm"; // Asegúrate de que este archivo exista en templates
    }
}
