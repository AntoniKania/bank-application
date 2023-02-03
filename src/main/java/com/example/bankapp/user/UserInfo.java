package com.example.bankapp.user;

public class UserInfo {
    private final String name;
    private final Double balance;

    public UserInfo(String name, Double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public Double getBalance() {
        return balance;
    }
}
