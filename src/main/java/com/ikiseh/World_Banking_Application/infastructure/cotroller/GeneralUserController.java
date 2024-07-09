package com.ikiseh.World_Banking_Application.infastructure.cotroller;


import com.ikiseh.World_Banking_Application.payload.response.BankResponse;
import com.ikiseh.World_Banking_Application.service.impl.GeneralUserServiceImpl;
import com.ikiseh.World_Banking_Application.utils.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class GeneralUserController {

    private final GeneralUserServiceImpl generalUserService;

    @PutMapping("/{id}/profile-pics")
    public ResponseEntity<BankResponse<String>> profileUpload(@PathVariable("id") Long id, @RequestParam MultipartFile profilePic){
        if(profilePic.getSize() > AppConstants.MAX_FILE_SIZE){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new BankResponse<>("file size exceeds the normal limit"));
        }
        return generalUserService.uploadProfilePics(id, profilePic);
    }
}
