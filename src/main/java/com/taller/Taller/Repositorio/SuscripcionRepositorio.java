package com.taller.Taller.Repositorio;

import com.taller.Taller.Entidad.Suscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SuscripcionRepositorio extends JpaRepository<Suscripcion,Long> {
    List<Suscripcion> findByTipoContainingIgnoreCase(String tipo);
    Optional<Suscripcion> findById(Long id);
}
