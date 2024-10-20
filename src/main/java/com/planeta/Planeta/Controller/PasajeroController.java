package com.planeta.Planeta.Controller;

import com.planeta.Planeta.DTO.PasajeroDTO;
import com.planeta.Planeta.Model.Pasajero;
import com.planeta.Planeta.Service.IPasajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pasajeros")
public class PasajeroController {

    @Autowired
    private IPasajeroService pasajeroService;

    @GetMapping("/traer")
    public ResponseEntity<List<PasajeroDTO>>obtenerListaPasajeros()
    {
        List<PasajeroDTO> pasajeros = pasajeroService.ObtenerListaPasajeros();
        return ResponseEntity.ok(pasajeros);
    }

    @GetMapping("/traer/{id}")
    public ResponseEntity<PasajeroDTO> obtenerPasajeroPorId(@PathVariable Long id)
    {
        PasajeroDTO pasajero = pasajeroService.obtenerPasajeroPorId(id);
        return ResponseEntity.ok(pasajero);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearPasajero(@RequestBody Pasajero pasajero)
    {
        pasajeroService.crearPasajero(pasajero);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PasajeroDTO> actualizarPasajero(@PathVariable Long id,
                                                          @RequestBody PasajeroDTO pasajero)
    {
        pasajeroService.actualizarPasajero(id,pasajero);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarPasajero(@PathVariable Long id)
    {
        pasajeroService.eliminarPasajero(id);
        return ResponseEntity.noContent().build();
    }
}
