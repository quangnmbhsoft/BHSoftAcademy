package com.example.BHSoft.RedditClone.service;

import com.example.BHSoft.RedditClone.dto.SignupRequest;
import com.example.BHSoft.RedditClone.dto.UserDTO;
import com.example.BHSoft.RedditClone.model.User;

public interface AuthService {
    UserDTO createUser(SignupRequest signupRequest);

    User getCurrentUser();
}
