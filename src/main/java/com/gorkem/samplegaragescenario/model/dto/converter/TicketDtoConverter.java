package com.gorkem.samplegaragescenario.model.dto.converter;

import com.gorkem.samplegaragescenario.model.concretes.Ticket;
import com.gorkem.samplegaragescenario.model.dto.CreateTicketDto;
import org.springframework.stereotype.Component;

@Component
public class TicketDtoConverter {

    public Ticket convert(CreateTicketDto createTicketDto) {
        Ticket ticket = new Ticket();

        ticket.setCarPlaque(createTicketDto.getCarPlaque());
        ticket.setCarColor(createTicketDto.getCarColor());
        ticket.setReasonForApplication(createTicketDto.getReasonForApplication());

        return ticket;
    }
}
