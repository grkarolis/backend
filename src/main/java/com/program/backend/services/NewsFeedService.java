package com.program.backend.services;

import com.google.common.collect.Lists;
import com.program.backend.beans.entity.NewsFeed;
import com.program.backend.beans.entity.Skill;
import com.program.backend.beans.entity.SkillLevel;
import com.program.backend.beans.enums.NewsFeedType;
import com.program.backend.beans.response.NewsFeedResponse;
import com.program.backend.beans.response.user.UserResponse;
import com.program.backend.repositories.NewsFeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsFeedService {

    @Autowired
    private NewsFeedRepository newsFeedRepository;

    @Autowired
    private UserService userService;

    public List<NewsFeed> getAllNewsFedd() {
        return Lists.newArrayList(newsFeedRepository.findAll(new Sort(Sort.Direction.DESC, "creationTime")));
    }

    public List<NewsFeed> getNewsFeedPage(int pageNumber) {
        return Lists.newArrayList(newsFeedRepository.findAll(new Pageable() {
            @Override
            public int getPageNumber() {
                return pageNumber;
            }

            @Override
            public int getPageSize() {
                return 15;
            }

            @Override
            public long getOffset() {
                return pageNumber * getPageSize();
            }

            @Override
            public Sort getSort() {
                return new Sort(Sort.Direction.DESC, "creationTime");
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        }));
    }

    public List<NewsFeedResponse> getNewsFeedResponse(List<NewsFeed> allNewsFeed) {
        return allNewsFeed.stream().map(newsFeed -> {
           return new NewsFeedResponse(newsFeed, getUserInfo(newsFeed.getUserId()));
        }).collect(Collectors.toList());
    }

    public UserResponse getUserInfo(Long userId) {
        return new UserResponse(userService.getUserById(userId));
    }

    public void addNewUserFeed(Long userId) {
        newsFeedRepository.save(new NewsFeed(NewsFeedType.NEW_USER, userId));
    }

    public void addSkillLevelRaisedFeed(Long userId, SkillLevel skillLevel, Skill skill) {
        newsFeedRepository.save(new NewsFeed(NewsFeedType.SKILL_LEVEL_RAISED, userId, skillLevel, skill));
    }
}
