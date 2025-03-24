package com.tickets.rave_tix.domain;

import com.tickets.rave_tix.domain.enums.EstadoEvento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "eventos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evento {

    @Id
    @GeneratedValue
    private UUID id;

    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;

    private String ubicacion;

    private Integer capacidadMaxima;

    @Enumerated(EnumType.STRING)
    private EstadoEvento estado;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Zona> zonas = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "evento_usuario",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> usuarios = new ArrayList<>();

//    public void setZonas(List<Zona> nuevasZonas) {
//        if (nuevasZonas == null) {
//            this.zonas.clear();
//            return;
//        }
//        this.zonas.forEach(zona -> zona.setEvento(null)); // Desasocia las antiguas
//        this.zonas.clear();
//        nuevasZonas.forEach(zona -> zona.setEvento(this)); // Asocia las nuevas
//        this.zonas.addAll(nuevasZonas);
//    }
}
