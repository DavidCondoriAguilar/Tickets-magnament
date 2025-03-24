package com.tickets.rave_tix.service;

import com.tickets.rave_tix.model.UsuarioModel;
import java.util.List;
import java.util.UUID;

public interface UsuarioService {
    UsuarioModel crearUsuario(UsuarioModel usuario);
    UsuarioModel obtenerUsuarioPorId(UUID id);
    List<UsuarioModel> listarUsuarios();
    UsuarioModel actualizarUsuario(UUID id, UsuarioModel usuario);
    void eliminarUsuario(UUID id);
}
