package com.taller.Taller.Servicio;

import com.taller.Taller.Entidad.Usuarios;
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

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void registrar(String nombre, String email, String password, String fechaNacimiento, String telefono) {
        String encodedPassword = passwordEncoder.encode(password);

        Usuarios usuario = new Usuarios();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(encodedPassword);
        usuario.setFechaNacimiento(fechaNacimiento);
        usuario.setTelefono(telefono);
        usuario.setRol(Rol.USER);

        usuarioRepositorio.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuarios usuario = usuarioRepositorio.buscarUsuarioByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString()));
        return new User(usuario.getEmail(), usuario.getPassword(), authorities);
    }

    public Optional<Usuarios> buscarUsuarioByEmail(String email) {
        return usuarioRepositorio.buscarUsuarioByEmail(email);
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
}
