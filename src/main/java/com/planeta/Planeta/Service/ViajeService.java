package com.planeta.Planeta.Service;

import com.planeta.Planeta.DTO.PasajeroDTO;
import com.planeta.Planeta.DTO.PlanetaDTO;
import com.planeta.Planeta.DTO.ViajeDTO;
import com.planeta.Planeta.Model.Cliente;
import com.planeta.Planeta.Model.Pasajero;
import com.planeta.Planeta.Model.Planeta;
import com.planeta.Planeta.Model.Viaje;
import com.planeta.Planeta.Repository.IClienteRepository;
import com.planeta.Planeta.Repository.IPasajeroRepository;
import com.planeta.Planeta.Repository.IPlanetaRepository;
import com.planeta.Planeta.Repository.IViajeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViajeService implements IViajeService {

    @Autowired
    private IViajeRepository viajeRepository;
    @Autowired
    private IPlanetaRepository planetaRepository;

    @Override
    public void crearViaje(Viaje viaje) {
        viajeRepository.save(viaje);
    }

    @Override
    public ViajeDTO obtenerViajePorId(Long id) {
        Viaje viaje = viajeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaje no encontrado con id: " + id));
        return mapearViajeADTO(viaje);
    }

    @Override
    public List<ViajeDTO> obtenerTodosLosViajes() {
        List<Viaje> viajes = viajeRepository.findAll();
        return viajes.stream()
                .map(this::mapearViajeADTO)
                .collect(Collectors.toList());
    }

    @Override
    public void actualizarViaje(Long id, ViajeDTO viajeDTO) {
        Viaje viajeExistente = viajeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaje no encontrado con id: " + id));

        actualizarViajeDesdeDTO(viajeExistente, viajeDTO);
        viajeRepository.save(viajeExistente);
    }

    @Override
    public void eliminarViaje(Long id) {
        if(!viajeRepository.existsById(id)) {
            throw new EntityNotFoundException("Viaje no encontrado con id: " + id);
        }
        viajeRepository.deleteById(id);
    }

    private ViajeDTO mapearViajeADTO(Viaje viaje) {
        ViajeDTO viajeDTO = new ViajeDTO();
        viajeDTO.setId(viaje.getId());

        if (viaje.getDestino() != null) {
            viajeDTO.setPlanetaId(viaje.getDestino().getId());
            viajeDTO.setPlanetaNombre(viaje.getDestino().getNombre());

            // Crear PlanetaDTO
            PlanetaDTO planetaDTO = new PlanetaDTO(
                    viaje.getDestino().getId(),
                    viaje.getDestino().getNombre(),
                    viaje.getDestino().getTipo(),
                    viaje.getDestino().getKmCuadrados()
            );
            viajeDTO.setDestino(planetaDTO);
        }

        viajeDTO.setFechaSalida(viaje.getFechaViaje());
        viajeDTO.setAsientosDisponibles(viaje.getAsientosDisponibles());
        viajeDTO.setPrecioPorPasajero(viaje.getPrecioPorPasajero());

        // Si necesitas mapear los pasajeros, puedes hacerlo aquí
        // viajeDTO.setPasajeros(mapearPasajeros(viaje.getPasajeros()));

        return viajeDTO;
    }

    private Viaje mapearDTOAViaje(ViajeDTO viajeDTO) {
        Viaje viaje = new Viaje();
        viaje.setId(viajeDTO.getId());
        Planeta destino = planetaRepository.findById(viajeDTO.getPlanetaId())
                .orElseThrow(() -> new EntityNotFoundException("Planeta no encontrado con id: " + viajeDTO.getPlanetaId()));
        viaje.setDestino(destino);
        viaje.setFechaViaje(viajeDTO.getFechaSalida());
        viaje.setAsientosDisponibles(viajeDTO.getAsientosDisponibles());
        viaje.setPrecioPorPasajero(viajeDTO.getPrecioPorPasajero());

        return viaje;
    }

    private void actualizarViajeDesdeDTO(Viaje viaje, ViajeDTO viajeDTO) {
        if (viajeDTO.getPlanetaId() != null) {
            Planeta destino = planetaRepository.findById(viajeDTO.getPlanetaId())
                    .orElseThrow(() -> new EntityNotFoundException("Planeta no encontrado con id: " + viajeDTO.getPlanetaId()));
            viaje.setDestino(destino);
        }
        if (viajeDTO.getFechaSalida() != null) {
            viaje.setFechaViaje(viajeDTO.getFechaSalida());
        }
        if (viajeDTO.getAsientosDisponibles() != null) {
            viaje.setAsientosDisponibles(viajeDTO.getAsientosDisponibles());
        }
        if (viajeDTO.getPrecioPorPasajero() != null) {
            viaje.setPrecioPorPasajero(viajeDTO.getPrecioPorPasajero());
        }

    }
}