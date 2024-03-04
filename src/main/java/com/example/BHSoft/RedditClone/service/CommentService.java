package com.example.BHSoft.RedditClone.service;

import com.example.BHSoft.RedditClone.dto.CommentDTO;

import java.util.List;

public interface CommentService {

    void save(CommentDTO commentDTO);

    Object getAllCommentsForPost(Long postId);

    List<CommentDTO> getAllCommentsForUser(String userName);
}
