package com.tickets.rave_tix.mapper;

import com.tickets.rave_tix.domain.Evento;
import com.tickets.rave_tix.domain.enums.EstadoEvento;
import com.tickets.rave_tix.model.EventoModel;
import java.util.List;
import java.util.stream.Collectors;

public class EventoMapper {

    public static EventoModel toModel(Evento evento) {
        return EventoModel.builder()
                .id(evento.getId())
                .nombre(evento.getNombre())
                .descripcion(evento.getDescripcion())
                .fechaInicio(evento.getFechaInicio())
                .fechaFin(evento.getFechaFin())
                .ubicacion(evento.getUbicacion())
                .capacidadMaxima(evento.getCapacidadMaxima())
                .estado(evento.getEstado().name())
                .build();
    }

    public static Evento toEntity(EventoModel model) {
        return Evento.builder()
                .id(model.getId())
                .nombre(model.getNombre())
                .descripcion(model.getDescripcion())
                .fechaInicio(model.getFechaInicio())
                .fechaFin(model.getFechaFin())
                .ubicacion(model.getUbicacion())
                .capacidadMaxima(model.getCapacidadMaxima())
                .estado(EstadoEvento.valueOf(model.getEstado()))
                .build();
    }

    public static List<EventoModel> toModelList(List<Evento> eventos) {
        return eventos.stream().map(EventoMapper::toModel).collect(Collectors.toList());
    }

    public static List<Evento> toEntityList(List<EventoModel> models) {
        return models.stream().map(EventoMapper::toEntity).collect(Collectors.toList());
    }

}
