package com.planeta.Planeta.Controller;

import com.planeta.Planeta.DTO.ClientePlanetaPropiedadDTO;
import com.planeta.Planeta.Model.ClientePlanetaPropiedad;
import com.planeta.Planeta.Service.IClientePlanetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes-planetas")
public class ClientePlanetaController {

    @Autowired
    private IClientePlanetaService clientePlanetaService;

    @PostMapping("/crear")
    public ResponseEntity<Void> crearClientePlaneta(@RequestBody ClientePlanetaPropiedad clientePlanetaPropiedad) {
        clientePlanetaService.crearClientePlaneta(clientePlanetaPropiedad);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientePlanetaPropiedadDTO> obtenerClientePlanetaPorId(@PathVariable Long id) {
        ClientePlanetaPropiedadDTO propiedad = clientePlanetaService.obtenerClientePlanetaPorId(id);
        return ResponseEntity.ok(propiedad);
    }

    @GetMapping("/traer")
    public ResponseEntity<List<ClientePlanetaPropiedadDTO>> obtenerTodosClientesPlanetas() {
        List<ClientePlanetaPropiedadDTO> propiedades = clientePlanetaService.obtenerTodosClientesPlanetas();
        return ResponseEntity.ok(propiedades);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Void> actualizarClientePlaneta(@PathVariable Long id, @RequestBody ClientePlanetaPropiedad clientePlanetaPropiedad) {
        clientePlanetaService.actualizarClientePlaneta(id, clientePlanetaPropiedad);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarClientePlaneta(@PathVariable Long id) {
        clientePlanetaService.eliminarClientePlaneta(id);
        return ResponseEntity.noContent().build();
    }
}
