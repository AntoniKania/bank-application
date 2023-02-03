package com.example.bankapp.bank;

import com.example.bankapp.common.OrderInfo;
import com.example.bankapp.common.Status;
import com.example.bankapp.user.User;
import com.example.bankapp.user.UserInfo;
import com.example.bankapp.user.UserRepository;
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
class BankServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BankService bankService;

    @Test
    void successTransferOrder() {
        //given
        User user = new User(4L, "Basia", 2000d);
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        //when
        OrderInfo orderInfo = bankService.transferOrder(1L, 1000d);
        //then
        assertThat(orderInfo.getBalance()).isEqualTo(1000);
        assertThat(orderInfo.getStatus()).isEqualTo(Status.ACCEPTED);
    }

    @Test
    void successDepositOrder() {
        //given
        User user = new User(4L, "Basia", 2000d);
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        //when
        OrderInfo orderInfo = bankService.depositOrder(1L, 1000d);
        //then
        assertThat(orderInfo.getBalance()).isEqualTo(3000);
        assertThat(orderInfo.getStatus()).isEqualTo(Status.ACCEPTED);
    }

    @Test
    void shouldBeDeclinedIfUserDontHaveEnoughFunds() {
        //given
        User user = new User(4L, "Basia", 2000d);
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        //when
        OrderInfo orderInfo = bankService.transferOrder(1L, 4000d);
        //then
        assertThat(orderInfo.getStatus()).isEqualTo(Status.DECLINE);
    }

    @Test
    void getUserInfoShouldReturnNullIfClientDoesntExistInDB() {
        //given
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        //when
        UserInfo userInfo = bankService.getUserInfo(1L);
        //then
        assertThat(userInfo).isNull();
    }

    @Test
    void successGetUserInfo() {
        //given
        User user = new User(1L, "John", 2000d);
        //when
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        UserInfo userInfo = bankService.getUserInfo(any());
        //then
        assertThat(userInfo.getName()).isEqualTo("John");
    }
}