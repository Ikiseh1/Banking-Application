package com.ikiseh.World.Banking.Application.payload.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtAuthResponse {
    private long id;
    private String firstName;
    private String lastName;
    private String profilePicture;
    private String email;
    private String gender;
    private String accessToken;
    private String tokentype = "Bearer";
}
