package com.example.bankapp.bank;

import com.example.bankapp.common.OrderInfo;
import com.example.bankapp.common.Status;
import com.example.bankapp.user.User;
import com.example.bankapp.user.UserInfo;
import com.example.bankapp.user.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BankService {
    private final UserRepository userRepository;

    public BankService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserInfo getUserInfo(Long clientId) {
        Optional<User> user = userRepository.findById(clientId);
        if (user.isEmpty()) {
            return null;
        }
        return new UserInfo(user.get().getName(), user.get().getBalance());
    }

    public Long registerClient(String clientName, Double balance) {
        Long id = userRepository.count() + 1;
        userRepository.save(new User(id, clientName, balance));
        return id;
    }

    public OrderInfo depositOrder(Long clientId, Double amount) {
        Optional<User> user = userRepository.findById(clientId);
        if (user.isEmpty()) {
            return new OrderInfo(Status.DECLINE, null, "Client don't exist in database");
        }

        user.get().increaseBalance(amount);
        return new OrderInfo(Status.ACCEPTED, user.get().getBalance(), "");
    }

    public OrderInfo transferOrder(Long clientId, Double amount) {
        Optional<User> user = userRepository.findById(clientId);

        if (user.isEmpty()) {
            return new OrderInfo(Status.DECLINE, null, "Client don't exist in database");
        }

        if (user.get().getBalance() < amount) {
            return new OrderInfo(Status.DECLINE, user.get().getBalance(), "Lack of funds");
        }

        user.get().decreaseBalance(amount);
        return new OrderInfo(Status.ACCEPTED, user.get().getBalance(), "");
    }
}
