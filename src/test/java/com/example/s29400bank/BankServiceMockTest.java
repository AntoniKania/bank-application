package com.example.s29400bank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankServiceMockTest {

    @Mock
    private UserStorage userStorage;

    @InjectMocks
    private BankService bankService;

    @Test
    void successTransferOrder() {
        //given
        User user = new User(4, "Basia", 2000);
        when(userStorage.findUserById(any())).thenReturn(Optional.of(user));
        //when
        OrderInfo orderInfo = bankService.transferOrder(1, 1000);
        //then
        assertThat(orderInfo.getBalance()).isEqualTo(1000);
        assertThat(orderInfo.getStatus()).isEqualTo(Status.ACCEPTED);
    }

    @Test
    void successDepositOrder() {
        //given
        User user = new User(4, "Basia", 2000);
        when(userStorage.findUserById(any())).thenReturn(Optional.of(user));
        //when
        OrderInfo orderInfo = bankService.depositOrder(1, 1000);
        //then
        assertThat(orderInfo.getBalance()).isEqualTo(3000);
        assertThat(orderInfo.getStatus()).isEqualTo(Status.ACCEPTED);
    }

    @Test
    void shouldBeDeclinedIfUserDontHaveEnoughFunds() {
        //given
        User user = new User(4, "Basia", 2000);
        when(userStorage.findUserById(any())).thenReturn(Optional.of(user));
        //when
        OrderInfo orderInfo = bankService.transferOrder(1, 4000);
        //then
        assertThat(orderInfo.getStatus()).isEqualTo(Status.DECLINE);
    }

    @Test
    void getUserInfoShouldReturnNullIfClientDoesntExistInDB() {
        //given
        when(userStorage.findUserById(any())).thenReturn(Optional.empty());
        //when
        UserInfo userInfo = bankService.getUserInfo(1);
        //then
        assertThat(userInfo).isNull();
    }

    @Test
    void successGetUserInfo() {
        //given
        User user = new User(1, "John", 2000);
        //when
        when(userStorage.findUserById(any())).thenReturn(Optional.of(user));
        UserInfo userInfo = bankService.getUserInfo(any());
        //then
        assertThat(userInfo.getName()).isEqualTo("John");
    }
}