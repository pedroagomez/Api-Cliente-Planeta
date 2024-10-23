package com.planeta.Planeta.Controller;

<<<<<<< HEAD
import com.planeta.Planeta.DTO.ReservaDTO;
import com.planeta.Planeta.Model.Reserva;
import com.planeta.Planeta.Service.IReservaService;
=======
import com.planeta.Planeta.DTO.PasajeroDTO;
import com.planeta.Planeta.DTO.ReservaDTO;
import com.planeta.Planeta.Model.Cliente;
import com.planeta.Planeta.Model.Pasajero;
import com.planeta.Planeta.Model.Reserva;
import com.planeta.Planeta.Model.Viaje;
import com.planeta.Planeta.Service.IClienteService;
import com.planeta.Planeta.Service.IReservaService;
import com.planeta.Planeta.Service.IViajeService;
import jakarta.validation.Valid;
>>>>>>> main
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import java.util.List;
=======
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
>>>>>>> main

@RestController
@RequestMapping("/api/v1/reservas")
public class ReservaController {

    @Autowired
    private IReservaService reservaService;
<<<<<<< HEAD


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
=======
    @Autowired
    private IClienteService clienteService; // Servicio para obtener clientes

    @Autowired
    private IViajeService viajeService; // Servicio para obtener viajes


    @GetMapping("/traer")
    public ResponseEntity<List<ReservaDTO>>obtenerReservas()
    {
        List<ReservaDTO>reserva=reservaService.obtenerTodasLasReservas();
        return ResponseEntity.ok(reserva);
    }

    @GetMapping("/traer/{id}")
    public ResponseEntity<ReservaDTO> obtenerReservaPorId(@PathVariable Long id)
    {
        ReservaDTO reserva=reservaService.obtenerReservaPorId(id);
        return ResponseEntity.ok(reserva);
    }

    @PostMapping("/crear")
    public ResponseEntity<ReservaDTO> crearReserva(@Valid @RequestBody ReservaDTO reservaDTO) {

        Cliente cliente = clienteService.obtenerClientePorId(reservaDTO.getClienteId());

        Viaje viaje = viajeService.obtenerViajePorId(reservaDTO.getViaje().getId());
        Reserva reserva = new Reserva();
        reserva.setCliente(cliente);
        reserva.setViaje(viaje);
        reserva.setFechaReserva(LocalDate.now());
        reserva.setPasajeros(reservaDTO.getPasajeros().stream()
                .map(this::mapearPasajero)
                .collect(Collectors.toList()));
        reserva.setPrecioTotal(calcularPrecioTotal(viaje, reserva.getPasajeros().size())); // Calcular el precio total

        reservaService.realizarReserva(reserva);

        ReservaDTO reservaRespuesta = reservaService.obtenerReservaPorId(reserva.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(reservaRespuesta);
    }


    private Pasajero mapearPasajero(PasajeroDTO pasajeroDTO) {
        Pasajero pasajero = new Pasajero();
        pasajero.setNombre(pasajeroDTO.getNombre());
        pasajero.setApellido(pasajeroDTO.getApellido());
        pasajero.setEmail(pasajeroDTO.getEmail());
        return pasajero;
    }


    private double calcularPrecioTotal(Viaje viaje, int cantidadPasajeros) {
        return viaje.getPrecioPorPasajero() * cantidadPasajeros;
    }


    @DeleteMapping("/cancelar/{id}")
    public ResponseEntity<Void>cancelarReserva(@PathVariable Long id)
    {
        reservaService.cancelarReserva(id);
>>>>>>> main
        return ResponseEntity.noContent().build();
    }
}
