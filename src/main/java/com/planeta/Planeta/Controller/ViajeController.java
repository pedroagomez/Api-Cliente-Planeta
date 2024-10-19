package com.planeta.Planeta.Controller;

import com.planeta.Planeta.DTO.ViajeDTO;
import com.planeta.Planeta.Model.Viaje;
import com.planeta.Planeta.Service.IViajeService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<ViajeDTO> obtenerViajePorId(@PathVariable Long id)
    {
        ViajeDTO viaje = viajeService.obtenerViajePorId(id);
        return ResponseEntity.ok(viaje);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearViaje(@RequestBody Viaje viaje)
    {
        viajeService.crearViaje(viaje);
        return ResponseEntity.created(null).build();
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarViaje(@PathVariable Long id, @RequestBody Viaje viaje)
    {
        viajeService.actualizarViaje(id, viaje);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarViaje(@PathVariable Long id)
    {
        viajeService.eliminarViaje(id);
        return ResponseEntity.ok().build();
    }
}
