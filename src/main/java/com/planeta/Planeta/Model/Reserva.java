package com.planeta.Planeta.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

<<<<<<< HEAD
    @ManyToOne
=======
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @NotNull(message = "El cliente no puede ser nulo")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER)
>>>>>>> main
    @JoinColumn(name = "viaje_id")
    @NotNull(message = "El viaje no puede ser nulo")
    private Viaje viaje;

<<<<<<< HEAD
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pasajero_principal_id")
    private Pasajero pasajeroPrincipal;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pasajero> acompanantes = new ArrayList<>();

    private LocalDate fechaReserva;
=======
    @NotNull(message = "La fecha de reserva es obligatoria")
>>>>>>> main

    private LocalDate fechaReserva;
    ;

<<<<<<< HEAD
    private Double precioTotal;

    public Reserva(Long id, Viaje viaje, Pasajero pasajeroPrincipal, List<Pasajero> acompanantes, LocalDate fechaReserva, Integer cantidadPasajeros, Double precioTotal) {
=======
    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pasajero> pasajeros = new ArrayList<>();

    @NotNull(message = "El campo es obligatorio")
    @Min(value = 1, message = "El precio tiene que ser superior a 1 ")
    private Double precioTotal;

    public Reserva(Long id, Cliente cliente, Viaje viaje, LocalDate fechaReserva, List<Pasajero> pasajeros, Double precioTotal) {
>>>>>>> main
        this.id = id;
        this.viaje = viaje;
        this.pasajeroPrincipal = pasajeroPrincipal;
        this.acompanantes = acompanantes;
        this.fechaReserva = fechaReserva;
<<<<<<< HEAD
        this.cantidadPasajeros = cantidadPasajeros;
=======
        this.pasajeros = pasajeros;
>>>>>>> main
        this.precioTotal = precioTotal;
    }


    public Reserva() {
    }
}
