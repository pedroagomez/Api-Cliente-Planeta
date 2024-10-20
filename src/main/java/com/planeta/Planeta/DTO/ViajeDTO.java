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

    public ViajeDTO() {
    }
}
