package com.taller.Taller.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexControlador {
    @GetMapping("/index")
    public String Index(){
        return "index.html";
    }
}
