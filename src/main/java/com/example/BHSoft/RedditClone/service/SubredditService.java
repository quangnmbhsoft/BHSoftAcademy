package com.example.BHSoft.RedditClone.service;

import com.example.BHSoft.RedditClone.dto.SubredditDTO;

import java.util.List;

public interface SubredditService {
    SubredditDTO save(SubredditDTO subredditDTO);


    List<SubredditDTO> getAll();

    SubredditDTO getSubreddit(Long id);
}
