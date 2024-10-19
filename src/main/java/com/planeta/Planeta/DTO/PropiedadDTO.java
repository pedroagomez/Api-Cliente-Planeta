package com.planeta.Planeta.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PropiedadDTO {
    private Long id; // ID de la propiedad
    private Long planetaId; // ID del planeta
    private Double kilometrosCuadrados;

    public PropiedadDTO(Long id, Long planetaId, Double kilometrosCuadrados) {
        this.id = id;
        this.planetaId = planetaId;
        this.kilometrosCuadrados = kilometrosCuadrados;
    }
    public PropiedadDTO() {
    }
}
