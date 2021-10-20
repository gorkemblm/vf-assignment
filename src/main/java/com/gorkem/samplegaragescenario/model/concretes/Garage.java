package com.gorkem.samplegaragescenario.model.concretes;

import com.gorkem.samplegaragescenario.model.abstracts.Entity;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Data
@Component
public class Garage implements Entity {

     int capacity = 10;
     HashMap<Integer, Vehicle> inMemoryGarage = new HashMap<>();
}
