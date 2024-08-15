package com.taller.Taller.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlancaControlador {
    @GetMapping("/vistaBlanca")
    public String VistaBlanca(){
        return "vistaBlanca";
    }

}
