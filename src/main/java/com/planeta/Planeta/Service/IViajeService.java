package com.planeta.Planeta.Service;

import com.planeta.Planeta.DTO.ViajeDTO;
import com.planeta.Planeta.Model.Viaje;

import java.util.List;

public interface IViajeService {
    void crearViaje(Viaje viaje);
    ViajeDTO obtenerViajePorId(Long id);
    List<ViajeDTO> obtenerTodosLosViajes();
    void actualizarViaje(Long id, Viaje viajeDTO);
    void eliminarViaje(Long id);
}
