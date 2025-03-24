package com.tickets.rave_tix.service.ServiceImpl;

import com.tickets.rave_tix.domain.Evento;
import com.tickets.rave_tix.domain.Zona;
import com.tickets.rave_tix.model.EventoModel;
import com.tickets.rave_tix.model.ZonaModel;
import com.tickets.rave_tix.repository.EventoRepository;
import com.tickets.rave_tix.service.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventoServiceImpl implements EventoService {

    private final EventoRepository eventoRepository;

    @Override
    public EventoModel crearEvento(EventoModel eventoModel) {
        // 1. Crear y guardar el evento sin zonas
        Evento evento = Evento.builder()
                .nombre(eventoModel.getNombre())
                .descripcion(eventoModel.getDescripcion())
                .fechaInicio(eventoModel.getFechaInicio())
                .fechaFin(eventoModel.getFechaFin())
                .ubicacion(eventoModel.getUbicacion())
                .capacidadMaxima(eventoModel.getCapacidadMaxima())
                .estado(eventoModel.getEstado())
                .zonas(Collections.emptyList()) // Inicializamos con lista vacía
                .build();

        evento = eventoRepository.save(evento); // ✅ Guardar antes de asignar zonas

        // 2. Si hay zonas, las creamos con el evento asignado
        if (eventoModel.getZonas() != null && !eventoModel.getZonas().isEmpty()) {
            final Evento eventoFinal = evento; // ✅ Hacemos una variable final
            List<Zona> zonas = eventoModel.getZonas().stream()
                    .map(zonaModel -> Zona.builder()
                            .nombre(zonaModel.getNombre())
                            .capacidad(zonaModel.getCapacidad())
                            .precioBase(zonaModel.getPrecioBase())
                            .beneficios(zonaModel.getBeneficios())
                            .evento(eventoFinal) // ✅ Ahora sí se puede usar en la lambda
                            .build())
                    .collect(Collectors.toList());
            evento.setZonas(zonas);
            evento = eventoRepository.save(evento); // ✅ Guardamos con las zonas
        }

        return convertirAEventoModel(evento);
    }



    @Override
    public EventoModel obtenerEventoPorId(UUID id) {
        return eventoRepository.findById(id)
                .map(this::convertirAEventoModel)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
    }

    @Override
    public List<EventoModel> listarEventos() {
        List<Evento> eventos = eventoRepository.findAll();
        return eventos.stream()
                .map(this::convertirAEventoModel)
                .collect(Collectors.toList());
    }

    @Override
    public EventoModel actualizarEvento(UUID id, EventoModel eventoModel) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        evento.setNombre(eventoModel.getNombre());
        evento.setDescripcion(eventoModel.getDescripcion());
        evento.setFechaInicio(eventoModel.getFechaInicio());
        evento.setFechaFin(eventoModel.getFechaFin());
        evento.setUbicacion(eventoModel.getUbicacion());
        evento.setCapacidadMaxima(eventoModel.getCapacidadMaxima());
        evento.setEstado(eventoModel.getEstado());

        evento = eventoRepository.save(evento);
        return convertirAEventoModel(evento);
    }

    @Override
    public void eliminarEvento(UUID id) {
        if (!eventoRepository.existsById(id)) {
            throw new RuntimeException("Evento no encontrado");
        }
        eventoRepository.deleteById(id);
    }

    private EventoModel convertirAEventoModel(Evento evento) {
        return EventoModel.builder()
                .id(evento.getId())
                .nombre(evento.getNombre())
                .descripcion(evento.getDescripcion())
                .fechaInicio(evento.getFechaInicio())
                .fechaFin(evento.getFechaFin())
                .ubicacion(evento.getUbicacion())
                .capacidadMaxima(evento.getCapacidadMaxima())
                .estado(evento.getEstado())
                .zonas(evento.getZonas() != null && !evento.getZonas().isEmpty()
                        ? evento.getZonas().stream()
                        .map(zona -> ZonaModel.builder()
                                .id(zona.getId())
                                .nombre(zona.getNombre())
                                .capacidad(zona.getCapacidad())
                                .precioBase(zona.getPrecioBase())
                                .beneficios(zona.getBeneficios())
                                .build())
                        .collect(Collectors.toList())
                        : Collections.emptyList()) // Retorna [] en lugar de null
                .build();
    }
}
