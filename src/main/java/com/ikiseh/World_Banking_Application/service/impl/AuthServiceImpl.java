package com.ikiseh.World_Banking_Application.service.impl;


import com.ikiseh.World_Banking_Application.domain.entity.UserEntity;
import com.ikiseh.World_Banking_Application.domain.enums.Role;
import com.ikiseh.World_Banking_Application.payload.request.EmailDetails;
import com.ikiseh.World_Banking_Application.payload.request.LoginRequest;
import com.ikiseh.World_Banking_Application.payload.request.UserRequest;
import com.ikiseh.World_Banking_Application.payload.response.AccountInfo;
import com.ikiseh.World_Banking_Application.payload.response.ApiResponse;
import com.ikiseh.World_Banking_Application.payload.response.BankResponse;
import com.ikiseh.World_Banking_Application.payload.response.JwtAuthResponse;
import com.ikiseh.World_Banking_Application.repository.UserRepository;
import com.ikiseh.World_Banking_Application.service.AuthService;
import com.ikiseh.World_Banking_Application.service.EmailService;
import com.ikiseh.World_Banking_Application.utils.AccountUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    @Override
    public BankResponse registerUser(UserRequest userRequest) {
        if(userRepository.existsByEmail(userRequest.getEmail())){
            BankResponse response = BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)
                    .build();

            return response;
        }
        UserEntity newUser = UserEntity.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .otherName(userRequest.getOtherName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .phoneNumber(userRequest.getPhoneNumber())
                .address(userRequest.getAddress())
                .BVN(userRequest.getBVN())
                .pin(userRequest.getPin())
                .stateOfOrigin(userRequest.getStateOfOrigin())
                .accountNumber(AccountUtils.generateAccountNumber())
                .bankName("world Bank Limited")
                .accountBalance(BigDecimal.ZERO)
                .status("ACTIVE")
                .profilePicture("https://res.cloudinary.com/dpfqbb9pl/image/upload/v1701260428/maleprofile_ffeep9.png")
                .role(Role.USER)
                .build();

        UserEntity saveUser = userRepository.save(newUser);

        //Add email alert here
        EmailDetails emailDetails = EmailDetails.builder()
                .recipient(saveUser.getEmail())
                .subject("ACCOUNT CREATED")
                .messageBody("CONGRATULATIONS, YOUR ACCOUNT HAS BEEN SUCCESSFULLY CREATED.\n YOUR ACCOUNT DETAILS: \n" +
                        "Account Name: " +saveUser.getFirstName()+ " "+ saveUser.getLastName()+ " " + saveUser.getOtherName()+ " \nAccount Number: " + saveUser.getAccountNumber())
                .attachment("/Users/uyser/Downloads/IKISEH SAMUEL TOCHUKWU CV (1).docx")
                .build();

        emailService.sendEmailAlert(emailDetails);

        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATED_SUCCESS_CODE)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_SUCCESS_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(saveUser.getAccountBalance())
                        .accountNumber(saveUser.getAccountNumber())
                        .bankName(saveUser.getBankName())
                        .accountName(saveUser.getFirstName()+ " " +
                                saveUser.getLastName()+ " " +
                                saveUser.getOtherName())
                        .build())
                .build();
    }
//day2
    @Override
    public ResponseEntity<ApiResponse<JwtAuthResponse>> loginUser(LoginRequest loginRequest) {
        Optional<UserEntity> userEntityOptional =
                userRepository.findByEmail(loginRequest.getEmail());

        EmailDetails loginAlert = EmailDetails.builder()
                .subject("You are logged in")
                .recipient(loginRequest.getEmail())
                .messageBody("You logged into your account. If you did"+
                        "not initiate this request, contact support desk.")
                .build();
        emailService.sendEmailAlert(loginAlert);

        UserEntity user =userEntityOptional.get();

        return ResponseEntity.status(HttpStatus.OK)
                .body(
                        new ApiResponse<>(
                                "Login Successfully",
                                JwtAuthResponse.builder()
                                        .accessToken("generate token here")
                                        .tokentype("Bearer")
                                        .id(user.getId())
                                        .email(user.getEmail())
                                        .gender(user.getGender())
                                        .firstName(user.getFirstName())
                                        .lastName(user.getLastName())
                                        .profilePicture(user.getProfilePicture())
                                        .build()
                        )
                );

    }
}
