package com.taller.Taller.Servicio;

import com.taller.Taller.Entidad.Suscripcion;
import com.taller.Taller.Repositorio.SuscripcionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuscripcionServicio {

    @Autowired
    private SuscripcionRepositorio suscripcionRepositorio;

    public List<Suscripcion> obtenerTodasSuscripciones() {
        return suscripcionRepositorio.findAll();
    }

    public Suscripcion obtenerSuscripcionPorId(Long id) {
        return suscripcionRepositorio.findById(id).orElse(null);
    }

    public void eliminarSuscripcion(Long id) {
        suscripcionRepositorio.deleteById(id);
    }

    public List<Suscripcion> buscarSuscripciones(String buscarSuscripcion) {
        return suscripcionRepositorio.findByTipoContainingIgnoreCase(buscarSuscripcion);
    }

}
