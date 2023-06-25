package com.coderhouse.FacturacionPungitore.service;

import com.coderhouse.FacturacionPungitore.model.Client;
import com.coderhouse.FacturacionPungitore.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client guardarCliente(Client client) throws Exception {

        if (client.getName() == null || client.getName().isBlank()) {
            throw new Exception("El campo NAME no puede estar vacio.");
        }
        if (client.getDocnumber() == null || client.getDocnumber().isBlank()) {
            throw new Exception("El campo DOCNUMBER no puede estar vacio.");
        } else {
            try {
                Integer.parseInt(client.getDocnumber());
            } catch (NumberFormatException e) {
                throw new Exception("El campo DOCNUMBER debe ser un numero.");
            }
        }
        if (client.getLastname() == null || client.getLastname().isBlank()) {
            throw new Exception("El campo LASTNAME no puede estar vacio.");
        }

        return clientRepository.save(client);
    }

    public Client actualizarCliente(Client client) throws Exception {
        Optional<Client> clienteObtenido = obtenerCliente(client.getId());
        if (clienteObtenido.isPresent()) {
            if (client.getName() == null || client.getName().isBlank()) {
                client.setName(clienteObtenido.get().getName());
            }

            if (client.getLastname() == null || client.getLastname().isBlank()) {
                client.setLastname(clienteObtenido.get().getLastname());
            }

            if (client.getDocnumber() == null || client.getDocnumber().isBlank()) {
                client.setDocnumber(clienteObtenido.get().getDocnumber());
            } else {
                try {
                    Integer.parseInt(client.getDocnumber());
                } catch (NumberFormatException e) {
                    throw new Exception("El campo DOCNUMBER debe ser un numero.");
                }
            }

            return clientRepository.save(client);
        } else {
            throw new Exception("Cliente con id "+ client.getId() + " inexsistente.");
        }
    }

    public Optional<Client> obtenerCliente(int id) {
        return clientRepository.findById(id);
    }

    public void eliminarCliente(Client client) {
        clientRepository.delete(client);
    }

}
