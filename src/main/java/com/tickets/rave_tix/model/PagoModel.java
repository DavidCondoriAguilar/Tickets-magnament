package com.tickets.rave_tix.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PagoModel {
    private UUID id;
    private UUID usuarioId;
    private BigDecimal montoTotal;
    private String metodoPago;
    private String estado;
    private LocalDateTime fechaPago;
}