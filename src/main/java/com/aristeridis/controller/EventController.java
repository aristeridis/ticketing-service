package com.aristeridis.controller;

import com.aristeridis.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {
    private final TicketService ticketService;

    public EventController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/{eventId}/booking")
    public ResponseEntity<String> reserveTicket(@PathVariable Long eventId) {
        String result = ticketService.reserveTicket(eventId);
        return ResponseEntity.ok(result);
    }
}