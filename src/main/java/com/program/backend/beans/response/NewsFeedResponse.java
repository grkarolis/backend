package com.program.backend.beans.response;

import com.program.backend.beans.entity.NewsFeed;
import com.program.backend.beans.entity.Skill;
import com.program.backend.beans.entity.SkillLevel;
import com.program.backend.beans.enums.NewsFeedType;
import com.program.backend.beans.response.user.UserResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class NewsFeedResponse extends Response{

    private Long id;

    private NewsFeedType newsFeedType;

    private UserResponse user;

    private Skill skill;

    private SkillLevel skillLevel;

    private LocalDateTime date;

    public NewsFeedResponse(NewsFeed newsFeed, UserResponse user) {
        setId(newsFeed.getId());
        setNewsFeedType(newsFeed.getType());
        setUser(user);
        setSkill(newsFeed.getSkill());
        setSkillLevel(newsFeed.getSkillLevel());
        setDate(newsFeed.getCreationTime());
    }
}
