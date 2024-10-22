package com.planeta.Planeta.Service;


import com.planeta.Planeta.DTO.*;
import com.planeta.Planeta.Model.*;
import com.planeta.Planeta.Repository.IClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.query.Page;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository clienteRepository;
    @Autowired
    private ModelMapper modelMapper;


    public void createCliente(Cliente cliente) {
        if (clienteRepository.existsByMail(cliente.getMail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        clienteRepository.save(cliente);
    }

    @Override
    public Cliente obtenerClientePorId(Long id) {

        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + id));
    }

    @Override
    public List<ClienteDTO> obtenerCliente() {
        List<Cliente> clientes = clienteRepository.findAll();
        return mapearClientesADTO(clientes);
    }

    // Método para mapear una lista de clientes a DTOs
    private List<ClienteDTO> mapearClientesADTO(List<Cliente> clientes) {
        return clientes.stream()
                .map(this::mapearClienteADTO)
                .collect(Collectors.toList());
    }

    // Método para mapear un cliente a DTO
    private ClienteDTO mapearClienteADTO(Cliente cliente) {
        ClienteDTO dto = modelMapper.map(cliente, ClienteDTO.class);
        dto.setPropiedades(mapearPropiedadesADTO(cliente.getPropiedades()));
        dto.setReservas(mapearReservasADTO(cliente.getReservas()));
        return dto;
    }

    private List<ClientePlanetaPropiedadDTO> mapearPropiedadesADTO(List<ClientePlanetaPropiedad> propiedades) {
        if (propiedades == null) {
            return new ArrayList<>();
        }
        return propiedades.stream()
                .map(this::mapearPropiedadADTO)
                .collect(Collectors.toList());
    }

    private ClientePlanetaPropiedadDTO mapearPropiedadADTO(ClientePlanetaPropiedad propiedad) {
        if (propiedad == null) {
            return null;
        }
        return modelMapper.map(propiedad, ClientePlanetaPropiedadDTO.class); // Usando ModelMapper
    }

    private List<ReservaDTO> mapearReservasADTO(List<Reserva> reservas) {
        if (reservas == null) {
            return new ArrayList<>();
        }
        return reservas.stream()
                .map(this::mapearReservaADTO)
                .collect(Collectors.toList());
    }

    private ReservaDTO mapearReservaADTO(Reserva reserva) {
        if (reserva == null) {
            return null; // Manejo de null
        }
        ReservaDTO dto = modelMapper.map(reserva, ReservaDTO.class);
        dto.setViaje(mapearViajeADTO(reserva.getViaje()));
        dto.setPasajeros(mapearPasajerosADTO(reserva.getPasajeros()));
        return dto;
    }

    private ViajeDTO mapearViajeADTO(Viaje viaje) {
        if (viaje == null) {
            return null; // Manejo de null
        }
        ViajeDTO dto = modelMapper.map(viaje, ViajeDTO.class);
        dto.setDestino(mapearPlanetaADTO(viaje.getDestino()));
        return dto;
    }

    private PlanetaDTO mapearPlanetaADTO(Planeta planeta) {
        if (planeta == null) {
            return null;
        }
        return modelMapper.map(planeta, PlanetaDTO.class);
    }

    private List<PasajeroDTO> mapearPasajerosADTO(List<Pasajero> pasajeros) {
        if (pasajeros == null) {
            return new ArrayList<>();
        }
        return pasajeros.stream()
                .map(this::mapearPasajeroADTO)
                .collect(Collectors.toList());
    }

    private PasajeroDTO mapearPasajeroADTO(Pasajero pasajero) {
        if (pasajero == null) {
            return null;
        }
        return modelMapper.map(pasajero, PasajeroDTO.class);
    }

    @Override
    public void actualizarCliente(Cliente cliente) {
            clienteRepository.save(cliente);
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
