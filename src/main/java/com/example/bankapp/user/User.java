package com.example.bankapp.user;

public class User {
    private final Integer id;
    private final String name;
    private Integer balance;

    public User(Integer id, String name, Integer balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public void decreaseBalance(Integer amount) {
        balance -= amount;
    }

    public void increaseBalance(Integer amount) {
        balance += amount;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getBalance() {
        return balance;
    }
}
