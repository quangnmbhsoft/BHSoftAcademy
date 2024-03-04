package com.example.BHSoft.RedditClone.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class SignupRequest {
    private String username;
    private String password;
    private String email;
    private Instant created;
    private boolean enabled;
}