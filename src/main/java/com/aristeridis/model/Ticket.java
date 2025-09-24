package com.aristeridis.model;

import lombok.Data;

@Data
public class Ticket {
    private Long id;
    private Long eventId;
    private String owner;
    private String status;

    public Ticket() {
    }
}