package com.tickets.rave_tix.model;

import com.tickets.rave_tix.domain.enums.EstadoPago;
import com.tickets.rave_tix.domain.enums.MetodoPago;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    private MetodoPago metodoPago;
    private EstadoPago estado;
    private LocalDate fechaPago;
    private BigDecimal monto;
}
