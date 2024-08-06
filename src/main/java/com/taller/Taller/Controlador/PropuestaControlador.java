package com.taller.Taller.Controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PropuestaControlador {
    @GetMapping("/vistaPropuesta")
    public String VistaPropuesta(){
        return "vistaPropuesta.html";
    }
}
