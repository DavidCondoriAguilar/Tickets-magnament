package com.tickets.rave_tix.model;

import com.tickets.rave_tix.domain.enums.EstadoEvento;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoModel {
    private UUID id;
    private String nombre;
    private String descripcion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private String ubicacion;
    private Integer capacidadMaxima;
    private EstadoEvento estado;
    private List<ZonaModel> zonas;

}