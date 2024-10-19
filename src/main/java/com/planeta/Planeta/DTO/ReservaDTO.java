package com.planeta.Planeta.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReservaDTO {
    private Long id;
    private Long clienteId; // Solo necesitas el ID del cliente
    private Long viajeId;   // Solo necesitas el ID del viaje
    private Date fechaReserva;
    private Integer cantidadPasajeros;

    public ReservaDTO(Long id, Long clienteId, Long viajeId, Date fechaReserva, Integer cantidadPasajeros) {
        this.id = id;
        this.clienteId = clienteId;
        this.viajeId = viajeId;
        this.fechaReserva = fechaReserva;
        this.cantidadPasajeros = cantidadPasajeros;
    }

    public ReservaDTO() {
    }
}