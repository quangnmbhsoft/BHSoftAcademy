package com.example.BHSoft.RedditClone.mapper;

import com.example.BHSoft.RedditClone.dto.SubredditDTO;
import com.example.BHSoft.RedditClone.model.Post;
import com.example.BHSoft.RedditClone.model.Subreddit;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface SubredditMapper {
    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
    SubredditDTO mapSubredditToDTO(Subreddit subreddit);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Subreddit mapDTOToSubreddit(SubredditDTO subredditDTO);

}
