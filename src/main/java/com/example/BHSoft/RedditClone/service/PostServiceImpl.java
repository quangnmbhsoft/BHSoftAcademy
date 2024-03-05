package com.example.BHSoft.RedditClone.service;

import com.example.BHSoft.RedditClone.dto.PostRequest;
import com.example.BHSoft.RedditClone.dto.PostResponse;
import com.example.BHSoft.RedditClone.mapper.PostMapper;
import com.example.BHSoft.RedditClone.model.Post;
import com.example.BHSoft.RedditClone.model.Subreddit;
import com.example.BHSoft.RedditClone.model.User;
import com.example.BHSoft.RedditClone.repository.PostRepository;
import com.example.BHSoft.RedditClone.repository.SubredditRepository;
import com.example.BHSoft.RedditClone.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class PostServiceImpl implements PostService{
    @Autowired
    private SubredditRepository subredditRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public void save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new RuntimeException(postRequest.getSubredditName()));
        User currentUser = authService.getCurrentUser();
        postRepository.save(postMapper.map(postRequest, subreddit, authService.getCurrentUser()));

    }

    @Override
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDTO)
                .collect(toList());
    }

    @Override
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(id.toString()));
        return postMapper.mapToDTO(post);
    }

    @Override
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new RuntimeException(subredditId.toString()));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDTO).collect(toList());
    }

    @Override
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDTO)
                .collect(toList());
    }

}
