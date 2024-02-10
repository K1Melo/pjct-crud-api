package com.github.k1melo.crudapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/get")
public class TestController {

    @GetMapping
    public String get() {
        System.out.println("Ta vsfd fi");
        return "Foi Ta okay";
    }

}
