package com.taller.Taller.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlitaControlador {
    @GetMapping("/vistaAlita")
    public String VistaAlita(){
        return "vistaAlita.html";
    }
}
