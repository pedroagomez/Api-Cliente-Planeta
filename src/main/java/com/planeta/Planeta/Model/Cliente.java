package com.planeta.Planeta.Model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String apellido;
    private String mail;
    private String password;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private List<ClientePlanetaPropiedad> propiedades;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Viaje> viajes = new ArrayList<>();


    public Cliente(Long id, String nombre, String apellido, String mail, String password, List<ClientePlanetaPropiedad> propiedades, List<Viaje> viajes) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.password = password;
        this.propiedades = propiedades;
        this.viajes = viajes;
    }

    public Cliente() {
    }

}
