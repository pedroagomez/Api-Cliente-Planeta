package com.planeta.Planeta.Model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Entity
public class Planeta {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String tipo;
    private int kmCuadrados;


    @OneToMany(mappedBy = "planeta")
    private List<ClientePlanetaPropiedad> propiedades;

    public Planeta(Long id, String nombre, String tipo, int kmCuadrados, List<ClientePlanetaPropiedad> propiedades) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.kmCuadrados = kmCuadrados;
        this.propiedades = propiedades;
    }

    public Planeta() {
    }
}
