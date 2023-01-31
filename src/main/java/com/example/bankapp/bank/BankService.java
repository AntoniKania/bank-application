package com.example.bankapp.bank;

import com.example.bankapp.common.OrderInfo;
import com.example.bankapp.common.Status;
import com.example.bankapp.user.User;
import com.example.bankapp.user.UserInfo;
import com.example.bankapp.user.UserStorage;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BankService {
    private final UserStorage userStorage;

    public BankService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public UserInfo getUserInfo(Integer clientId) {
        Optional<User> user = userStorage.findUserById(clientId);
        if (user.isEmpty()) {
            return null;
        }
        return new UserInfo(user.get().getName(), user.get().getBalance());
    }

    public Integer registerClient(String clientName, Integer balance) {
        Integer id = userStorage.getUserList().size() + 1;
        userStorage.addUser(new User(id, clientName, balance));
        return id;
    }

    public OrderInfo depositOrder(Integer clientId, Integer amount) {
        Optional<User> user = userStorage.findUserById(clientId);
        if (user.isEmpty()) {
            return new OrderInfo(Status.DECLINE, 0, "Client don't exist in database");
        }

        user.get().increaseBalance(amount);
        return new OrderInfo(Status.ACCEPTED, user.get().getBalance(), "");
    }

    public OrderInfo transferOrder(Integer clientId, Integer amount) {
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
