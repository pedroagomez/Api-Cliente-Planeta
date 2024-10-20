package com.planeta.Planeta.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasajeroDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    public PasajeroDTO(Long id, String nombre, String apellido, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    public PasajeroDTO() {
    }
}