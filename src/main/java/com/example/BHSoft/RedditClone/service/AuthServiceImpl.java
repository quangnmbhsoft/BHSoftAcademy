package com.example.BHSoft.RedditClone.service;

import com.example.BHSoft.RedditClone.dto.SignupRequest;
import com.example.BHSoft.RedditClone.dto.UserDTO;
import com.example.BHSoft.RedditClone.model.User;
import com.example.BHSoft.RedditClone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDTO createUser(SignupRequest signupRequest) {
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setEmail(signupRequest.getEmail());
        user.setCreated(Instant.now());
        user.setEnabled(signupRequest.isEnabled());
        User createdUser = userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(createdUser.getUsername());
        userDTO.setPassword(createdUser.getPassword());
        userDTO.setEmail(createdUser.getEmail());
        userDTO.setCreated(createdUser.getCreated());
        userDTO.setEnabled(createdUser.isEnabled());

        return userDTO;

    }
}
