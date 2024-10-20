package com.planeta.Planeta.Controller;

import com.planeta.Planeta.DTO.ViajeDTO;
import com.planeta.Planeta.Model.Viaje;
import com.planeta.Planeta.Service.IViajeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/viajes")
public class ViajeController {

    @Autowired
    private IViajeService viajeService;

    @GetMapping("/traer")
    public ResponseEntity<List<ViajeDTO>>obtenerViaje()
    {
        List<ViajeDTO>listaViaje=viajeService.obtenerTodosLosViajes();
        return ResponseEntity.ok(listaViaje);
    }

    @GetMapping("/traer/{id}")
    public ResponseEntity<Viaje> obtenerViajePorId(@PathVariable Long id) {
        try {
            Viaje viaje = viajeService.obtenerViajePorId(id);
            return ResponseEntity.ok(viaje);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @PostMapping("/crear")
    public ResponseEntity<?> crearViaje(@RequestBody Viaje viaje)
    {
        viajeService.crearViaje(viaje);
        return ResponseEntity.created(null).build();
    }



    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarViaje(@PathVariable Long id)
    {
        viajeService.eliminarViaje(id);
        return ResponseEntity.ok().build();
    }
}
