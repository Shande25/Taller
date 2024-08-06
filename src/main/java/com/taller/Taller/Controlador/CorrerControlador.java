package com.taller.Taller.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CorrerControlador {
    @GetMapping("/vistaCorrer")
    public String VistaCorrer(){
        return "vistaCorrer.html";
    }
}
