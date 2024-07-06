package com.ikiseh.World.Banking.Application.service;


import com.ikiseh.World.Banking.Application.payload.request.LoginRequest;
import com.ikiseh.World.Banking.Application.payload.request.UserRequest;
import com.ikiseh.World.Banking.Application.payload.response.ApiResponse;
import com.ikiseh.World.Banking.Application.payload.response.BankResponse;
import com.ikiseh.World.Banking.Application.payload.response.JwtAuthResponse;
import org.springframework.http.ResponseEntity;

//anything that has to do with authentication always put it in your auth service class
public interface AuthService {

    BankResponse registerUser(UserRequest userRequest);

    //day2
    //we used Api and Jwt here because Api does not have all we need so we collected some from jwt
    ResponseEntity<ApiResponse<JwtAuthResponse>> loginUser (LoginRequest loginRequest);


}
