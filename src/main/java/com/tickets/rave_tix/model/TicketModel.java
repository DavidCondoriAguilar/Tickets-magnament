package com.tickets.rave_tix.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class TicketModel {
    private UUID id;
    private UUID eventoId;
    private UUID usuarioId;
    private String tipo;
    private BigDecimal precio;
    private String qrCode;
    private String estado;
}