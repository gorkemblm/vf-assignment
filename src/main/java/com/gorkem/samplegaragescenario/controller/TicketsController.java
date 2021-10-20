package com.gorkem.samplegaragescenario.controller;

import com.gorkem.samplegaragescenario.model.concretes.Ticket;
import com.gorkem.samplegaragescenario.model.dto.CreateTicketDto;
import com.gorkem.samplegaragescenario.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketsController {

    private final TicketService ticketService;

    public TicketsController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTicketForCar(@RequestBody CreateTicketDto createTicketDto) {
        var result = this.ticketService.createTicket(createTicketDto);

        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @GetMapping("/status")
    public ResponseEntity<List<Ticket>> showTheTickets() {
        var result = this.ticketService.tickets;
        if (result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteTicketFromTickets(@RequestParam String carPlaque) {
        try {
            this.ticketService.deleteTicket(carPlaque);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
