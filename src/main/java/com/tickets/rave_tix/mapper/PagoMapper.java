package com.tickets.rave_tix.mapper;

import com.tickets.rave_tix.domain.Pago;
import com.tickets.rave_tix.domain.enums.EstadoPago;
import com.tickets.rave_tix.domain.enums.MetodoPago;
import com.tickets.rave_tix.model.PagoModel;

import java.util.List;
import java.util.stream.Collectors;

public class PagoMapper {

    public static PagoModel toModel(Pago pago) {
        return PagoModel.builder()
                .id(pago.getId())
                .usuarioId(pago.getUsuario().getId())
                .montoTotal(pago.getMontoTotal())
                .metodoPago(pago.getMetodoPago().name())
                .estado(pago.getEstado().name())
                .fechaPago(pago.getFechaPago())
                .build();
    }

    public static Pago toEntity(PagoModel model) {
        return Pago.builder()
                .id(model.getId())
                .montoTotal(model.getMontoTotal())
                .metodoPago(MetodoPago.valueOf(model.getMetodoPago()))
                .estado(EstadoPago.valueOf(model.getEstado()))
                .fechaPago(model.getFechaPago())
                .build();
    }

    public static List<PagoModel> toModelList(List<Pago> pagos) {
        return pagos.stream().map(PagoMapper::toModel).collect(Collectors.toList());
    }

    public static List<Pago> toEntityList(List<PagoModel> models) {
        return models.stream().map(PagoMapper::toEntity).collect(Collectors.toList());
    }
}
