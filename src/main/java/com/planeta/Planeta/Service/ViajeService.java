package com.planeta.Planeta.Service;

import com.planeta.Planeta.DTO.PasajeroDTO;
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
    private IClienteRepository clienteRepository;
    @Autowired
    private IPlanetaRepository planetaRepository;
    @Autowired
    private IPasajeroRepository pasajeroRepository;

    @Override
    public void crearViaje(Viaje viaje) {
        viajeRepository.save(viaje);
    }

    @Override
    public ViajeDTO obtenerViajePorId(Long id) {
        Viaje viaje = viajeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaje no encontrado con id: " + id));

        // Mapear a DTO
        ViajeDTO dto = new ViajeDTO();
        dto.setId(viaje.getId());

        // Verificar si el cliente no es null
        if (viaje.getCliente() != null) {
            dto.setCliente_id(viaje.getCliente().getId());
        } else {
            dto.setCliente_id(null);
        }

        // Verificar si el destino no es null
        if (viaje.getDestino() != null) {
            dto.setPlaneta_id(viaje.getDestino().getId());
        } else {
            dto.setPlaneta_id(null);
        }

        List<PasajeroDTO> pasajerosDTO = viaje.getPasajeros().stream()
                .map(this::mapearPasajeroADTO)
                .collect(Collectors.toList());
        dto.setListaPasajero(pasajerosDTO);

        return dto;
    }


    @Override
    public List<ViajeDTO> obtenerTodosLosViajes() {
        List<Viaje> viajes = viajeRepository.findAll();

        // Filtrar solo los viajes que tienen un cliente asignado
        List<Viaje> viajesConCliente = viajes.stream()
                .filter(viaje -> viaje.getCliente() != null)
                .collect(Collectors.toList());

        // Mapear los viajes filtrados a DTO
        return viajesConCliente.stream()
                .map(this::mapearViajeADTO)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public void actualizarViaje(Long id, Viaje viajeActualizado) {
        Viaje viajeExistente = viajeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaje no encontrado con id: " + id));

        // Actualizar el cliente del viaje
        if (viajeActualizado.getCliente() != null) {
            Cliente cliente = clienteRepository.findById(viajeActualizado.getCliente().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id: " + viajeActualizado.getCliente().getId()));
            viajeExistente.setCliente(cliente);
        }

        // Actualizar el destino del viaje
        if (viajeActualizado.getDestino() != null) {
            Planeta destino = planetaRepository.findById(viajeActualizado.getDestino().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Planeta no encontrado con id: " + viajeActualizado.getDestino().getId()));
            viajeExistente.setDestino(destino);
        }

        // Actualizar la fecha del viaje
        if (viajeActualizado.getFechaViaje() != null) {
            viajeExistente.setFechaViaje(viajeActualizado.getFechaViaje());
        }

        // Actualizar la lista de pasajeros si es necesario
        if (viajeActualizado.getPasajeros() != null && !viajeActualizado.getPasajeros().isEmpty()) {
            List<Pasajero> pasajeros = viajeActualizado.getPasajeros().stream()
                    .map(pasajero -> pasajeroRepository.findById(pasajero.getId())
                            .orElseThrow(() -> new EntityNotFoundException("Pasajero no encontrado con id: " + pasajero.getId())))
                    .collect(Collectors.toList());
            viajeExistente.setPasajeros(pasajeros);
        }

    }

    @Override
    public void eliminarViaje(Long id) {
        if(!viajeRepository.existsById(id)) {
            throw new EntityNotFoundException("Viaje no encontrado con id: " + id);
        }
        viajeRepository.deleteById(id);
    }

    private PasajeroDTO mapearPasajeroADTO(Pasajero pasajero) {
        return new PasajeroDTO(
                pasajero.getId(),
                pasajero.getNombre(),
                pasajero.getApellido(),
                pasajero.getMail()
        );
    }

    private ViajeDTO mapearViajeADTO(Viaje viaje) {
        ViajeDTO viajeDTO = new ViajeDTO();
        viajeDTO.setId(viaje.getId());

        // Verificar si el cliente no es null antes de acceder a su ID
        if (viaje.getCliente() != null) {
            viajeDTO.setCliente_id(viaje.getCliente().getId());
        } else {
            viajeDTO.setCliente_id(null);  // O puedes dejarlo sin setear si prefieres
        }

        // Verificar si el destino no es null antes de acceder a su ID
        if (viaje.getDestino() != null) {
            viajeDTO.setPlaneta_id(viaje.getDestino().getId());
        } else {
            viajeDTO.setPlaneta_id(null);  // O puedes dejarlo sin setear si prefieres
        }

        List<PasajeroDTO> pasajerosDTO = viaje.getPasajeros().stream()
                .map(this::mapearPasajeroADTO)
                .collect(Collectors.toList());
        viajeDTO.setListaPasajero(pasajerosDTO);

        return viajeDTO;
    }


}
