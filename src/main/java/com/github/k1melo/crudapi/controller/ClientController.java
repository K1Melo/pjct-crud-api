package com.github.k1melo.crudapi.controller;

import com.github.k1melo.crudapi.model.entitie.Client;
import com.github.k1melo.crudapi.model.repository.ClientRepository;
import com.github.k1melo.crudapi.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public ResponseEntity<String> postClients(@RequestBody Client client) {

        if (clientRepository.findByEmail(client.getEmail()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already registered");
        } else {
            if (!client.getEmail().contains(".com")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email not valid");
            }
            clientRepository.save(client);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered: " + client.getName());
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> updateClients(@PathVariable Long id, @RequestBody Client client) {

        Client currentClient = this.clientRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

        Utils.copyNonNullProperties(client, currentClient);
        Client updateClient = this.clientRepository.save(currentClient);

        return ResponseEntity.ok("Client " + updateClient.getName() + " updated");
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteClients(@PathVariable Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
        clientRepository.delete(client);
        return ResponseEntity.ok("Client " + client.getName() + " was deleted");
    }
}
