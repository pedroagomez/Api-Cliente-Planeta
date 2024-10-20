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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "viaje_id")
    private Viaje viaje;

    private LocalDate fechaReserva;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pasajero> pasajeros = new ArrayList<>();

    private Double precioTotal;

    public Reserva(Long id, Cliente cliente, Viaje viaje, LocalDate fechaReserva, List<Pasajero> pasajeros, Double precioTotal) {
        this.id = id;
        this.cliente = cliente;
        this.viaje = viaje;
        this.fechaReserva = fechaReserva;
        this.pasajeros = pasajeros;
        this.precioTotal = precioTotal;
    }

    public Reserva() {
    }
}
