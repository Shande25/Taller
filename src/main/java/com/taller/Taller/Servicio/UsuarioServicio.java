package com.taller.Taller.Servicio;

import com.taller.Taller.Entidad.Usuarios;
import com.taller.Taller.Repositorio.ClienteRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public UsuarioServicio() {
    }

    public List<Usuarios> listaUsuarios() {
        return this.clienteRepositorio.findAll();
    }

    public Optional<Usuarios> buscarUsuarioId(Long id) {
        return this.clienteRepositorio.findById(id);
    }

    public void guardarUsuario(Usuarios usuario) {
        this.clienteRepositorio.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        this.clienteRepositorio.deleteById(id);
    }
    public Optional<Usuarios> buscarPorEmail(String email) {
        return clienteRepositorio.findByEmail(email);
    }
}