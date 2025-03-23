package com.tickets.rave_tix.repository;

import com.tickets.rave_tix.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    List<Ticket> findByUsuarioId(UUID usuarioId);

    List<Ticket> findByEventoId(UUID eventoId);
}
