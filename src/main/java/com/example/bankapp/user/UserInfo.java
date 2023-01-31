package com.example.bankapp.user;

public class UserInfo {
    private final String name;
    private final Integer balance;

    public UserInfo(String name, Integer balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public Integer getBalance() {
        return balance;
    }
}
