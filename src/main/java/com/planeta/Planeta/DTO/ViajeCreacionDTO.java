package com.planeta.Planeta.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
public class ViajeCreacionDTO{
    private Long clienteId;
    private Long planetaId;
    private LocalDate fechaViaje;
    private List<PasajeroDTO> pasajeros;

    public ViajeCreacionDTO(Long clienteId, Long planetaId, LocalDate fechaViaje, List<PasajeroDTO> pasajeros) {
        this.clienteId = clienteId;
        this.planetaId = planetaId;
        this.fechaViaje = fechaViaje;
        this.pasajeros = pasajeros;
    }

    public ViajeCreacionDTO() {
    }
}