    package com.taller.Taller.Entidad;

    import com.taller.Taller.Roles.Rol;
    import jakarta.persistence.*;
    import lombok.Data;
    import lombok.Generated;
    @Data
    @Entity
    @Table(name = "usuarios")
    public class Usuarios {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true, nullable = false)
        private String email;

        @Column(nullable = false)
        private String password;

        @Column(nullable = false)
        private String nombre;

        @Column(nullable = false)
        private String fechaNacimiento;

        @Column(nullable = false)
        private String telefono;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private Rol rol;
    }