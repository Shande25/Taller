package com.taller.Taller.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AmorControlador {
    @GetMapping("/vistaAmor")
    public String VistaAmor(){
        return "vistaAmor.html";
    }
}
