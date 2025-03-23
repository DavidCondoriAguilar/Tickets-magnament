package com.tickets.rave_tix.repository;

import com.tickets.rave_tix.domain.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PagoRepository extends JpaRepository<Pago, UUID> {

    List<Pago> findByUsuarioId(UUID usuarioId);

    List<Pago> findByEstado(String estado);

}
