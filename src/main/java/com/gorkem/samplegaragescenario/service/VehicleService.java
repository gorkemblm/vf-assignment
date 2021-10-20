package com.gorkem.samplegaragescenario.service;

import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    public int findTheCarSizeForTicket(String carType) {
        switch (carType) {
            case "Car":
                return 1;
            case "Jeep":
                return 2;
            case "Truck":
                return 4;
        }
        return -1;
    }
}
