package com.planeta.Planeta.Service;

import com.planeta.Planeta.DTO.ReservaDTO;
import com.planeta.Planeta.Model.Reserva;

import java.util.List;

public interface IReservaService {


    void crearReserva(Reserva reserva);
    List<ReservaDTO> obtenerReservas();
    ReservaDTO obtenerReservaPorId(Long id);
    void eliminarReserva(Long id);



}
