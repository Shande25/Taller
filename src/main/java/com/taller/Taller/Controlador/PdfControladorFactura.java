package com.taller.Taller.Controlador;

import com.taller.Taller.Entidad.Usuarios;
import com.taller.Taller.Repositorio.UsuarioRepositorio;
import com.taller.Taller.Servicio.PdfServicio;
import com.taller.Taller.Servicio.PdfServicioFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class PdfControladorFactura {
    @Autowired
    private PdfServicioFactura pdfServicioFactura;
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/factura/pdf")
    public ResponseEntity<byte[]> descargarPdf(Principal principal) throws Exception {
        // Obtener el usuario actual
        String username = principal.getName();
        Usuarios usuario = usuarioRepositorio.buscarUsuarioByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        byte[] pdf = pdfServicioFactura.generarPdf(usuario.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "factura.pdf");
        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }
}
