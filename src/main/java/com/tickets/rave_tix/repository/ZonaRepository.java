package com.tickets.rave_tix.repository;

import com.tickets.rave_tix.domain.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ZonaRepository extends JpaRepository<Zona, UUID> {

    List<Zona> findByEventoId(UUID eventoId);
}
