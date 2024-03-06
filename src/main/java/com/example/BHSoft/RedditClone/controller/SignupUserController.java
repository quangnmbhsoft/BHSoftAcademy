package com.example.BHSoft.RedditClone.controller;

import com.example.BHSoft.RedditClone.dto.SignupRequest;
import com.example.BHSoft.RedditClone.dto.UserDTO;
import com.example.BHSoft.RedditClone.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class SignupUserController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody SignupRequest signupRequest) {
        authService.createUser(signupRequest);
        return new ResponseEntity<>("Account created successfully, please activate account", HttpStatus.CREATED);
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully", OK);
    }
}
