package com.taller.Taller.Servicio;

import com.taller.Taller.Entidad.Factura;
import com.taller.Taller.Repositorio.FacturaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturaServicio {

    @Autowired
    private FacturaRepositorio facturaRepositorio;

    public Factura save(Factura factura) {
        return facturaRepositorio.save(factura);
    }
}