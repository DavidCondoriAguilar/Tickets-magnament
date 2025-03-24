package com.tickets.rave_tix.model;

import lombok.*;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class HistorialEventoModel {
    private UUID id;
    private UUID usuarioId;
    private UUID eventoId;
    private boolean asistenciaConfirmada;
    private int calificacion;
    private String comentario;
    private EventoModel evento;
}
