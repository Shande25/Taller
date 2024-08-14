package com.taller.Taller.Repositorio;

import com.taller.Taller.Entidad.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepositorio extends JpaRepository <Administrador, Long> {
}
