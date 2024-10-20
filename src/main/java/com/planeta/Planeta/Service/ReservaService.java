package com.planeta.Planeta.Service;

import com.planeta.Planeta.DTO.PasajeroDTO;
import com.planeta.Planeta.DTO.PlanetaDTO;
import com.planeta.Planeta.DTO.ReservaDTO;
import com.planeta.Planeta.DTO.ViajeDTO;
import com.planeta.Planeta.Model.Pasajero;
import com.planeta.Planeta.Model.Planeta;
import com.planeta.Planeta.Model.Reserva;
import com.planeta.Planeta.Model.Viaje;
import com.planeta.Planeta.Repository.IReservaRepository;
import com.planeta.Planeta.Repository.IViajeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaService implements IReservaService {

    @Autowired
    private IReservaRepository reservaRepo;
    @Autowired

    private IViajeService viajeService;

    @Override
    public void realizarReserva(Reserva reserva) {
        // Obtiene el viaje a partir del ID de la reserva
        Viaje viaje = viajeService.obtenerViajePorId(reserva.getViaje().getId());

        // Establece el viaje en la reserva
        reserva.setViaje(viaje);

        // Establecer la lista de pasajeros en la reserva
        for (Pasajero pasajero : reserva.getPasajeros()) {
            pasajero.setReserva(reserva); // Asigna la reserva al pasajero

        }

        // Llama al método de validación
        validarReserva(reserva);

        // Actualiza el viaje para reflejar la nueva reserva
        actualizarViaje(viaje, reserva.getPasajeros().size());

        // Guarda la reserva
        reservaRepo.save(reserva);
    }



    private void validarReserva(Reserva reserva) {
        Viaje viaje = reserva.getViaje();

        // Asegúrate de que el viaje no sea nulo
        if (viaje == null) {
            throw new IllegalArgumentException("El viaje no está disponible.");
        }

        // Verifica que asientosDisponibles no sea nulo o menor a cero
        Integer asientosDisponibles = viaje.getAsientosDisponibles();
        if (asientosDisponibles == null || asientosDisponibles <= 0) {
            throw new IllegalArgumentException("El viaje no tiene asientos disponibles.");
        }

        // Permitir la reserva si hay suficientes asientos
        if (asientosDisponibles < reserva.getPasajeros().size()) {
            throw new IllegalArgumentException("No hay suficientes asientos disponibles para la reserva.");
        }
    }

    private void actualizarViaje(Viaje viaje, int cantidadPasajeros) {

        viaje.setAsientosDisponibles(viaje.getAsientosDisponibles() - cantidadPasajeros);
        viajeService.actualizarViaje(viaje);
    }

    @Override
    public List<ReservaDTO> obtenerTodasLasReservas() {
        List<Reserva> reservas = reservaRepo.findAll();

        return reservas.stream()
                .map(this::mapearReservaADTO)
                .collect(Collectors.toList());
    }
    @Override
    public ReservaDTO obtenerReservaPorId(Long id) {

        Reserva reserva = reservaRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada con ID: " + id));

        return mapearReservaADTO(reserva);
    }

    // Método auxiliar para mapear una Reserva a ReservaDTO
    private ReservaDTO mapearReservaADTO(Reserva reserva) {
        ReservaDTO dto = new ReservaDTO();
        dto.setId(reserva.getId());
        dto.setClienteId(reserva.getCliente().getId());
        dto.setViaje(mapearViajeADTO(reserva.getViaje()));
        dto.setFechaReserva(reserva.getFechaReserva());

        // Mapea los pasajeros
        if (reserva.getPasajeros() != null) {
            dto.setPasajeros(reserva.getPasajeros().stream()
                    .map(this::mapearPasajeroADTO) // Mapea cada pasajero a PasajeroDTO
                    .collect(Collectors.toList()));
        } else {
            dto.setPasajeros(Collections.emptyList()); // Asigna una lista vacía si no hay pasajeros
        }

        dto.setPrecioTotal(reserva.getPrecioTotal());

        return dto;
    }


    // Método auxiliar para mapear un Pasajero a PasajeroDTO
    private PasajeroDTO mapearPasajeroADTO(Pasajero pasajero) {
        PasajeroDTO dto = new PasajeroDTO();
        dto.setId(pasajero.getId());
        dto.setNombre(pasajero.getNombre());
        dto.setApellido(pasajero.getApellido());
        dto.setEmail(pasajero.getEmail());
        // Asigna el ID de reserva si es necesario
        return dto;
    }

    private ViajeDTO mapearViajeADTO(Viaje viaje) {
        if (viaje == null) {
            return null; // Maneja el caso cuando el viaje es nulo
        }

        ViajeDTO dto = new ViajeDTO();
        dto.setId(viaje.getId());
        dto.setFechaSalida(viaje.getFechaViaje());
        dto.setDestino(mapearPlanetaADTO(viaje.getDestino())); // Asegúrate de tener un método para mapear Planeta a PlanetaDTO
        dto.setAsientosDisponibles(viaje.getAsientosDisponibles());
        dto.setCapacidadTotal(viaje.getCapacidadTotal());
        dto.setPrecioPorPasajero(viaje.getPrecioPorPasajero());

        return dto;
    }


    private PlanetaDTO mapearPlanetaADTO(Planeta planeta) {
        if (planeta == null) {
            return null; // Maneja el caso cuando el planeta es nulo
        }

        PlanetaDTO dto = new PlanetaDTO();
        dto.setId(planeta.getId());
        dto.setNombre(planeta.getNombre());
        dto.setTipo(planeta.getTipo());
        dto.setKmCuadrados(planeta.getKmCuadrados());

        return dto;
    }



    @Override
    public void cancelarReserva(Long id) {

        Reserva reserva = reservaRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada con ID: " + id));

        liberarAsientos(reserva);

        reservaRepo.delete(reserva);
    }

    private void liberarAsientos(Reserva reserva) {

        Viaje viaje = reserva.getViaje();
        viaje.setAsientosDisponibles(viaje.getAsientosDisponibles() + reserva.getPasajeros().size());

        viajeService.crearViaje(viaje);
    }



}
