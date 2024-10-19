package com.planeta.Planeta.Service;


import com.planeta.Planeta.DTO.ClienteDTO;
import com.planeta.Planeta.DTO.PropiedadDTO;
import com.planeta.Planeta.Model.Cliente;
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

        // Mapeamos las propiedades del cliente
        List<PropiedadDTO> propiedades = cliente.getPropiedades().stream()
                .map(propiedad -> new PropiedadDTO(propiedad.getId(), propiedad.getPlaneta().getId(), propiedad.getKilometrosCuadrados()))
                .collect(Collectors.toList());

        // Devolvemos el ClienteDTO con las propiedades
        return new ClienteDTO(cliente.getId(), cliente.getNombre(), cliente.getApellido(), cliente.getMail(), propiedades);
    }


    @Override
    public List<ClienteDTO> obtenerCliente() {
        List<Cliente> clientes = clienteRepo.findAll();
        return clientes.stream().map(cliente -> {
            List<PropiedadDTO> propiedades = cliente.getPropiedades().stream()
                    .map(propiedad -> new PropiedadDTO(propiedad.getId(), propiedad.getPlaneta().getId(), propiedad.getKilometrosCuadrados()))
                    .collect(Collectors.toList());
            return new ClienteDTO(cliente.getId(), cliente.getNombre(), cliente.getApellido(), cliente.getMail(), propiedades);
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