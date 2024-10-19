package com.planeta.Planeta.Service;

import com.planeta.Planeta.DTO.PasajeroDTO;
import com.planeta.Planeta.Model.Pasajero;
import com.planeta.Planeta.Repository.IPasajeroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PasajeroService implements IPasajeroService {

    @Autowired
    private IPasajeroRepository pasajeroRepository;

    @Override
    public void crearPasajero(Pasajero pasajero) {
        pasajeroRepository.save(pasajero);
    }

    @Override
    public PasajeroDTO obtenerPasajeroPorId(Long id) {
        Pasajero pasajero = pasajeroRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Pasajero no encontrado "));
        PasajeroDTO dto = new PasajeroDTO();
        dto.setId(pasajero.getId());
        dto.setNombre(pasajero.getNombre());
        dto.setApellido(pasajero.getApellido());
        dto.setEmail(pasajero.getMail());
        return dto;
    }

    @Override
    public List<PasajeroDTO> ObtenerListaPasajeros() {
        List<Pasajero> pasajeros = pasajeroRepository.findAll();
        List<PasajeroDTO> pasajeroDto = pasajeros.stream().map(pasajero -> new PasajeroDTO(
                pasajero.getId(),
                pasajero.getNombre(),
                pasajero.getApellido(),
                pasajero.getMail()
        )).collect(Collectors.toList());
        return pasajeroDto;
    }

    @Override
    public void actualizarPasajero(Long id, Pasajero pasajero) {
        Pasajero pasajero1 = pasajeroRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Pasajero con id " + id + " no encontrado"));
        pasajero1.setNombre(pasajero.getNombre());
        pasajero1.setApellido(pasajero.getApellido());
        pasajero1.setMail(pasajero.getMail());
        pasajeroRepository.save(pasajero1);
    }

    @Override
    public void eliminarPasajero(Long id) {
        Pasajero pasajero = pasajeroRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Pasajero con id " + id + " no encontrado"));

        pasajeroRepository.delete(pasajero);
    }
}
