package com.ikiseh.World_Banking_Application.infastructure.cotroller;


import com.ikiseh.World_Banking_Application.payload.request.CreditAndDebitRequest;
import com.ikiseh.World_Banking_Application.payload.request.EnquiryRequest;
import com.ikiseh.World_Banking_Application.payload.request.TransferRequest;
import com.ikiseh.World_Banking_Application.payload.response.BankResponse;
import com.ikiseh.World_Banking_Application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/balance-enquiry")
    public BankResponse balanceEnquiry(@RequestBody EnquiryRequest enquiryRequest) {
        return userService.balanceEnquiry(enquiryRequest);
    }

    @GetMapping("/name-enquiry")
    public String nameEnquiry(@RequestBody EnquiryRequest enquiryRequest) {
        return userService.nameEnquiry(enquiryRequest);
    }

    @PostMapping("/credit-account")
    public BankResponse creditAccount(@RequestBody CreditAndDebitRequest creditAndDebitRequest) {
        return userService.creditAccount(creditAndDebitRequest);
    }

    @PostMapping("/debit-account")
    public BankResponse debitAccount(@RequestBody CreditAndDebitRequest creditAndDebitRequest) {
        return userService.debitAccount(creditAndDebitRequest);
    }

    @PostMapping("/transfer")
    public BankResponse transfer(@RequestBody TransferRequest request) {
        return userService.transfer(request);
    }
}
