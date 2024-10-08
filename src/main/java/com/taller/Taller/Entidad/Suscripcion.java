package com.taller.Taller.Entidad;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;
@Data
@Entity
@Table(name = "suscripciones")
public class Suscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
    private Double precio;
    private String duracion;

    @OneToMany(mappedBy = "suscripcion")
    private Set<Factura> facturas;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuarios usuario;

    // Getters and setters
    // ...
}