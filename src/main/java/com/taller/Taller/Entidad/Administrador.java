package com.taller.Taller.Entidad;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "solicitudes_administrador")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 1000)
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuarios usuario;

}
