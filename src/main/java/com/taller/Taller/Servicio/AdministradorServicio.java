package com.taller.Taller.Servicio;

import com.taller.Taller.Entidad.Administrador;
import com.taller.Taller.Repositorio.AdministradorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorServicio {
    private final AdministradorRepositorio solicitudRepositorio;

    @Autowired
    public AdministradorServicio(AdministradorRepositorio solicitudRepositorio) {
        this.solicitudRepositorio = solicitudRepositorio;
    }

    public Administrador guardarSolicitud(Administrador solicitud) {
        return solicitudRepositorio.save(solicitud);
    }
}
