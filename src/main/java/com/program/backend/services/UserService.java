package com.program.backend.services;

import com.program.backend.beans.entity.Department;
import com.program.backend.beans.entity.Team;
import com.program.backend.beans.entity.User;
import com.program.backend.beans.enums.Role;
import com.program.backend.beans.request.AssignTeamRequest;
import com.program.backend.beans.response.user.UserResponse;
import com.program.backend.beans.response.user.UserWithSkillResponse;
import com.program.backend.exceptions.user.UserNotFoundException;
import com.program.backend.repositories.UserRepository;
import com.program.backend.repositories.search.UserSearchRepository;
import com.program.backend.services.UserSkillService;
import com.program.backend.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserSearchRepository userSearchRepository;

    @Autowired
    private UserSkillService userSkillService;

    @Autowired
    private TeamService teamService;

    @Autowired
    public UserService(UserRepository userRepository, UserSearchRepository userSearchRepository) {
        this.userRepository = userRepository;
        this.userSearchRepository = userSearchRepository;
    }

    public User getUserById(Long id) throws UserNotFoundException {
        User user = userRepository.findOneById(id);
        if (user == null)
            throw new UserNotFoundException();
        return user;
    }

    public List<User> getUsersByIds(List<Long> ids) {
        assert ids != null;

        List<User> users = new LinkedList<>();
        for (Long id : ids)
            users.add(getUserById(id));
        return users;
    }

    public UserResponse getMyProfile(Long userId) {
        User currentUser = getUserById(userId);
        return new UserWithSkillResponse(currentUser,userSkillService.getProfileUserSkills(currentUser.getActiveUserSkills()));
    }

    public UserResponse getUserResponseBasedOnDepartment(User currentUser, User requiredUser) {

        if (requiredUser.getDepartment() != null && currentUser.getDepartment() != null)
            if (isUsersInSameDepartment(currentUser, requiredUser))
                return new UserWithSkillResponse(requiredUser, userSkillService.getNormalUserSkillResponseList(requiredUser.getActiveUserSkills()));

        return new UserWithSkillResponse(requiredUser, userSkillService.getNonColleagueUserSkillResponseList(requiredUser.getActiveUserSkills()));
    }

    public User assignTeam(final Long userId, final AssignTeamRequest assignTeamRequest) {
        User user = getUserById(userId);
        user.setTeam(teamService.getTeamById(assignTeamRequest.getTeamId()));
        return userRepository.save(user);
    }

    private boolean isUsersInSameDepartment(User currentUser, User colleague) {
        return currentUser.getDepartment().getId().equals(colleague.getDepartment().getId());
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateOrCreateUserTeam(Team team, List<User> newUsers) {
        if (team.getUsers() != null) {
            team.getUsers().removeAll(newUsers);
            removeAllUsersFromTeam(team);
        }

        Set<Team> oldTeams = teamService.getUsersTeams(newUsers);
        addTeamsToNewUsers(team, newUsers);
        team.setUsers(newUsers);
        teamService.updateTeams(oldTeams);
    }

    private void removeAllUsersFromTeam(Team team) {
        team.getUsers().forEach(user ->
            user.setTeam(null));
        team.getUsers().clear();
        userRepository.saveAll(team.getUsers());
    }

    private void addTeamsToNewUsers(Team team, List<User> newUsers) {
        newUsers.forEach(newUser -> newUser.setTeam(team));
        userRepository.saveAll(newUsers);
    }

    public List<UserResponse> getAllUserResponses() {
        List<User> users =(List<User>) userRepository.findAll();
        return users.stream().map(UserResponse::new).collect(Collectors.toList());
    }

    public List<UserResponse> getAllTeamlessUserResponses() {
        List<User> users =(List<User>) userRepository.findAll();
        return users.stream()
                .filter(u -> !u.getTeam().isPresent())
                .map(UserResponse::new).collect(Collectors.toList());
    }

    public Set<UserResponse> getNonColleagueResponsesFromDepartment(Department department) {
        Set<User> users = new HashSet<>();
        department.getTeams().forEach(team -> users.addAll(team.getUsers()));
        return users.stream()
                .map(user -> new UserWithSkillResponse(user, userSkillService.getNonColleagueUserSkillResponseList(user.getActiveUserSkills())))
                .collect(Collectors.toSet());
    }

    public Set<UserResponse> getColleagueResponsesFromDepartment(Department department) {
        Set<User> users = new HashSet<>();
        department.getTeams().forEach(team -> users.addAll(team.getUsers()));
        return users.stream()
                .map(user -> new UserWithSkillResponse(user, userSkillService.getNormalUserSkillResponseList(user.getActiveUserSkills())))
                .collect(Collectors.toSet());
    }

    public List<UserResponse> getColleagueResponsesFromUsers(List<User> users) {
        if (users == null)
            return new LinkedList<>();
        return users.stream().map(user -> new UserWithSkillResponse(user,
                userSkillService.getNormalUserSkillResponseList(user.getActiveUserSkills()))).collect(Collectors.toList());
    }

    public List<UserResponse> getNonColleagueResponsesFromUsers(List<User> users) {
        if (users == null)
            return new LinkedList<>();
        return users.stream().map(user -> new UserWithSkillResponse(user,
                userSkillService.getNonColleagueUserSkillResponseList(user.getActiveUserSkills()))).collect(Collectors.toList());
    }

}
