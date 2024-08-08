    package com.taller.Taller.Entidad;

    import jakarta.persistence.*;
    import lombok.Data;

    import java.util.Set;

    @Entity
    @Table(name = "peliculas")
    @Data
    public class Peliculas {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nombre;
        private String genero;
        private String imagenUrl;
        private String director;
        private Integer anio;

        @OneToMany(mappedBy = "pelicula")
        private Set<Compra> compras;

    }
