package com.example.BHSoft.RedditClone.service;

import com.example.BHSoft.RedditClone.dto.VoteDTO;
import com.example.BHSoft.RedditClone.model.Post;
import com.example.BHSoft.RedditClone.model.Vote;
import com.example.BHSoft.RedditClone.repository.PostRepository;
import com.example.BHSoft.RedditClone.repository.VoteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.BHSoft.RedditClone.model.VoteType.UPVOTE;

@Service
@AllArgsConstructor
@Slf4j
public class VoteServiceImpl implements VoteService{
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private AuthService authService;

    @Override
    public void vote(VoteDTO voteDTO) {
        Post post = postRepository.findById(voteDTO.getPostId())
                .orElseThrow(() -> new RuntimeException("Post Not Found with ID - " + voteDTO.getPostId()));
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        if (voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get().getVoteType()
                        .equals(voteDTO.getVoteType())) {
            throw new RuntimeException("You have already "
                    + voteDTO.getVoteType() + "'d for this post");
        }
        if (UPVOTE.equals(voteDTO.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }
        voteRepository.save(mapToVote(voteDTO, post));
        postRepository.save(post);
    }

    private Vote mapToVote(VoteDTO voteDTO, Post post) {
        return Vote.builder()
                .voteType(voteDTO.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
}
