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

<<<<<<< HEAD
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getMail(),
                mapearPropiedades(cliente.getPropiedades()),
                mapearViajes(cliente.getViajes())
        );
    }

    private List<PropiedadDTO> mapearPropiedades(List<ClientePlanetaPropiedad> propiedades) {
        return propiedades.stream()
                .map(this::mapearPropiedad)
                .collect(Collectors.toList());
    }

    private PropiedadDTO mapearPropiedad(ClientePlanetaPropiedad propiedad) {
        return new PropiedadDTO(
                propiedad.getId(),
                propiedad.getPlaneta().getId(),
                propiedad.getKilometrosCuadrados()
        );
    }



    private List<ViajeDTO> mapearViajes(List<Viaje> viajes) {
        return viajes.stream()
                .map(this::mapearViaje)
                .collect(Collectors.toList());
    }


    private ViajeDTO mapearViaje(Viaje viaje) {
        PlanetaDTO planetaDTO = null;
        Long planetaId = null;
        String planetaNombre = null;

        if (viaje.getDestino() != null) {
            Planeta planeta = viaje.getDestino();
            planetaId = planeta.getId();
            planetaNombre = planeta.getNombre();

            // Creamos el PlanetaDTO de manera segura
            planetaDTO = new PlanetaDTO();
            planetaDTO.setId(planetaId);
            planetaDTO.setNombre(planetaNombre);

            // Intentamos establecer el tipo y kmCuadrados si están disponibles
            try {
                planetaDTO.setTipo(planeta.getTipo());
            } catch (Exception e) {
                // Si no existe el método getTipo(), simplemente lo dejamos como null
            }

            try {
                planetaDTO.setKmCuadrados(planeta.getKmCuadrados());
            } catch (Exception e) {
                // Si no existe el método getKmCuadrados(), o si devuelve un tipo incompatible, lo dejamos como 0
                planetaDTO.setKmCuadrados(0);
            }
        }

        return new ViajeDTO(
                viaje.getId(),
                planetaId,
                planetaNombre,
                viaje.getFechaViaje(),
                planetaDTO,
                viaje.getAsientosDisponibles(),
                viaje.getPrecioPorPasajero(),
                mapearPasajeros(viaje.getPasajeros())
        );
    }
    private List<PasajeroDTO> mapearPasajeros(List<Pasajero> pasajeros) {
        return pasajeros.stream()
                .map(this::mapearPasajero)
                .collect(Collectors.toList());
    }

    private PasajeroDTO mapearPasajero(Pasajero pasajero) {
        return new PasajeroDTO(
                pasajero.getId(),
                pasajero.getNombre(),
                pasajero.getApellido(),
                pasajero.getMail()
        );
=======
        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + id));
>>>>>>> main
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
