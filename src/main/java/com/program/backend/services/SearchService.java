package com.program.backend.services;

import com.program.backend.beans.entity.Skill;
import com.program.backend.beans.entity.User;
import com.program.backend.beans.entity.UserSkill;
import com.program.backend.beans.response.user.UserResponse;
import com.program.backend.repositories.search.UserSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSearchRepository userSearchRepository;

    @Autowired
    private UserSkillService userSkillService;


    public List<UserResponse> searchColleagues(Long userId, String query) {
        User currentUser = userService.getUserById(userId);

        return this.searchUsers(query).stream().filter(user -> !currentUser.getId().equals(user.getId()))
                .map(u -> userService.getUserResponseBasedOnDepartment(currentUser, u)).collect(Collectors.toList());
    }

    public List<User> searchUsers(String query) {
        List<User> userList = new LinkedList<>(search(query));
        userList.sort(Comparator.comparing(User::getName));
        return userList;
    }

    private Set<User> getUsersFromSkills(List<Skill> skills) {
        Set<User> users = new HashSet<>();
        for (Skill skill : skills) {
            for (UserSkill userskill : userSkillService.getUserSkillsBySkill(skill)) {
                users.add(userskill.getUser());
            }
        }

        return users;
    }

    public Set<User> getResultsBySkillProperties(String keyword) {
        return getUsersFromSkills(userSearchRepository.resultsBySkillProperties(keyword));
    }

    public Set<User> search(String searchText) {
        Set<User> userResults = new HashSet<>();
        String[] keywords = userSearchRepository.processQuery(searchText);

        for (String keyword : keywords) {
            userResults.addAll(userSearchRepository.resultsByName(keyword));
            userResults.addAll(userSearchRepository.resultsBySurname(keyword));
            userResults.addAll(getResultsBySkillProperties(keyword));
        }
        return userResults;
    }
}
