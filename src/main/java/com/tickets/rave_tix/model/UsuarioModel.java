package com.tickets.rave_tix.model;

import lombok.*;

import java.util.UUID;
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UsuarioModel {
    private UUID id;
    private String nombre;
    private String correo;
    private String telefono;
}

