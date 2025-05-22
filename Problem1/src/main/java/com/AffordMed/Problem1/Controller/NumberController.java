package com.AffordMed.Problem1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AffordMed.Problem1.Service.NumberService;

@RestController
@RequestMapping("/average")
public class NumberController {

    @Autowired
    private NumberService numberService;

    @GetMapping
    public double getAverage() {
        return numberService.getAverage();
    }
}
