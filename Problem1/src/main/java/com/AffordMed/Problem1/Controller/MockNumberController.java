package com.AffordMed.Problem1.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api")
public class MockNumberController {

    private final Random random = new Random();

    @GetMapping("/prime")
    public int getPrime() {
        return 13;
    }

    @GetMapping("/fibonacci")
    public int getFibonacci() {
        return 21;
    }

    @GetMapping("/even")
    public int getEven() {
        return 42;
    }

    @GetMapping("/random")
    public int getRandom() {
        
        return random.nextInt(100);
    }
}
