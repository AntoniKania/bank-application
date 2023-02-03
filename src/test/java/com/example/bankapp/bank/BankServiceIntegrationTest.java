package com.example.bankapp.bank;

import com.example.bankapp.user.UserInfo;
import com.example.bankapp.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BankServiceIntegrationTest {

    @MockBean
    private UserRepository userRepository;
    @Autowired
    private BankService bankService;
    @Test
    void successAddClientAndGetUserInfo() {
        //when
        Long id = bankService.registerClient("name", 2000d);
        UserInfo userInfo = bankService.getUserInfo(id);
        //then
        assertThat(userInfo.getName()).isEqualTo("name");
    }
}
