package com.github.k1melo.crudapi.controller;

import com.github.k1melo.crudapi.model.entitie.Client;
import com.github.k1melo.crudapi.model.repository.ClientRepository;
import com.github.k1melo.crudapi.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(path = "/{username}")
    public ResponseEntity<String> getClientById(@PathVariable String username) {
        if (clientRepository.findByUsername(username) == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        return ResponseEntity.status(200).body(clientRepository.findByUsername(username).getAll());
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
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered: " + client.getUsername());
        }
    }

    @PutMapping(path = "/{username}")
    public ResponseEntity<String> updateClientsById(@PathVariable String username, @RequestBody Client client) {

        Client currentClient = this.clientRepository.findByUsername(username);
        if (currentClient == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        Utils.copyNonNullProperties(client, currentClient);
        Client updateClient = this.clientRepository.save(currentClient);

        return ResponseEntity.ok("Client " + updateClient.getUsername() + " updated");
    }

    @DeleteMapping(path = "/{username}")
    public ResponseEntity<String> deleteClientsById(@PathVariable String username) {
        Client client = clientRepository.findByUsername(username);
        if (client == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");

        clientRepository.delete(client);
        return ResponseEntity.ok("Client " + client.getUsername() + " was deleted");
    }
}