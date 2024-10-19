package com.planeta.Planeta.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "viaje_id")
    private Viaje viaje;

    private Date fechaReserva;

    private Integer cantidadPasajeros;

    public Reserva(Long id, Cliente cliente, Viaje viaje, Date fechaReserva, Integer cantidadPasajeros) {
        this.id = id;
        this.cliente = cliente;
        this.viaje = viaje;
        this.fechaReserva = fechaReserva;
        this.cantidadPasajeros = cantidadPasajeros;
    }

    public Reserva() {
    }
}
