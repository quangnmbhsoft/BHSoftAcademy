package com.example.BHSoft.RedditClone.mapper;

import com.example.BHSoft.RedditClone.dto.PostRequest;
import com.example.BHSoft.RedditClone.dto.PostResponse;
import com.example.BHSoft.RedditClone.model.Post;
import com.example.BHSoft.RedditClone.model.Subreddit;
import com.example.BHSoft.RedditClone.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    PostResponse mapToDTO(Post post);

}
