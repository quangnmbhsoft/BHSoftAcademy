package com.example.BHSoft.RedditClone.service;

import com.example.BHSoft.RedditClone.dto.SubredditDTO;
import com.example.BHSoft.RedditClone.model.Subreddit;
import com.example.BHSoft.RedditClone.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditServiceImpl implements SubredditService {
    @Autowired
    private SubredditRepository subredditRepository;

    private Subreddit mapSubredditDTO(SubredditDTO subredditDTO) {
        return Subreddit.builder().name(subredditDTO.getName())
                .description(subredditDTO.getDescription())
                .build();
    }

    @Override
    public SubredditDTO save(SubredditDTO subredditDTO) {
        Subreddit save = subredditRepository.save(mapSubredditDTO(subredditDTO));
        subredditDTO.setId(save.getId());
        return subredditDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubredditDTO> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private SubredditDTO mapToDTO(Subreddit subreddit) {
        return SubredditDTO.builder().name(subreddit.getName())
                .id(subreddit.getId())
                .numberOfPosts(subreddit.getPosts().size())
                .description(subreddit.getDescription())
                .build();
    }
}
