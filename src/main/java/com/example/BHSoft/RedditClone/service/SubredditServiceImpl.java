package com.example.BHSoft.RedditClone.service;

import com.example.BHSoft.RedditClone.dto.SubredditDTO;
import com.example.BHSoft.RedditClone.mapper.SubredditMapper;
import com.example.BHSoft.RedditClone.model.Subreddit;
import com.example.BHSoft.RedditClone.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditServiceImpl implements SubredditService {
    @Autowired
    private SubredditRepository subredditRepository;
    @Autowired
    private SubredditMapper subredditMapper;




    @Override
    public SubredditDTO save(SubredditDTO subredditDTO) {
        Subreddit save = subredditRepository.save(subredditMapper.mapDTOToSubreddit(subredditDTO));
        subredditDTO.setId(save.getId());
        return subredditDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubredditDTO> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SubredditDTO getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No subreddit found with id = {id}"));
        return subredditMapper.mapSubredditToDTO(subreddit);
    }


}
