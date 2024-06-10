package dev.snackoverflow.noworryallergen.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoWorryAllergenController {
    @GetMapping("/")
    public String apiRoot() {
        return "Hello, World";
    }
}
