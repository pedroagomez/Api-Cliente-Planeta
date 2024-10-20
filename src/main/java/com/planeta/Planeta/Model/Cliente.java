package com.planeta.Planeta.Model;


import com.planeta.Planeta.DTO.ReservaDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String apellido;
    private String mail;
    private String password;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClientePlanetaPropiedad> propiedades = new ArrayList<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reserva> reservas = new ArrayList<>();

    // Constructor completo
    public Cliente(Long id, String nombre, String apellido, String mail, String password, List<ClientePlanetaPropiedad> propiedades, List<Reserva> reservas) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.password = password;
        this.propiedades = propiedades;
        this.reservas = reservas;
    }

    // Constructor vacío
    public Cliente() {}
}

