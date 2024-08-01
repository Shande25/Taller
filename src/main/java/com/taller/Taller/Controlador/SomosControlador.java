package com.taller.Taller.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SomosControlador {
    @GetMapping("/quienesSomos")
    public String QuienesSomos(){
        return "quienesSomos";
    }

}
