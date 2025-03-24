package com.tickets.rave_tix.service.ServiceImpl;

import com.tickets.rave_tix.domain.*;
import com.tickets.rave_tix.domain.enums.EstadoPago;
import com.tickets.rave_tix.exception.EventoNoEncontradoException;
import com.tickets.rave_tix.exception.UsuarioNoEncontradoException;
import com.tickets.rave_tix.exception.ZonaNoEncontradaException;
import com.tickets.rave_tix.model.*;
import com.tickets.rave_tix.repository.EventoRepository;
import com.tickets.rave_tix.repository.PagoRepository;
import com.tickets.rave_tix.repository.UsuarioRepository;
import com.tickets.rave_tix.repository.ZonaRepository;
import com.tickets.rave_tix.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ZonaRepository zonaRepository;

    @Autowired
    private PagoRepository pagoRepository;

    @Override
    public UsuarioModel crearUsuario(UsuarioModel usuarioModel) {
        // Convertir UsuarioModel a Usuario
        Usuario usuario = Usuario.builder()
                .nombre(usuarioModel.getNombre())
                .correo(usuarioModel.getCorreo())
                .telefono(usuarioModel.getTelefono())
                .fechaRegistro(LocalDate.now())
                .build();

        // Guardar usuario antes de usarlo en las lambdas
        usuario = usuarioRepository.save(usuario);
        final Usuario finalUsuario = usuario; // Variable final para las lambdas

        // Instanciar y asociar Tickets
        List<Ticket> tickets = usuarioModel.getTickets().stream().map(ticketModel -> {
            Evento evento = eventoRepository.findById(ticketModel.getEvento().getId())
                    .orElseThrow(() -> new EventoNoEncontradoException("Evento no encontrado"));
            Zona zona = zonaRepository.findById(ticketModel.getZona().getId())
                    .orElseThrow(() -> new ZonaNoEncontradaException("Zona no encontrada"));
            return Ticket.builder()
                    .id(UUID.randomUUID())
                    .usuario(finalUsuario)
                    .evento(evento)
                    .zona(zona)
                    .precio(ticketModel.getPrecio())
                    .fechaCompra(String.valueOf(LocalDateTime.now()))
                    .build();
        }).collect(Collectors.toList());
        usuario.setTickets(tickets);

        // Instanciar y asociar Pagos
        List<Pago> pagos = usuarioModel.getPagos().stream().map(pagoModel ->
                Pago.builder()
                        .id(UUID.randomUUID())
                        .usuario(finalUsuario)
                        .montoTotal(pagoModel.getMonto())
                        .metodoPago(pagoModel.getMetodoPago())
                        .estado(EstadoPago.COMPLETADO)
                        .fechaPago(LocalDateTime.now())
                        .build()
        ).collect(Collectors.toList());
        usuario.setPagos(pagos);
        pagoRepository.saveAll(pagos);

        // Instanciar y asociar HistorialEventos
        List<HistorialEvento> historialEventos = usuarioModel.getHistorialEventos().stream().map(historialModel -> {
            Evento evento = eventoRepository.findById(historialModel.getEvento().getId())
                    .orElseThrow(() -> new EventoNoEncontradoException("Evento no encontrado"));
            return HistorialEvento.builder()
                    .id(UUID.randomUUID())
                    .usuario(finalUsuario)
                    .evento(evento)
                    .asistenciaConfirmada(historialModel.isAsistenciaConfirmada())
                    .calificacion(historialModel.getCalificacion())
                    .comentario(historialModel.getComentario())
                    .build();
        }).collect(Collectors.toList());
        usuario.setHistorialEventos(historialEventos);

        // Guardar usuario con relaciones
        usuario = usuarioRepository.save(usuario);

        // Convertir Usuario a UsuarioModel con datos anidados y retornar
        return convertirAUsuarioModel(usuario);
    }


    @Override
    public UsuarioModel obtenerUsuarioPorId(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));
        return convertirAUsuarioModel(usuario);
    }

    @Override
    public List<UsuarioModel> listarUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::convertirAUsuarioModel)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioModel actualizarUsuario(UUID id, UsuarioModel usuario) {
        return null;
    }

    @Override
    public void eliminarUsuario(UUID id) {

    }

    private UsuarioModel convertirAUsuarioModel(Usuario usuario) {
        return UsuarioModel.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .correo(usuario.getCorreo())
                .telefono(usuario.getTelefono())
                .fechaRegistro(String.valueOf(usuario.getFechaRegistro()))
                .tickets(usuario.getTickets().stream().map(ticket -> TicketModel.builder()
                        .id(ticket.getId())
                        .evento(EventoModel.builder()
                                .id(ticket.getEvento().getId())
                                .nombre(ticket.getEvento().getNombre())
                                .descripcion(ticket.getEvento().getDescripcion())
                                .fechaInicio(ticket.getEvento().getFechaInicio())
                                .fechaFin(ticket.getEvento().getFechaFin())
                                .ubicacion(ticket.getEvento().getUbicacion())
                                .build())
                        .zona(ZonaModel.builder()
                                .id(ticket.getZona().getId())
                                .nombre(ticket.getZona().getNombre())
                                .capacidad(ticket.getZona().getCapacidad())
                                .precioBase(ticket.getZona().getPrecioBase())
                                .beneficios(ticket.getZona().getBeneficios())
                                .build())
                        .precio(ticket.getPrecio())
                        .fechaCompra(ticket.getFechaCompra())
                        .build()).collect(Collectors.toList()))
                .pagos(usuario.getPagos().stream().map(pago -> PagoModel.builder()
                        .id(pago.getId())
                        .monto(pago.getMontoTotal())
                        .metodoPago(pago.getMetodoPago())
                        .estado(pago.getEstado())
                        .fechaPago(LocalDate.from(pago.getFechaPago()))
                        .build()).collect(Collectors.toList()))
                .historialEventos(usuario.getHistorialEventos().stream().map(historial -> HistorialEventoModel.builder()
                        .evento(EventoModel.builder()
                                .id(historial.getEvento().getId())
                                .nombre(historial.getEvento().getNombre())
                                .fechaInicio(historial.getEvento().getFechaInicio())
                                .fechaFin(historial.getEvento().getFechaFin())
                                .ubicacion(historial.getEvento().getUbicacion())
                                .build())
                        .asistenciaConfirmada(historial.isAsistenciaConfirmada())
                        .calificacion(historial.getCalificacion())
                        .comentario(historial.getComentario())
                        .build()).collect(Collectors.toList()))
                .build();
    }
}
