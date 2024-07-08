package com.ikiseh.World_Banking_Application.payload.request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDetails {

    private String recipient;

    private String messageBody;

    private String attachment;

    private String subject;

}
