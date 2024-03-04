package com.example.BHSoft.RedditClone.mapper;

import com.example.BHSoft.RedditClone.dto.CommentDTO;
import com.example.BHSoft.RedditClone.model.Comment;
import com.example.BHSoft.RedditClone.model.Post;
import com.example.BHSoft.RedditClone.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentsDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    Comment map(CommentDTO commentsDto, Post post, User user);

    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
    CommentDTO mapToDto(Comment comment);
}
