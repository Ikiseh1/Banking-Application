package com.ikiseh.World_Banking_Application.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

//here houses all the info we want our client to send
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    //to use all this extra command, we add @valid in our AuthController

    @NotBlank(message = "First name must not be blank")
    @Size(min = 2, max = 125, message = "First name must be at least 2 character long")
    private String firstName;

    @NotBlank(message = "Last name must not be blank")
    @Size(min = 2, max = 125, message = "Last name must be at least 2 character long")
    private String lastName;

    private String otherName;

    @NotBlank(message = "Email must not be blank")
    private String email;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 4, max = 20, message = "Password must be at least 4 character long")
    private String password;


    @NotBlank(message = "Gender must not be blank")
    private String gender;

    @NotBlank(message = "Address must not be blank")
    private String address;

    @NotBlank(message = "State Of Origin must not be blank")
    private String stateOfOrigin;

    @NotBlank(message = "PhoneNumber must not be blank")
    private String phoneNumber;

    @NotBlank(message = "BVN must not be blank")
    private String BVN;

    @NotBlank(message = "Pin must not be blank")
    @Size(min = 4, max = 8, message = "Pin must be at least 4 character long")
    private String pin;
}
