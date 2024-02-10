package com.github.k1melo.crudapi.model.entitie;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.UUID;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String name;
    @NotEmpty
    private String lastName;

    @Min(1)
    @Max(100)
    private int age;

    @Email
    private String email;

    public Client() {
    }

    public Client(String username, String name, String lastName, int age, String email) {
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAll() {
        return "Id: " + id + "\n" + "Username: " + username + "\n" + "Name: " + name + "\n" + "Last name: " + lastName + "\n" + "Age: " + age + "\n" + "Email: " + email;
    }
}

