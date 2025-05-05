package com.perfumeria.perfumeria.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    private String nombreCompleto;
    private String correo;
    private String contraseña;
    private String telefono;
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "idRol")
    private Rol rol;

    private Boolean estado = true;
}
