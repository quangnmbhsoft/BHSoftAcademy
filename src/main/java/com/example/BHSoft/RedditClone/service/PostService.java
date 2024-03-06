package com.example.BHSoft.RedditClone.service;

import com.example.BHSoft.RedditClone.dto.PostRequest;
import com.example.BHSoft.RedditClone.dto.PostResponse;

import java.util.List;

public interface PostService {
    PostRequest save(PostRequest postRequest);

    List<PostResponse> getAllPosts();

    PostResponse getPost(Long id);

    List<PostResponse> getPostsBySubreddit(Long subredditId);

    List<PostResponse> getPostsByUsername(String username);
}
