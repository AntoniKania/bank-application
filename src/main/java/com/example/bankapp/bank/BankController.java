package com.example.bankapp.bank;

import com.example.bankapp.common.OrderInfo;
import com.example.bankapp.user.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {
    BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/")
    public String returnStatus() {
        return "ok";
    }

    @GetMapping("/user/{clientId}")
    public UserInfo getUserInfo(@PathVariable Long clientId) {
        return bankService.getUserInfo(clientId);
    }

    @PostMapping("/user/{clientName}/{balance}")
    public Long registerClient(@PathVariable String clientName, @PathVariable Double balance) {
        return bankService.registerClient(clientName, balance);
    }

    @PostMapping("/deposit/{clientId}/{amount}")
    public OrderInfo depositOrder(@PathVariable Long clientId, @PathVariable Double amount) {
        return bankService.depositOrder(clientId, amount);
    }

    @PostMapping("/transfer/{clientId}/{amount}")
    public OrderInfo transferOrder(@PathVariable Long clientId, @PathVariable Double amount) {
        return bankService.transferOrder(clientId, amount);
    }
}