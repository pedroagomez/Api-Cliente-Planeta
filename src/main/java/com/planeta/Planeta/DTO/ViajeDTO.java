package com.planeta.Planeta.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ViajeDTO {

    private Long id;
    private Long cliente_id;
    private Long planeta_id;
    private List<PasajeroDTO>listaPasajero;

    public ViajeDTO(Long id, Long cliente_id, Long planeta_id, List<PasajeroDTO> listaPasajero) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.planeta_id = planeta_id;
        this.listaPasajero = listaPasajero;
    }
    public ViajeDTO()
    {

    }
}
