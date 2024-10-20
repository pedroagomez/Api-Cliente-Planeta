package com.planeta.Planeta.Service;


import com.planeta.Planeta.DTO.*;
import com.planeta.Planeta.Model.*;
import com.planeta.Planeta.Repository.IClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository clienteRepo;

    @Override
    public void createCliente(Cliente cliente) {
        clienteRepo.save(cliente);
    }

    @Override
    public ClienteDTO obtenerClientePorId(Long id) {
        Cliente cliente = clienteRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id: " + id));

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
    }

    @Override
    public List<ClienteDTO> obtenerCliente() {
        List<Cliente> clientes = clienteRepo.findAll();
        return clientes.stream().map(cliente -> {
            List<PropiedadDTO> propiedades = mapearPropiedades(cliente.getPropiedades());
            List<ViajeDTO> viajes = mapearViajes(cliente.getViajes());
            return new ClienteDTO(
                    cliente.getId(),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getMail(),
                    propiedades,
                    viajes
            );
        }).collect(Collectors.toList());
    }




    @Override
    public void actualizarCliente(Cliente cliente) {
        if (!clienteRepo.existsById(cliente.getId())) {
            throw new EntityNotFoundException("No se puede actualizar. Cliente no encontrado con id: " + cliente.getId());
        }
        clienteRepo.save(cliente); // Actualiza el cliente
    }

    @Override
    public void eliminarCliente(Long id) {
        if (!clienteRepo.existsById(id)) {
            throw new EntityNotFoundException("Cliente no encontrado con id: " + id);
        }
        clienteRepo.deleteById(id); // Elimina el cliente
    }
}