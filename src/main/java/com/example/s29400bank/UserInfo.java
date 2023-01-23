package com.example.s29400bank;

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
