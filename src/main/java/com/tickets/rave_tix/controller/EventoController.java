package com.tickets.rave_tix.controller;

import com.tickets.rave_tix.model.EventoModel;
import com.tickets.rave_tix.service.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventoService;

    @PostMapping
    public ResponseEntity<EventoModel> crearEvento(@RequestBody EventoModel evento) {
        return ResponseEntity.ok(eventoService.crearEvento(evento));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoModel> obtenerEvento(@PathVariable UUID id) {
        return ResponseEntity.ok(eventoService.obtenerEventoPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<EventoModel>> listarEventos() {
        return ResponseEntity.ok(eventoService.listarEventos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoModel> actualizarEvento(@PathVariable UUID id, @RequestBody EventoModel evento) {
        return ResponseEntity.ok(eventoService.actualizarEvento(id, evento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEvento(@PathVariable UUID id) {
        eventoService.eliminarEvento(id);
        return ResponseEntity.noContent().build();
    }
}
