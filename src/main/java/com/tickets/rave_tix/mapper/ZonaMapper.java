//package com.tickets.rave_tix.mapper;
//
//import com.tickets.rave_tix.domain.Evento;
//import com.tickets.rave_tix.domain.Zona;
//import com.tickets.rave_tix.model.ZonaModel;
//import lombok.Builder;
//import lombok.Data;
//
//import java.util.List;
//import java.util.stream.Collectors;
//@Data
//@Builder
//public class ZonaMapper {
//
//    public static ZonaModel toModel(Zona zona) {
//        return ZonaModel.builder()
//                .id(zona.getId())
//                .eventoId(zona.getEvento().getId())
//                .nombre(zona.getNombre())
//                .capacidad(zona.getCapacidad())
//                .precioBase(zona.getPrecioBase())
//                .build();
//    }
//
//    public static Zona toEntity(ZonaModel model) {
//        return Zona.builder()
//                .id(model.getId())
//                .evento(Evento.builder().id(model.getEventoId()).build())
//                .nombre(model.getNombre())
//                .capacidad(model.getCapacidad())
//                .precioBase(model.getPrecioBase())
//                .build();
//    }
//
//    public static List<ZonaModel> toModelList(List<Zona> zonas) {
//        return zonas.stream().map(ZonaMapper::toModel).collect(Collectors.toList());
//    }
//
//    public static List<Zona> toEntityList(List<ZonaModel> models) {
//        return models.stream().map(ZonaMapper::toEntity).collect(Collectors.toList());
//    }
//}
