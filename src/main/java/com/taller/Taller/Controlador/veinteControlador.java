package com.taller.Taller.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class veinteControlador {
    @GetMapping("/vista20")
    public String Vista20(){
        return "vista20.html";
    }
}
