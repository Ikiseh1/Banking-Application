package com.ikiseh.World_Banking_Application.payload.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor

//note this class is to allow you to receive response when you test from backend from front end,
// it's not needed
public class BankResponse<T> {
    private String responseCode;
    private String responseMessage;
    private AccountInfo accountInfo;

    public BankResponse(String responseCode, String responseMessage, AccountInfo accountInfo) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.accountInfo = accountInfo;
    }

    public BankResponse(String message, String fileUrl) {
        this.responseMessage = message;
    }

    public BankResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
