package com.aristeridis.controller;

import com.aristeridis.model.Event;
import com.aristeridis.service.PaymentService;
import com.aristeridis.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/events")
public class EventController {
    private final Map<Long, Event> events = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(EventController.class);
    private final PaymentService paymentService;


    public EventController(PaymentService paymentService) {
        this.paymentService = paymentService;
        events.put(1L, new Event(1L, "Football", 50));
        events.put(2L, new Event(2L, "Basket", 30));
        events.put(3L, new Event(3L, "Tennis", 100));
    }

    @PostMapping("/{id}/reserve")
    public String reserveTicket(@PathVariable Long id) {
        Event event = events.get(id);
        if (event == null || event.getAvailableSeats() <= 0) {
            logger.info("Reservation failed for Event ID {}: Event not found or no seats left", id);
            return "Event not found or no seats left";
        }

        boolean paymentResult = paymentService.processPayment(id);
        if (paymentResult) {
            event.setAvailableSeats(event.getAvailableSeats() - 1);
            logger.info("Reservation SUCCESS for Event ID {} ({}) | Remaining seats: {}",
                    id, event.getTitle(), event.getAvailableSeats());
            return "Reservation successful for " + event.getTitle() +
                    " | Remaining seats: " + event.getAvailableSeats();
        } else {
            logger.info("Reservation FAILED for Event ID {} ({}) | Payment failed", id, event.getTitle());
            return "Payment failed - please try later";
        }
    }

    @GetMapping
    public Collection<Event> getAllEvents() {
        return events.values();
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id) {
        return events.get(id);
    }
}