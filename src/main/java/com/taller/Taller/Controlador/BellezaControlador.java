package com.taller.Taller.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BellezaControlador {
    @GetMapping("/vistaBelleza")
    public String VistaBelleza(){
        return "vistaBelleza.html";
    }
}
