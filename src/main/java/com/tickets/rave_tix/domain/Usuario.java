package com.tickets.rave_tix.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue
    private UUID id;

    private String nombre;

    @Column(unique = true, nullable = false)
    private String correo;

    private String telefono;

}
