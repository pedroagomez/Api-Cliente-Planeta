package com.planeta.Planeta.Service;

import com.planeta.Planeta.DTO.PasajeroDTO;
import com.planeta.Planeta.DTO.PlanetaDTO;
<<<<<<< HEAD
=======
import com.planeta.Planeta.DTO.ReservaDTO;
>>>>>>> main
import com.planeta.Planeta.DTO.ViajeDTO;
import com.planeta.Planeta.Model.*;
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
<<<<<<< HEAD
    @Autowired
    private IPlanetaRepository planetaRepository;
=======
>>>>>>> main

    @Override
    public void crearViaje(Viaje viaje) {
        viajeRepository.save(viaje);
    }

    @Override
<<<<<<< HEAD
    public ViajeDTO obtenerViajePorId(Long id) {
        Viaje viaje = viajeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaje no encontrado con id: " + id));
        return mapearViajeADTO(viaje);
    }

    @Override
    public List<ViajeDTO> obtenerTodosLosViajes() {
        List<Viaje> viajes = viajeRepository.findAll();
        return viajes.stream()
=======
    public Viaje obtenerViajePorId(Long id) {
        return viajeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Id no encontrado"));
    }

    private PlanetaDTO mapearPlaneta(Planeta planeta) {
        return new PlanetaDTO(
                planeta.getId(),
                planeta.getNombre(),
                planeta.getTipo(),
                planeta.getKmCuadrados()
        );
    }

    @Override
    public List<ViajeDTO> obtenerTodosLosViajes() {
        List<Viaje> viajes = viajeRepository.findAll();

        return viajes.stream()
                .filter(viaje -> viaje.getFechaViaje() != null &&
                        viaje.getCapacidadTotal() != null &&
                        viaje.getAsientosDisponibles() != null &&
                        viaje.getPrecioPorPasajero() != null) // Filtra viajes válidos
>>>>>>> main
                .map(this::mapearViajeADTO)
                .collect(Collectors.toList());
    }

<<<<<<< HEAD
    @Override
    public void actualizarViaje(Long id, ViajeDTO viajeDTO) {
        Viaje viajeExistente = viajeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaje no encontrado con id: " + id));

        actualizarViajeDesdeDTO(viajeExistente, viajeDTO);
        viajeRepository.save(viajeExistente);
=======
    private ViajeDTO mapearViajeADTO(Viaje viaje) {
        ViajeDTO dto = new ViajeDTO();
        dto.setId(viaje.getId());
        dto.setFechaSalida(viaje.getFechaViaje());

        // Asegúrate de que el destino no sea nulo antes de mapear
        if (viaje.getDestino() != null) {
            dto.setDestino(mapearPlaneta(viaje.getDestino()));
        } else {
            dto.setDestino(null);
        }

        // Asegúrate de que estos campos se asignen correctamente
        dto.setAsientosDisponibles(viaje.getAsientosDisponibles());
        dto.setCapacidadTotal(viaje.getCapacidadTotal());
        dto.setPrecioPorPasajero(viaje.getPrecioPorPasajero());

        // Mapeo de reservas sin llamar a mapearViajeADTO para evitar recursión
        if (viaje.getReservas() != null) {
            dto.setReservasDto(viaje.getReservas().stream()
                    .map(this::mapearReservaADTOSinViaje)
                    .collect(Collectors.toList()));
        } else {
            dto.setReservasDto(new ArrayList<>()); // Inicializa como una lista vacía si es nulo
        }

        return dto;
    }

    // Método auxiliar para mapear una Reserva a ReservaDTO sin llamar a mapearViajeADTO
    private ReservaDTO mapearReservaADTOSinViaje(Reserva reserva) {
        ReservaDTO dto = new ReservaDTO();
        dto.setId(reserva.getId());
        dto.setClienteId(reserva.getCliente().getId());
        dto.setFechaReserva(reserva.getFechaReserva());

        // Mapeo de pasajeros
        dto.setPasajeros(reserva.getPasajeros().stream()
                .map(pasajero -> mapearPasajeroADTO(pasajero, reserva.getId())) // Pasamos el reservaId
                .collect(Collectors.toList()));
        dto.setPrecioTotal(reserva.getPrecioTotal());

        return dto;
    }

    // Método auxiliar para mapear un Pasajero a PasajeroDTO
    private PasajeroDTO mapearPasajeroADTO(Pasajero pasajero, Long reservaId) {
        PasajeroDTO dto = new PasajeroDTO();
        dto.setId(pasajero.getId());
        dto.setNombre(pasajero.getNombre());
        dto.setApellido(pasajero.getApellido());
        dto.setEmail(pasajero.getEmail());
        dto.setReservaId(reservaId);

        return dto;
    }

    @Override
    public void actualizarViaje(Viaje viaje) {
        // Verifica que el viaje existe antes de actualizar
        if (!viajeRepository.existsById(viaje.getId())) {
            throw new EntityNotFoundException("No se encontró el viaje con ID: " + viaje.getId());
        }
        viajeRepository.save(viaje); // Guarda la entidad actualizada
>>>>>>> main
    }

    @Override
    public void eliminarViaje(Long id) {
        viajeRepository.deleteById(id);
    }
<<<<<<< HEAD

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
=======
}
>>>>>>> main
