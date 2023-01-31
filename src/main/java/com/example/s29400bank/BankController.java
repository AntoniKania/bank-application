package com.example.s29400bank;

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
    public UserInfo getUserInfo(@PathVariable Integer clientId) {
        return bankService.getUserInfo(clientId);
    }

    @PostMapping("/user/{clientName}/{balance}")
    public Integer registerClient(@PathVariable String clientName, @PathVariable Integer balance) {
        return bankService.registerClient(clientName, balance);
    }

    @PostMapping("/deposit/{clientId}/{amount}")
    public OrderInfo depositOrder(@PathVariable Integer clientId, @PathVariable Integer amount) {
        return bankService.depositOrder(clientId, amount);
    }

    @PostMapping("/transfer/{clientId}/{amount}")
    public OrderInfo transferOrder(@PathVariable Integer clientId, @PathVariable Integer amount) {
        return bankService.transferOrder(clientId, amount);
    }
}