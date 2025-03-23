package com.tickets.rave_tix.mapper;

import com.tickets.rave_tix.domain.Evento;
import com.tickets.rave_tix.domain.Ticket;
import com.tickets.rave_tix.domain.Usuario;
import com.tickets.rave_tix.domain.enums.EstadoTicket;
import com.tickets.rave_tix.domain.enums.TipoTicket;
import com.tickets.rave_tix.model.TicketModel;
import java.util.List;
import java.util.stream.Collectors;

public class TicketMapper {

    public static TicketModel toModel(Ticket ticket) {
        return TicketModel.builder()
                .id(ticket.getId())
                .eventoId(ticket.getEvento().getId())
                .usuarioId(ticket.getUsuario().getId())
                .tipo(ticket.getTipo().name()) // Convertir Enum a String
                .precio(ticket.getPrecio())
                .qrCode(ticket.getQrCode())
                .estado(ticket.getEstado().name()) // Convertir Enum a String
                .build();
    }

    public static Ticket toEntity(TicketModel model) {
        return Ticket.builder()
                .id(model.getId())
                .evento(Evento.builder().id(model.getEventoId()).build())
                .usuario(Usuario.builder().id(model.getUsuarioId()).build())
                .tipo(TipoTicket.valueOf(model.getTipo())) // Convertir String a Enum
                .precio(model.getPrecio())
                .qrCode(model.getQrCode())
                .estado(EstadoTicket.valueOf(model.getEstado())) // Convertir String a Enum
                .build();
    }

    public static List<TicketModel> toModelList(List<Ticket> tickets) {
        return tickets.stream().map(TicketMapper::toModel).collect(Collectors.toList());
    }

    public static List<Ticket> toEntityList(List<TicketModel> models) {
        return models.stream().map(TicketMapper::toEntity).collect(Collectors.toList());
    }
}
