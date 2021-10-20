package com.gorkem.samplegaragescenario.model.dto.converter;

import com.gorkem.samplegaragescenario.model.concretes.Vehicle;
import com.gorkem.samplegaragescenario.model.dto.CreateTicketDto;
import org.springframework.stereotype.Component;

@Component
public class VehicleDtoConverter {

    public Vehicle convert(CreateTicketDto createTicketDto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setColor(createTicketDto.getCarColor());
        vehicle.setType(createTicketDto.getCarType());
        vehicle.setPlaque(createTicketDto.getCarPlaque());

        return vehicle;
    }
}
