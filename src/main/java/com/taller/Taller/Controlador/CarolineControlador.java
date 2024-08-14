package com.taller.Taller.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarolineControlador {
    @GetMapping("/vistaCaroline")
    public String VistaCaroline(){
        return "vistaCaroline";
    }
}