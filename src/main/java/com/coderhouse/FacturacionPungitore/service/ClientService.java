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

    public Client crearCliente(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> obtenerCliente(int id) {
        return clientRepository.findById(id);
    }

}
