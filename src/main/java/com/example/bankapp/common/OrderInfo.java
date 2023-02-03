package com.example.bankapp.common;

public class OrderInfo {
    private final Status status;
    private final Double balance;
    private final String message;

    public OrderInfo(Status status, Double balance, String message) {
        this.status = status;
        this.balance = balance;
        this.message = message;
    }

    @Override
    public String toString() {
        return "OrderInfo: status: " + status + " balance: " + balance;
    }

    public Status getStatus() {
        return status;
    }

    public Double getBalance() {
        return balance;
    }

    public String getMessage() {
        return message;
    }
}
