package com.taller.Taller.Repositorio;

import com.taller.Taller.Entidad.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuarios, Long> {
    @Query("SELECT u FROM Usuarios u WHERE u.email = :email")
    Optional<Usuarios> buscarUsuarioByEmail(@Param("email") String email);
}
