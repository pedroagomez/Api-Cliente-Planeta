package com.planeta.Planeta.DTO;

import com.planeta.Planeta.Model.Planeta;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ViajeDTO {

    private Long id;
<<<<<<< HEAD
    private Long planetaId;
    private String planetaNombre;
    private LocalDate fechaSalida;
    private PlanetaDTO destino;
    private Integer asientosDisponibles;
    private Double precioPorPasajero;
    private List<PasajeroDTO> pasajeros;

    public ViajeDTO(Long id, Long planetaId, String planetaNombre, LocalDate fechaSalida, PlanetaDTO destino, Integer asientosDisponibles, Double precioPorPasajero, List<PasajeroDTO> pasajeros) {
        this.id = id;
        this.planetaId = planetaId;
        this.planetaNombre = planetaNombre;
        this.fechaSalida = fechaSalida;
        this.destino = destino;
        this.asientosDisponibles = asientosDisponibles;
        this.precioPorPasajero = precioPorPasajero;
        this.pasajeros = pasajeros;
    }
=======
    private LocalDate fechaSalida;
    private PlanetaDTO destino;
    private Integer asientosDisponibles;
    private Integer capacidadTotal;
    private Double precioPorPasajero;
    private List<ReservaDTO> reservasDto;

    public ViajeDTO(Long id, LocalDate fechaSalida, PlanetaDTO destino, Integer asientosDisponibles, Integer capacidadTotal, Double precioPorPasajero, List<ReservaDTO> reservasDto) {
        this.id = id;
        this.fechaSalida = fechaSalida;
        this.destino = destino;
        this.asientosDisponibles = asientosDisponibles;
        this.capacidadTotal = capacidadTotal;
        this.precioPorPasajero = precioPorPasajero;
        this.reservasDto = reservasDto;
    }

    public ViajeDTO()
    {
>>>>>>> main

    public ViajeDTO() {
    }
}
