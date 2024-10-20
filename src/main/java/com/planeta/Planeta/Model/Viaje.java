package com.planeta.Planeta.Model;


import com.planeta.Planeta.DTO.PlanetaDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planeta_id")
    private Planeta destino;

    private LocalDate fechaViaje;
    private Integer asientosDisponibles;
    private Double precioPorPasajero;

    @OneToMany(mappedBy = "viaje", orphanRemoval = true)
    private List<Pasajero> pasajeros = new ArrayList<>();

    public Viaje(Long id, Cliente cliente, Planeta destino, LocalDate fechaViaje, Integer asientosDisponibles, Double precioPorPasajero, List<Pasajero> pasajeros) {
        this.id = id;
        this.cliente = cliente;
        this.destino = destino;
        this.fechaViaje = fechaViaje;
        this.asientosDisponibles = asientosDisponibles;
        this.precioPorPasajero = precioPorPasajero;
        this.pasajeros = pasajeros;
    }

    public Viaje() {
    }


}
