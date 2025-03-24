package com.tickets.rave_tix.controller;

import com.tickets.rave_tix.model.ZonaModel;
import com.tickets.rave_tix.service.ZonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/zonas")
@RequiredArgsConstructor
public class ZonaController {

    private final ZonaService zonaService;

    @PostMapping("/evento/{eventoId}")
    public ResponseEntity<ZonaModel> crearZona(@PathVariable UUID eventoId, @RequestBody ZonaModel zonaModel) {
        return ResponseEntity.ok(zonaService.crearZona(eventoId, zonaModel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZonaModel> obtenerZonaPorId(@PathVariable UUID id) {
        ZonaModel zona = zonaService.obtenerZonaPorId(id);
        return zona != null ? ResponseEntity.ok(zona) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<ZonaModel>> listarZonas() {
        return ResponseEntity.ok(zonaService.listarZonas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ZonaModel> actualizarZona(@PathVariable UUID id, @RequestBody ZonaModel zonaModel) {
        return ResponseEntity.ok(zonaService.actualizarZona(id, zonaModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarZona(@PathVariable UUID id) {
        zonaService.eliminarZona(id);
        return ResponseEntity.noContent().build();
    }
}