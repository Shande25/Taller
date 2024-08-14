package com.taller.Taller.Servicio;

import com.taller.Taller.Entidad.Usuarios;
import com.taller.Taller.Exceptions.MiException;
import com.taller.Taller.Repositorio.UsuarioRepositorio;
import com.taller.Taller.Roles.Rol;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void registrar(String nombre, String email, String password) {
        // Codificar la contraseña
        String encodedPassword = passwordEncoder.encode(password);

        // Crear un nuevo usuario
        Usuarios usuario = new Usuarios();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(encodedPassword);

        // Guardar el usuario en la base de datos
        usuarioRepositorio.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuarios> optionalUsuario = usuarioRepositorio.buscarUsuarioByEmail(email);
        if (optionalUsuario.isPresent()) {
            Usuarios usuario = optionalUsuario.get();
            List<GrantedAuthority> permisos = new ArrayList<>();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
            permisos.add(p);
            return new User(usuario.getEmail(), usuario.getPassword(), permisos);
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado con email: " + email);
        }
    }

    public List<Usuarios> listaUsuarios() {
        return this.usuarioRepositorio.findAll();
    }

    public Optional<Usuarios> buscarUsuarioId(Long id) {
        return this.usuarioRepositorio.findById(id);
    }

    public void guardarUsuario(Usuarios usuario) {
        this.usuarioRepositorio.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        this.usuarioRepositorio.deleteById(id);
    }

    public Optional<Usuarios> buscarPorEmail(String email) {
        return usuarioRepositorio.buscarUsuarioByEmail(email);
    }
}
