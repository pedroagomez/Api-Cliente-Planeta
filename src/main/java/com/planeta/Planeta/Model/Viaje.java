package com.planeta.Planeta.Model;


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
    @JoinColumn(name = "planeta_id")
    private Planeta destino;

    private LocalDate fechaViaje;

    private Integer capacidadTotal;
    private Integer asientosDisponibles;
    private Double precioPorPasajero;

    @OneToMany(mappedBy = "viaje")
    private List<Reserva> reservas = new ArrayList<>();


    public Viaje(Long id, Planeta destino, LocalDate fechaViaje, Integer capacidadTotal, Integer asientosDisponibles, Double precioPorPasajero, List<Reserva> reservas) {
        this.id = id;
        this.destino = destino;
        this.fechaViaje = fechaViaje;
        this.capacidadTotal = capacidadTotal;
        this.asientosDisponibles = asientosDisponibles;
        this.precioPorPasajero = precioPorPasajero;
        this.reservas = reservas;
    }

    public Viaje() {
    }


}
