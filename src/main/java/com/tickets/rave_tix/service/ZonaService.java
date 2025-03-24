package com.tickets.rave_tix.service;

import com.tickets.rave_tix.model.ZonaModel;

import java.util.List;
import java.util.UUID;

public interface ZonaService {

    ZonaModel crearZona(UUID eventoId, ZonaModel zonaModel);
    ZonaModel obtenerZonaPorId(UUID id);
    List<ZonaModel> listarZonas();
    ZonaModel actualizarZona(UUID id, ZonaModel zonaModel);
    void eliminarZona(UUID id);
}
