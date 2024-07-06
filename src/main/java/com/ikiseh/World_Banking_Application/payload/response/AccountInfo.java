package com.ikiseh.World.Banking.Application.payload.response;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountInfo {
    private String accountName;

    //we are using BigDecimal for precision
    private BigDecimal accountBalance;

    private String accountNumber;

    private String bankName;
}
