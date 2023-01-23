package com.example.s29400bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BankServiceTest {

    private BankService bankService;

    @BeforeEach
    void setup() {
        UserStorage userStorage = new UserStorage();
        bankService = new BankService(userStorage);
    }


    @Test
    void successAddClientAndGetUserInfo() {
        //when
        Integer id = bankService.registerClient("name", 2000);
        UserInfo userInfo = bankService.getUserInfo(id);
        //then
        assertThat(userInfo.getName()).isEqualTo("name");
    }
}
