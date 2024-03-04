package com.example.BHSoft.RedditClone.service;

import com.example.BHSoft.RedditClone.dto.AuthenticationResponse;
import com.example.BHSoft.RedditClone.dto.RefreshTokenRequest;
import com.example.BHSoft.RedditClone.dto.SignupRequest;
import com.example.BHSoft.RedditClone.model.User;

public interface AuthService {
    void createUser(SignupRequest signupRequest);

    User getCurrentUser();

    AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    boolean isLoggedIn();

    void verifyAccount(String token);
}
