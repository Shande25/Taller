package com.taller.Taller.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeadControlador {
    @GetMapping("/vistaDeadPool")
    public String VistaDeadPool(){
        return "vistaDeadPool.html";
    }
}
