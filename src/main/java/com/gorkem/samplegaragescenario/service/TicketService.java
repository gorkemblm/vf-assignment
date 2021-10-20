package com.gorkem.samplegaragescenario.service;

import com.gorkem.samplegaragescenario.model.concretes.Ticket;
import com.gorkem.samplegaragescenario.model.concretes.Vehicle;
import com.gorkem.samplegaragescenario.model.dto.CreateTicketDto;
import com.gorkem.samplegaragescenario.model.dto.converter.TicketDtoConverter;
import com.gorkem.samplegaragescenario.model.dto.converter.VehicleDtoConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    //For the look tickets
    public List<Ticket> tickets;

    private final GarageService garageService;
    private final TicketDtoConverter ticketDtoConverter;
    private final VehicleDtoConverter vehicleDtoConverter;
    private final VehicleService vehicleService;

    public TicketService(List<Ticket> tickets, GarageService garageService, TicketDtoConverter ticketDtoConverter, VehicleDtoConverter vehicleDtoConverter, VehicleService vehicleService) {
        this.tickets = tickets;
        this.garageService = garageService;
        this.ticketDtoConverter = ticketDtoConverter;
        this.vehicleDtoConverter = vehicleDtoConverter;
        this.vehicleService = vehicleService;
    }

    public String createTicket(CreateTicketDto createTicketDto) {
        int carSize = vehicleService.findTheCarSizeForTicket(createTicketDto.getCarType());

        Vehicle vehicle = vehicleDtoConverter.convert(createTicketDto);
        vehicle.setSize(carSize);

        String result = this.garageService.addToVehicleTotalProcess(this.garageService.showTheGarage().getInMemoryGarage(), vehicle);

        if (!this.garageService.checkTheGarage(result)) {
            Ticket ticket = ticketDtoConverter.convert(createTicketDto);
            ticket.setCarSize(carSize);
            ticket.setParkStatus(true);

            this.tickets.add(ticket);
        }
        return result;
    }

    public void deleteTicket(String carPlaque) throws Exception {
        this.garageService.quitVehicleFromGarage(this.garageService.showTheGarage().getInMemoryGarage(), carPlaque);
        updateTheTicket(carPlaque);
    }

    public void updateTheTicket(String plaque) {
        Ticket ticket = findTheTicket(plaque);
        if (ticket != null) {
            ticket.setParkStatus(false);
        }
    }

    private Ticket findTheTicket(String plaque) {
        for (Ticket t : tickets) {
            if (t.getCarPlaque().equals(plaque)) {
                return t;
            }
        }
        return null;
    }
}
