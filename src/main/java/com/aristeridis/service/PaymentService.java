package com.aristeridis.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentService {

    private final Random rnd = new Random();

    @Retry(name = "paymentRetry", fallbackMethod = "fallbackPayment")
    @CircuitBreaker(name = "paymentCircuitBreaker", fallbackMethod = "fallbackPayment")
    public boolean processPayment(Long eventId) {
        int val = rnd.nextInt(10);
        if (val < 7) {
            System.out.println("Payment failure (val=" + val + ")");
            throw new RuntimeException("Payment API failure");
        }
        System.out.println("Payment success (val=" + val + ")");
        return true;
    }

    public boolean fallbackPayment(Long eventId, Throwable t) {
        System.out.println("Fallback called for event " + eventId + ": " + t.getMessage());
        return false;
    }
}
