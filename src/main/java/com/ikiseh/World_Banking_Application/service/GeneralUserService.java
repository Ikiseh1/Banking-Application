package com.ikiseh.World_Banking_Application.service;

import com.ikiseh.World_Banking_Application.payload.response.BankResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;



public interface GeneralUserService {

    ResponseEntity<BankResponse<String>> uploadProfilePics(Long id, MultipartFile multipartFile);
}
