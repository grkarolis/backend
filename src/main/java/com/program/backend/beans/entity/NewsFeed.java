package com.program.backend.beans.entity;

import com.program.backend.beans.enums.NewsFeedType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class NewsFeed {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @NonNull
    protected NewsFeedType type;

    @NonNull
    private Long userId;

    @ManyToOne
    private SkillLevel skillLevel;

    @ManyToOne
    private Skill skill;

    @CreationTimestamp
    private LocalDateTime creationTime;

    public NewsFeed(NewsFeedType newsFeedType, Long userId, SkillLevel skillLevel, Skill skill) {
        this.type = newsFeedType;
        this.userId = userId;
        this.skillLevel = skillLevel;
        this.skill = skill;
    }

    public NewsFeed(NewsFeedType newsFeedType, Long userId) {
        this.type = newsFeedType;
        this.userId = userId;
    }
}
