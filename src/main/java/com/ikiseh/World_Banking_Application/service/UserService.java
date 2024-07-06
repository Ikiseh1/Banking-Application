package com.ikiseh.World.Banking.Application.service;


import com.ikiseh.World.Banking.Application.payload.request.CreditAndDebitRequest;
import com.ikiseh.World.Banking.Application.payload.request.EnquiryRequest;
import com.ikiseh.World.Banking.Application.payload.response.BankResponse;

//day2
public interface UserService {

    BankResponse balanceEnquiry (EnquiryRequest enquiryRequest);

    String nameEnquiry(EnquiryRequest enquiryRequest);

    BankResponse creditAccount(CreditAndDebitRequest creditAndDebitRequest);

    BankResponse debitAccount(CreditAndDebitRequest creditAndDebitRequest);


}
