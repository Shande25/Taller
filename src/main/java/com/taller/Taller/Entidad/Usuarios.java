    package com.taller.Taller.Entidad;

    import jakarta.persistence.Column;
    import jakarta.persistence.Entity;
    import jakarta.persistence.GeneratedValue;
    import jakarta.persistence.GenerationType;
    import jakarta.persistence.Id;
    import lombok.Data;
    import lombok.Generated;
    @Data
    @Entity
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

        @Generated
        public Usuarios() {
        }

        @Generated
        public Long getId() {
            return this.id;
        }

        @Generated
        public String getEmail() {
            return this.email;
        }

        @Generated
        public String getPassword() {
            return this.password;
        }

        @Generated
        public String getNombre() {
            return this.nombre;
        }

        @Generated
        public String getFechaNacimiento() {
            return this.fechaNacimiento;
        }

        @Generated
        public String getTelefono() {
            return this.telefono;
        }

        @Generated
        public void setId(final Long id) {
            this.id = id;
        }

        @Generated
        public void setEmail(final String email) {
            this.email = email;
        }

        @Generated
        public void setPassword(final String password) {
            this.password = password;
        }

        @Generated
        public void setNombre(final String nombre) {
            this.nombre = nombre;
        }

        @Generated
        public void setFechaNacimiento(final String fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
        }

        @Generated
        public void setTelefono(final String telefono) {
            this.telefono = telefono;
        }

        @Generated
        public boolean equals(final Object o) {
            if (o == this) {
                return true;
            } else if (!(o instanceof Usuarios)) {
                return false;
            } else {
                Usuarios other = (Usuarios)o;
                if (!other.canEqual(this)) {
                    return false;
                } else {
                    Object this$id = this.getId();
                    Object other$id = other.getId();
                    if (this$id == null) {
                        if (other$id != null) {
                            return false;
                        }
                    } else if (!this$id.equals(other$id)) {
                        return false;
                    }

                    Object this$email = this.getEmail();
                    Object other$email = other.getEmail();
                    if (this$email == null) {
                        if (other$email != null) {
                            return false;
                        }
                    } else if (!this$email.equals(other$email)) {
                        return false;
                    }

                    Object this$password = this.getPassword();
                    Object other$password = other.getPassword();
                    if (this$password == null) {
                        if (other$password != null) {
                            return false;
                        }
                    } else if (!this$password.equals(other$password)) {
                        return false;
                    }

                    label62: {
                        Object this$nombre = this.getNombre();
                        Object other$nombre = other.getNombre();
                        if (this$nombre == null) {
                            if (other$nombre == null) {
                                break label62;
                            }
                        } else if (this$nombre.equals(other$nombre)) {
                            break label62;
                        }

                        return false;
                    }

                    label55: {
                        Object this$fechaNacimiento = this.getFechaNacimiento();
                        Object other$fechaNacimiento = other.getFechaNacimiento();
                        if (this$fechaNacimiento == null) {
                            if (other$fechaNacimiento == null) {
                                break label55;
                            }
                        } else if (this$fechaNacimiento.equals(other$fechaNacimiento)) {
                            break label55;
                        }

                        return false;
                    }

                    Object this$telefono = this.getTelefono();
                    Object other$telefono = other.getTelefono();
                    if (this$telefono == null) {
                        if (other$telefono != null) {
                            return false;
                        }
                    } else if (!this$telefono.equals(other$telefono)) {
                        return false;
                    }

                    return true;
                }
            }
        }

        @Generated
        protected boolean canEqual(final Object other) {
            return other instanceof Usuarios;
        }

        @Generated
        public int hashCode() {
            boolean PRIME = true;
            int result = 1;
            Object $id = this.getId();
            result = result * 59 + ($id == null ? 43 : $id.hashCode());
            Object $email = this.getEmail();
            result = result * 59 + ($email == null ? 43 : $email.hashCode());
            Object $password = this.getPassword();
            result = result * 59 + ($password == null ? 43 : $password.hashCode());
            Object $nombre = this.getNombre();
            result = result * 59 + ($nombre == null ? 43 : $nombre.hashCode());
            Object $fechaNacimiento = this.getFechaNacimiento();
            result = result * 59 + ($fechaNacimiento == null ? 43 : $fechaNacimiento.hashCode());
            Object $telefono = this.getTelefono();
            result = result * 59 + ($telefono == null ? 43 : $telefono.hashCode());
            return result;
        }

        @Generated
        public String toString() {
            Long var10000 = this.getId();
            return "Usuarios(id=" + var10000 + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ", nombre=" + this.getNombre() + ", fechaNacimiento=" + this.getFechaNacimiento() + ", telefono=" + this.getTelefono() + ")";
        }
    }