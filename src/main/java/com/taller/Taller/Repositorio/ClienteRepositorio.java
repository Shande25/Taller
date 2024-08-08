package com.taller.Taller.Repositorio;

import com.taller.Taller.Entidad.Usuarios;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Usuarios, Long> {
    Optional<Usuarios> findByEmail(String email);
}