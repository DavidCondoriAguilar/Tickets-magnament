package com.tickets.rave_tix.service.ServiceImpl;

import com.tickets.rave_tix.domain.Evento;
import com.tickets.rave_tix.domain.Zona;
import com.tickets.rave_tix.exception.EventoNoEncontradoException;
import com.tickets.rave_tix.exception.ZonaNotFoundException;
import com.tickets.rave_tix.model.ZonaModel;
import com.tickets.rave_tix.repository.EventoRepository;
import com.tickets.rave_tix.repository.ZonaRepository;
import com.tickets.rave_tix.service.ZonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ZonaServiceImpl implements ZonaService {

    private final ZonaRepository zonaRepository;
    private final EventoRepository eventoRepository;

    @Override
    public ZonaModel crearZona(UUID eventoId, ZonaModel zonaModel) {
        if (zonaModel == null || !StringUtils.hasText(zonaModel.getNombre())) {
            throw new IllegalArgumentException("El modelo de zona no puede estar vacío y debe tener un nombre válido.");
        }

        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new EventoNoEncontradoException("Evento no encontrado con ID: " + eventoId));

        Zona zona = Zona.builder()
                .nombre(zonaModel.getNombre().trim())
                .capacidad(zonaModel.getCapacidad() != null && zonaModel.getCapacidad() > 0 ? zonaModel.getCapacidad() : 1)
                .precioBase(zonaModel.getPrecioBase() != null && zonaModel.getPrecioBase().compareTo(BigDecimal.ZERO) > 0 ?
                        zonaModel.getPrecioBase() : BigDecimal.ZERO)
                .beneficios(zonaModel.getBeneficios() != null ? String.join(", ", zonaModel.getBeneficios()) : "")
                .evento(evento)
                .build();

        zona = zonaRepository.save(zona);
        return convertirAZonaModel(zona);
    }

    @Override
    public ZonaModel obtenerZonaPorId(UUID id) {
        return zonaRepository.findById(id)
                .map(this::convertirAZonaModel)
                .orElseThrow(() -> new ZonaNotFoundException("Zona no encontrada con ID: " + id));
    }

    @Override
    public List<ZonaModel> listarZonas() {
        return zonaRepository.findAll().stream()
                .map(this::convertirAZonaModel)
                .collect(Collectors.toList());
    }

    @Override
    public ZonaModel actualizarZona(UUID id, ZonaModel zonaModel) {
        if (zonaModel == null || !StringUtils.hasText(zonaModel.getNombre())) {
            throw new IllegalArgumentException("El modelo de zona no puede estar vacío y debe tener un nombre válido.");
        }

        Zona zona = zonaRepository.findById(id)
                .orElseThrow(() -> new ZonaNotFoundException("Zona no encontrada con ID: " + id));

        zona.setNombre(zonaModel.getNombre().trim());
        zona.setCapacidad(zonaModel.getCapacidad() != null && zonaModel.getCapacidad() > 0 ? zonaModel.getCapacidad() : zona.getCapacidad());
        zona.setPrecioBase(zonaModel.getPrecioBase() != null && zonaModel.getPrecioBase().compareTo(BigDecimal.ZERO) > 0 ?
                zonaModel.getPrecioBase() : zona.getPrecioBase());
        zona.setBeneficios(zonaModel.getBeneficios() != null ? String.join(", ", zonaModel.getBeneficios()) : zona.getBeneficios());

        return convertirAZonaModel(zonaRepository.save(zona));
    }

    @Override
    public void eliminarZona(UUID id) {
        Zona zona = zonaRepository.findById(id)
                .orElseThrow(() -> new ZonaNotFoundException("Zona no encontrada con ID: " + id));

        zonaRepository.delete(zona);
    }

    private ZonaModel convertirAZonaModel(Zona zona) {
        return ZonaModel.builder()
                .id(zona.getId())
                .eventoId(zona.getEvento() != null ? zona.getEvento().getId() : null)
                .nombre(zona.getNombre())
                .capacidad(zona.getCapacidad())
                .precioBase(zona.getPrecioBase())
                .beneficios(zona.getBeneficios() != null ? List.of(zona.getBeneficios().split(", ")) : List.of())
                .build();
    }
}
