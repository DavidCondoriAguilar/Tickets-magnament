package com.tickets.rave_tix.service;

import com.tickets.rave_tix.model.EventoModel;
import java.util.List;
import java.util.UUID;

public interface EventoService {
    EventoModel crearEvento(EventoModel evento);
    EventoModel obtenerEventoPorId(UUID id);
    List<EventoModel> listarEventos();
    EventoModel actualizarEvento(UUID id, EventoModel evento);
    void eliminarEvento(UUID id);
}
