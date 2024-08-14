package com.taller.Taller.Repositorio;

import com.taller.Taller.Entidad.Factura;
import com.taller.Taller.Entidad.Suscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacturaRepositorio extends JpaRepository<Factura,Long> {
    List<Factura> findByUsuarioId(Long usuarioId);
}
