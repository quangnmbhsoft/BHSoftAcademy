package com.example.BHSoft.RedditClone.service;


import com.example.BHSoft.RedditClone.model.RefreshToken;

public interface RefreshTokenService {
    RefreshToken generateRefreshToken();

    void validateRefreshToken(String token);

    void deleteRefreshToken(String token);
}
