package com.aristeridis.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Event {
    private Long id;
    private String title;
    private LocalDate date;
    private int availableSeats;

    public Event() {
    }
}