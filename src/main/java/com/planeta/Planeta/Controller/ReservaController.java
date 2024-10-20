package com.planeta.Planeta.Controller;

import com.planeta.Planeta.DTO.ReservaDTO;
import com.planeta.Planeta.Model.Reserva;
import com.planeta.Planeta.Service.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservas")
public class ReservaController {

    @Autowired
    private IReservaService reservaService;


    @PostMapping("/crear")
    public ResponseEntity<Reserva> crearReserva(@RequestBody Reserva reserva) {
        reservaService.crearReserva(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Obtener todas las reservas
    @GetMapping("/traer")
    public List<ReservaDTO> obtenerReservas() {
        return reservaService.obtenerReservas();
    }


    @GetMapping("traer/{id}")
    public ResponseEntity<ReservaDTO> obtenerReservaPorId(@PathVariable Long id) {
        ReservaDTO reserva = reservaService.obtenerReservaPorId(id);
        return ResponseEntity.ok(reserva);
    }

    // Eliminar una reserva
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long id) {
        reservaService.eliminarReserva(id);
        return ResponseEntity.noContent().build();
    }
}
