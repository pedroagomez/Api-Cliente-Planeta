package com.planeta.Planeta.Controller;

import com.planeta.Planeta.DTO.PlanetaDTO;
import com.planeta.Planeta.Model.Planeta;
import com.planeta.Planeta.Service.IPlanetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/planetas")
public class PlanetaController {

    @Autowired
    IPlanetaService planetaService;


    @GetMapping("traer")
    public ResponseEntity<List<PlanetaDTO>> obtenerClientes() {
        List<PlanetaDTO> planeta = planetaService.obtenerPlanetas();
        return ResponseEntity.ok(planeta);
    }

    @GetMapping("traer/{id}")
    public ResponseEntity<PlanetaDTO> obtenerPlanetaPorId(@PathVariable Long id) {
        PlanetaDTO planeta = planetaService.obtenerPlanetaPorId(id);
        return ResponseEntity.ok(planeta);
    }


    @PostMapping("crear")
    public ResponseEntity<?> crearPlaneta(@RequestBody Planeta planeta)
    {
        planetaService.createPlaneta(planeta);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Void> eliminarPlaneta(@PathVariable Long id) {
        planetaService.eliminarPlaneta(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> actualizarPlaneta(@PathVariable Long id, @RequestBody Planeta planeta) {
        planetaService.actualizarPlaneta(planeta);
        return ResponseEntity.noContent().build();
    }
}

