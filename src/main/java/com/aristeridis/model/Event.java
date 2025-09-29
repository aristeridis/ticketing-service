package com.aristeridis.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Event {
    private Long id;
    private String title;
    private LocalDate date;
    private int availableSeats;
    private int availableTickets;

    public Event() {
    }

    public Event(Long id, String title, int availableSeats) {
        this.id = id;
        this.title = title;
        this.availableSeats = availableSeats;
    }
}