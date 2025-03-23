package com.tickets.rave_tix.domain;

import com.tickets.rave_tix.domain.enums.EstadoEvento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "eventos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Evento {

    @Id
    @GeneratedValue
    private UUID id;

    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

    private String ubicacion;

    private Integer capacidadMaxima;

    @Enumerated(EnumType.STRING)
    private EstadoEvento estado;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Zona> zonas;

}
