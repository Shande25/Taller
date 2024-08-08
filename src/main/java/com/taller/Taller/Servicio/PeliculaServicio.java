package com.taller.Taller.Servicio;

import com.taller.Taller.Entidad.Peliculas;
import com.taller.Taller.Repositorio.PeliculaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaServicio {

    @Autowired
    private PeliculaRepositorio peliculaRepositorio;

    public Peliculas obtenerPeliculaPorId(Long id) {
        return peliculaRepositorio.findById(id).orElse(null);
    }

    public void eliminarPelicula(Long id) {
        peliculaRepositorio.deleteById(id);
    }

    public void guardarPelicula(Peliculas pelicula) {
        peliculaRepositorio.save(pelicula);
    }

    public List<Peliculas> obtenerTodasLasPeliculas() {
        return peliculaRepositorio.findAll();
    }

    public List<Peliculas> buscarPeliculas(String query) {
        if (query == null || query.isEmpty()) {
            return obtenerTodasLasPeliculas();
        }
        return peliculaRepositorio.findByNombreContainingIgnoreCase(query);
    }
}
