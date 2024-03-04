package com.example.BHSoft.RedditClone.repository;

import com.example.BHSoft.RedditClone.model.Post;
import com.example.BHSoft.RedditClone.model.User;
import com.example.BHSoft.RedditClone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
