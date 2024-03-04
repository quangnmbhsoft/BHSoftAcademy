package com.example.BHSoft.RedditClone.repository;

import com.example.BHSoft.RedditClone.model.Comment;
import com.example.BHSoft.RedditClone.model.Post;
import com.example.BHSoft.RedditClone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
