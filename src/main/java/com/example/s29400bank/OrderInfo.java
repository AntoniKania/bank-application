package com.example.s29400bank;

public class OrderInfo {
    private final Status status;
    private final Integer balance;

    private final String message;

    public OrderInfo(Status status, Integer balance, String message) {
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

    public Integer getBalance() {
        return balance;
    }

    public String getMessage() {
        return message;
    }
}
