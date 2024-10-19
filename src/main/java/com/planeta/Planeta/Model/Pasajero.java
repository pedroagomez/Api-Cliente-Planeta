package com.planeta.Planeta.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Pasajero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String mail;

    @ManyToOne
    @JoinColumn(name = "viaje_id")
    private Viaje viaje;

    public Pasajero(Long id, String nombre, String apellido, String mail, Viaje viaje) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.viaje = viaje;
    }

    public Pasajero() {
    }


}
