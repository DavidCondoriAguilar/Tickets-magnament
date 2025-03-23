package com.tickets.rave_tix.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ZonaModel {
    private UUID id;
    private UUID eventoId;
    private String nombre;
    private Integer capacidad;
    private BigDecimal precioBase;
}
