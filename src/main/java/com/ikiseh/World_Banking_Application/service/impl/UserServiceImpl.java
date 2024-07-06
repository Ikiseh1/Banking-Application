package com.ikiseh.World.Banking.Application.service.impl;


import com.ikiseh.World.Banking.Application.domain.entity.UserEntity;
import com.ikiseh.World.Banking.Application.payload.request.CreditAndDebitRequest;
import com.ikiseh.World.Banking.Application.payload.request.EmailDetails;
import com.ikiseh.World.Banking.Application.payload.request.EnquiryRequest;
import com.ikiseh.World.Banking.Application.payload.response.AccountInfo;
import com.ikiseh.World.Banking.Application.payload.response.BankResponse;
import com.ikiseh.World.Banking.Application.repository.UserRepository;
import com.ikiseh.World.Banking.Application.service.EmailService;
import com.ikiseh.World.Banking.Application.service.UserService;
import com.ikiseh.World.Banking.Application.utils.AccountUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

import static com.ikiseh.World.Banking.Application.utils.AccountUtils.ACCOUNT_NUMBER_NON_EXISTS_MESSAGE;

//day2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;


    @Override
    public BankResponse balanceEnquiry(EnquiryRequest enquiryRequest) {
        boolean isAccountExists =
                userRepository.existsByAccountNumber(enquiryRequest.getAccountNumber());
        if(!isAccountExists){
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NUMBER_NON_EXISTS_CODE)
                    //note you can decide not to call it via AccountUtils, by just importing the status constant
                    .responseMessage(ACCOUNT_NUMBER_NON_EXISTS_MESSAGE)
                    .build();
        }

        UserEntity foundUserAccount = userRepository.findByAccountNumber(enquiryRequest.getAccountNumber());
        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                .responseMessage(AccountUtils.ACCOUNT_NUMBER_FOUND_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(foundUserAccount.getAccountBalance())
                        .accountNumber(foundUserAccount.getAccountNumber())
                        .accountName(foundUserAccount.getFirstName()+" "+ foundUserAccount.getLastName())
                        .build())
                .build();
    }

    @Override
    public String nameEnquiry(EnquiryRequest enquiryRequest) {

        boolean isAccountExists =
                userRepository.existsByAccountNumber(enquiryRequest.getAccountNumber());
        if(!isAccountExists){
            return AccountUtils.ACCOUNT_NUMBER_NON_EXISTS_CODE;
        }
        UserEntity foundUserAccount =
                userRepository.findByAccountNumber(enquiryRequest.getAccountNumber());
        return foundUserAccount.getFirstName()+
                " "+ foundUserAccount.getLastName()+
                " "+ foundUserAccount.getOtherName();
    }

    @Override
    public BankResponse creditAccount(CreditAndDebitRequest creditAndDebitRequest) {
        Boolean isAccountExists =
                userRepository.existsByAccountNumber(creditAndDebitRequest.getAccountNumber());

        if(!isAccountExists){
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NUMBER_NON_EXISTS_CODE)
                    .responseMessage(ACCOUNT_NUMBER_NON_EXISTS_MESSAGE)
                    .build();
        }

        UserEntity userToCredit =
                userRepository.findByAccountNumber(creditAndDebitRequest.getAccountNumber());

        userToCredit.setAccountBalance(userToCredit.getAccountBalance()
                .add(creditAndDebitRequest.getAmount()));


        userRepository.save(userToCredit);

        EmailDetails creditAlert = EmailDetails.builder()
                .subject("CREDIT ALERT!")
                .recipient(userToCredit.getEmail())
                .messageBody("Your account has been credited with "+
                        creditAndDebitRequest.getAmount()+ "from "+ userToCredit.getFirstName()
                        + " your current account balance is "+ userToCredit.getAccountBalance())
                .build();

        emailService.sendEmailAlert(creditAlert);

        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREDITED_SUCCESS_CODE)
                .responseMessage(AccountUtils.ACCOUNT_CREDITED_SUCCESS_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(userToCredit.getAccountBalance())
                        .accountNumber(userToCredit.getAccountNumber())
                        .build())
                .build();
    }

    @Override
    public BankResponse debitAccount(CreditAndDebitRequest creditAndDebitRequest) {

        Boolean isAccountExists =
                userRepository.existsByAccountNumber(creditAndDebitRequest.getAccountNumber());

        if(!isAccountExists){
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NUMBER_NON_EXISTS_CODE)
                    .responseMessage(ACCOUNT_NUMBER_NON_EXISTS_MESSAGE)
                    .build();
        }
        UserEntity userToDebit =
                userRepository.findByAccountNumber(creditAndDebitRequest.getAccountNumber());

        //check for insufficient balance
        BigInteger availableBalance =
                userToDebit.getAccountBalance().toBigInteger();

        BigInteger debitAmount = creditAndDebitRequest.getAmount().toBigInteger();

        if (availableBalance.intValue() < debitAmount.intValue()){
            return BankResponse.builder()
                    .responseCode(AccountUtils.INSUFFICIENT_FUNDS_CODE)
                    .responseMessage(AccountUtils.INSUFFICIENT_FUNDS_MESSAGE)
                    .accountInfo(null)
                    .build();
        }else{
            userToDebit.setAccountBalance(userToDebit.getAccountBalance()
                    .subtract(creditAndDebitRequest.getAmount()));
            userRepository.save(userToDebit);

            EmailDetails debitAlert = EmailDetails.builder()
                    .subject("DEBIT ALERT!")
                    .recipient(userToDebit.getEmail())
                    .messageBody("The sum of " + creditAndDebitRequest.getAmount()+
                            "has been deducted from your account! Your current "+
                            "account balance is " + userToDebit.getAccountBalance())
                    .build();

            emailService.sendEmailAlert(debitAlert);
        }
        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATED_SUCCESS_CODE)
                .responseMessage(AccountUtils.ACCOUNT_DEBITED_SUCCESS_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountName(userToDebit.getFirstName())
                        .accountNumber(userToDebit.getAccountNumber())
                        .accountBalance(userToDebit.getAccountBalance())
                        .build())
                .build();
    }
}
