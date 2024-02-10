package com.github.k1melo.crudapi.controller;

import com.github.k1melo.crudapi.model.entitie.Client;
import com.github.k1melo.crudapi.model.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/clients")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @GetMapping
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @PostMapping
    public Client postClients(@RequestBody Client client) {
        return clientRepository.save(client);
    }
}
