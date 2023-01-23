package com.example.s29400bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BankServiceIntegrationTest {

    @Autowired
    private BankService bankService;

    @MockBean
    private UserStorage userStorage;

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
