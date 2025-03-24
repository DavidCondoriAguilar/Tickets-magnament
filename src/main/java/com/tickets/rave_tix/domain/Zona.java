package com.tickets.rave_tix.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "zonas")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Zona {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    private String nombre;

    private Integer capacidad;

    private BigDecimal precioBase;

    @ElementCollection
    private List<String> beneficios;
}
