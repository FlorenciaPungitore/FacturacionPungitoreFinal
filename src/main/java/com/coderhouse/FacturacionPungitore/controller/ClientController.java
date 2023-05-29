package com.coderhouse.FacturacionPungitore.controller;

import com.coderhouse.FacturacionPungitore.ResponseHandler;
import com.coderhouse.FacturacionPungitore.model.Client;
import com.coderhouse.FacturacionPungitore.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    //Creacion de Clientes
    @PostMapping
    public ResponseEntity<Object> crearCliente(@RequestBody Client client) {
        try {
            Client clienteGuardado = clientService.crearCliente(client);
            return ResponseHandler.generateResponse("Cliente creado correctamente", HttpStatus.CREATED, clienteGuardado);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    //Obtencion de Clientes
    @GetMapping(path = "{id}")
    public ResponseEntity<Object> obtenerCliente(@PathVariable int id) {
        try {
            Optional<Client> clienteObtenido = clientService.obtenerCliente(id);
            if (clienteObtenido.isPresent()) {
                return ResponseHandler.generateResponse("Cliente encontrado correctamente", HttpStatus.OK, clienteObtenido);
            } else {
                return ResponseHandler.generateResponse("Cliente con id "+ id + " inexsistente.", HttpStatus.NOT_FOUND, null);
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

}