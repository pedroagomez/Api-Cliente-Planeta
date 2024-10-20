package com.planeta.Planeta.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "viaje_id")
    private Viaje viaje;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pasajero_principal_id")
    private Pasajero pasajeroPrincipal;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pasajero> acompanantes = new ArrayList<>();

    private LocalDate fechaReserva;

    private Integer cantidadPasajeros;

    private Double precioTotal;

    public Reserva(Long id, Viaje viaje, Pasajero pasajeroPrincipal, List<Pasajero> acompanantes, LocalDate fechaReserva, Integer cantidadPasajeros, Double precioTotal) {
        this.id = id;
        this.viaje = viaje;
        this.pasajeroPrincipal = pasajeroPrincipal;
        this.acompanantes = acompanantes;
        this.fechaReserva = fechaReserva;
        this.cantidadPasajeros = cantidadPasajeros;
        this.precioTotal = precioTotal;
    }


    public Reserva() {
    }
}
