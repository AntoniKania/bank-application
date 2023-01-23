package com.example.s29400bank;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserStorage {
    private final List<User> userList = new ArrayList<>();

    public UserStorage() {
        userList.add(new User(0,"Zbigniew", 100));
        userList.add(new User(1,"Ryszard", 1000));
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public Optional<User> findUserById(Integer id) {
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public List<User> getUserList() {
        return userList;
    }
}
