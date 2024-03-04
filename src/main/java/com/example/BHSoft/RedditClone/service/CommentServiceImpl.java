package com.example.BHSoft.RedditClone.service;

import com.example.BHSoft.RedditClone.dto.CommentDTO;
import com.example.BHSoft.RedditClone.mapper.CommentMapper;
import com.example.BHSoft.RedditClone.model.Comment;
import com.example.BHSoft.RedditClone.model.NotificationEmail;
import com.example.BHSoft.RedditClone.model.Post;
import com.example.BHSoft.RedditClone.model.User;
import com.example.BHSoft.RedditClone.repository.CommentRepository;
import com.example.BHSoft.RedditClone.repository.PostRepository;
import com.example.BHSoft.RedditClone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    public static final String POST_URL="";
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthServiceImpl authService;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MailContentBuilder mailContentBuilder;
    @Autowired
    private MailService mailService;
    


    @Override
    public void save(CommentDTO commentDTO) {
        Post post = postRepository.findById(commentDTO.getPostId())
                .orElseThrow(() -> new RuntimeException(commentDTO.getPostId().toString()));
        Comment comment = commentMapper.map(commentDTO, post, authService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilder.build(post.getUser().getUsername() + " posted a comment on your post." + POST_URL);
        sendCommentNotification(message, post.getUser());
    }

    @Override
    public List<CommentDTO> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException(postId.toString()));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto).toList();
    }

    @Override
    public List<CommentDTO> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .toList();
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + "Comment on your post", user.getEmail(), message));
    }
}
