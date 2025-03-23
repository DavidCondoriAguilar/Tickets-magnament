package com.tickets.rave_tix.mapper;

import com.tickets.rave_tix.domain.Usuario;
import com.tickets.rave_tix.model.UsuarioModel;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static UsuarioModel toModel(Usuario usuario) {
        return UsuarioModel.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .correo(usuario.getCorreo())
                .telefono(usuario.getTelefono())
                .build();
    }

    public static Usuario toEntity(UsuarioModel model) {
        return Usuario.builder()
                .id(model.getId())
                .nombre(model.getNombre())
                .correo(model.getCorreo())
                .telefono(model.getTelefono())
                .build();
    }

    public static List<UsuarioModel> toModelList(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioMapper::toModel).collect(Collectors.toList());
    }

    public static List<Usuario> toEntityList(List<UsuarioModel> models) {
        return models.stream().map(UsuarioMapper::toEntity).collect(Collectors.toList());
    }
}
