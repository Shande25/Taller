package com.taller.Taller.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class homeControlador {
    @GetMapping("/home")
    public String Home(){
        return "home.html";
    }
}
