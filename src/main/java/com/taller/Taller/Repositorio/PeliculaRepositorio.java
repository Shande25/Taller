package com.taller.Taller.Repositorio;

import com.taller.Taller.Entidad.Peliculas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PeliculaRepositorio extends JpaRepository<Peliculas, Long> {
    List<Peliculas> findByNombreContainingIgnoreCase(String nombre);
}
