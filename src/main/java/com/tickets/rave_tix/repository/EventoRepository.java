package com.tickets.rave_tix.repository;

import com.tickets.rave_tix.domain.Evento;
import com.tickets.rave_tix.domain.enums.EstadoEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventoRepository extends JpaRepository<Evento, UUID> {

    List<Evento> findByEstado(EstadoEvento estado);

    List<Evento> findByNombreContainingIgnoreCase(String nombre);
}
