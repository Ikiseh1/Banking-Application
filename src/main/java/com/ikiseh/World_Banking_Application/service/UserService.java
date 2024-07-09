package com.ikiseh.World_Banking_Application.service;



import com.ikiseh.World_Banking_Application.payload.request.CreditAndDebitRequest;
import com.ikiseh.World_Banking_Application.payload.request.EnquiryRequest;
import com.ikiseh.World_Banking_Application.payload.request.TransferRequest;
import com.ikiseh.World_Banking_Application.payload.response.BankResponse;

//day2
public interface UserService {

    BankResponse balanceEnquiry (EnquiryRequest enquiryRequest);

    String nameEnquiry(EnquiryRequest enquiryRequest);

    BankResponse creditAccount(CreditAndDebitRequest creditAndDebitRequest);

    BankResponse debitAccount(CreditAndDebitRequest creditAndDebitRequest);

    BankResponse transfer(TransferRequest request);


}
