package com.taller.Taller.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibroControlador {
    @GetMapping("/vistaLibro")
    public String VistaLibro(){
        return "vistaLibro.html";
    }
}
