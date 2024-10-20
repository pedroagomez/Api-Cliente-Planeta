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

    private Double precioTotal;

    public ReservaDTO(Long id, Long clienteId, Long viajeId, Date fechaReserva, Integer cantidadPasajeros, Double precioTotal) {
        this.id = id;
        this.clienteId = clienteId;
        this.viajeId = viajeId;
        this.fechaReserva = fechaReserva;
        this.cantidadPasajeros = cantidadPasajeros;
        this.precioTotal = precioTotal;
    }

    public ReservaDTO() {
    }
}