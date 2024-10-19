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
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planeta_id")
    private Planeta destino;

    private LocalDate fechaViaje;

    @OneToMany(mappedBy = "viaje", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pasajero> pasajeros = new ArrayList<>();

    public Viaje(Long id, Cliente cliente, Planeta destino, LocalDate fecha, List<Pasajero> pasajeros) {
        this.id = id;
        this.cliente = cliente;
        this.destino = destino;
        this.fechaViaje = fecha;
        this.pasajeros = pasajeros;
    }

    public Viaje() {
    }


}
