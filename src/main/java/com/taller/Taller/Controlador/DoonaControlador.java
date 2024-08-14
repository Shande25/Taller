package com.taller.Taller.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DoonaControlador {
    @GetMapping("/vistaDoona")
    public String VistaDoona(){
        return "vistaDoona";
    }
}