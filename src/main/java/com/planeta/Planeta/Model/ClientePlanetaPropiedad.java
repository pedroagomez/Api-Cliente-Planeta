package com.planeta.Planeta.Model;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class ClientePlanetaPropiedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "planeta_id")
    private Planeta planeta;

    private Double kilometrosCuadrados;

    public ClientePlanetaPropiedad(Long id, Cliente cliente, Planeta planeta, Double kilometrosCuadrados) {
        this.id = id;
        this.cliente = cliente;
        this.planeta = planeta;
        this.kilometrosCuadrados = kilometrosCuadrados;
    }


    public ClientePlanetaPropiedad() {
    }
}
