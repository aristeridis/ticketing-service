package com.aristeridis.service;

import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final PaymentService paymentService;

    public TicketService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public String reserveTicket(Long eventId) {
        boolean paid = paymentService.processPayment(eventId);
        if (paid) {
            return "Ticket purchased successfully";
        } else {
            return "Payment failed - please try again later";
        }
    }
}