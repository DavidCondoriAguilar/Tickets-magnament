package com.tickets.rave_tix.domain;
import com.tickets.rave_tix.domain.enums.EstadoTicket;
import com.tickets.rave_tix.domain.enums.TipoTicket;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tickets")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Ticket {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private TipoTicket tipo;

    private BigDecimal precio;

    private String qrCode;

    @Enumerated(EnumType.STRING)
    private EstadoTicket estado;

}
