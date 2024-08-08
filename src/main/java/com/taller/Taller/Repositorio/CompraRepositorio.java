package com.taller.Taller.Repositorio;

import com.taller.Taller.Entidad.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepositorio extends JpaRepository<Compra,Long> {
}
