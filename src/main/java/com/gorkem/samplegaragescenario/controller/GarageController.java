package com.gorkem.samplegaragescenario.controller;

import com.gorkem.samplegaragescenario.model.concretes.Garage;
import com.gorkem.samplegaragescenario.service.GarageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/garage")
public class GarageController {

    private final GarageService garageService;

    public GarageController(GarageService garageService) {
        this.garageService = garageService;
    }

    @GetMapping("/status")
    public ResponseEntity<Garage> showTheGarage() {
        var result = this.garageService.showTheGarage();

        if (result.getInMemoryGarage().isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
}
