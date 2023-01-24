package com.example.s29400bank;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Component
public class BankService {
    private final UserStorage userStorage;

    public BankService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @GetMapping("/")
    public String returnStatus() {
        return "ok";
    }

    @GetMapping("/user/{clientId}")
    public UserInfo getUserInfo(@PathVariable Integer clientId) {
        Optional<User> user = userStorage.findUserById(clientId);
        if (user.isEmpty()) {
            return null;
        }
        return new UserInfo(user.get().getName(), user.get().getBalance());
    }

    @PostMapping("/user/{clientName}/{balance}")
    public Integer registerClient(@PathVariable String clientName, @PathVariable Integer balance) {
        Integer id = userStorage.getUserList().size() + 1;
        userStorage.addUser(new User(id, clientName, balance));
        return id;
    }

    @PostMapping("/deposit/{clientId}/{amount}")
    public OrderInfo depositOrder(@PathVariable Integer clientId, @PathVariable Integer amount) {
        Optional<User> user = userStorage.findUserById(clientId);
        if (user.isEmpty()) {
            return new OrderInfo(Status.DECLINE, 0, "Client don't exist in database");
        }

        user.get().increaseBalance(amount);
        return new OrderInfo(Status.ACCEPTED, user.get().getBalance(), "");
    }

    @PostMapping("/transfer/{clientId}/{amount}")
    public OrderInfo transferOrder(@PathVariable Integer clientId, @PathVariable Integer amount) {
        Optional<User> user = userStorage.findUserById(clientId);

        if (user.isEmpty()) {
            return new OrderInfo(Status.DECLINE, 0, "Client don't exist in database");
        }

        if (user.get().getBalance() < amount) {
            return new OrderInfo(Status.DECLINE, 0, "Lack of funds");
        }

        user.get().decreaseBalance(amount);
        return new OrderInfo(Status.ACCEPTED, user.get().getBalance(), "");
    }
}
