package com.planeta.Planeta.Controller;

import com.planeta.Planeta.DTO.ClienteDTO;
import com.planeta.Planeta.Model.Cliente;

import com.planeta.Planeta.Service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    @GetMapping("traer")
    public ResponseEntity<List<ClienteDTO>> obtenerClientes() {
        List<ClienteDTO> clientes = clienteService.obtenerCliente();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("traer/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        return ResponseEntity.ok(cliente);
    }


    @PostMapping("crear")
    public ResponseEntity<?> crearCliente(@RequestBody Cliente cliente)
    {
        clienteService.createCliente(cliente);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("actualizar/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        // Verifica si el cliente existe
        Cliente clienteExistenteDTO = clienteService.obtenerClientePorId(id);
        if (clienteExistenteDTO == null) {
            return ResponseEntity.notFound().build();
        }

        // Crea un nuevo Cliente a partir del DTO
        Cliente clienteExistente = new Cliente();
        clienteExistente.setId(id); // Asegúrate de que el ID esté establecido
        clienteExistente.setNombre(clienteDTO.getNombre());
        clienteExistente.setApellido(clienteDTO.getApellido());
        clienteExistente.setMail(clienteDTO.getMail());
        // No actualizamos la contraseña por motivos de seguridad, a menos que se requiera

        clienteService.actualizarCliente(clienteExistente);
        return ResponseEntity.ok(clienteService.obtenerClientePorId(id));
    }

}

