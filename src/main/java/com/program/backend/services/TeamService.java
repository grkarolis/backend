package com.program.backend.services;

import com.program.backend.beans.entity.*;
import com.program.backend.beans.enums.Role;
import com.program.backend.beans.request.AddTeamRequest;
import com.program.backend.beans.request.UpdateTeamRequest;
import com.program.backend.beans.response.SkillEntityResponse;
import com.program.backend.beans.response.SkillTemplateResponse;
import com.program.backend.beans.response.team.ColleagueTeamOverviewWithUsersResponse;
import com.program.backend.beans.response.team.NonColleagueTeamOverviewWithUsersResponse;
import com.program.backend.beans.response.team.TeamWithUsersResponse;
import com.program.backend.events.TeamAddedEvent;
import com.program.backend.events.TeamUpdatedEvent;
import com.program.backend.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ValueStreamService valueStreamService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private SkillTemplateService skillTemplateService;

    @Autowired
    private TeamSkillService teamSkillService;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public Iterable<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public List<TeamWithUsersResponse> getAllTeamOverviewResponses() {
        List<TeamWithUsersResponse> teamWithUsersResponses = new LinkedList<>();

        for (Team team : teamRepository.findAll()) {
            teamWithUsersResponses.add(new ColleagueTeamOverviewWithUsersResponse(team,
                    userService.getColleagueResponsesFromUsers(team.getUsers()),
                    getTeamSkillTemplateResponseList(team)));
        }
        return  teamWithUsersResponses;
    }

    public TeamWithUsersResponse updateSelectedTeam(User user, Long id, UpdateTeamRequest updateTeamRequest) {
        if (updateTeamRequest.getName().isEmpty())
            throw new RuntimeException("Team name is empty");

        Team selectedTeam = getTeamById(id);
        if (user.getRole() == Role.MANAGER) {
            return updateTeamAsManager(selectedTeam, updateTeamRequest);
        } else throw new RuntimeException("you have no rights to edit the team");
    }

    public Team getTeamById(Long id) {
        Team team = teamRepository.findTeamById(id);
        if (team == null)
            throw new RuntimeException("team not found");
        return team;
    }

    public TeamWithUsersResponse getTeamOverview(Long teamId, Long currentUserId) {
        User user = userService.getUserById(currentUserId);
        Team currentTeam = getTeamById(teamId);
        List<User> userList = currentTeam.getUsers();

        userList.sort(Comparator.comparing(User::getName));

        if (user.getTeam().isPresent() && user.getTeam().get().getDepartment().equals(currentTeam.getDepartment())) {
            return new ColleagueTeamOverviewWithUsersResponse(currentTeam,
                    userService.getColleagueResponsesFromUsers(currentTeam.getUsers()),
                    getTeamSkillTemplateResponseList(currentTeam));
        } else {
            return new NonColleagueTeamOverviewWithUsersResponse(currentTeam,
                    userService.getNonColleagueResponsesFromUsers(currentTeam.getUsers()),
                    getTeamSkillTemplateResponseList(currentTeam));
        }
    }

    public TeamWithUsersResponse getMyTeam(Long currentUserId) {
        User user = userService.getUserById(currentUserId);
        Team team = user.getTeam().orElseThrow(RuntimeException::new);
        List<User> userList = team.getUsers();

        return new ColleagueTeamOverviewWithUsersResponse(team,
                userService.getColleagueResponsesFromUsers(userList),
                getTeamSkillTemplateResponseList(team));
    }

    public TeamWithUsersResponse addTeam(AddTeamRequest addTeamRequest) {
        if (addTeamRequest.getName().isEmpty()) {
            throw new RuntimeException("team name is empty");
        } else if (teamRepository.findByName(addTeamRequest.getName()) != null) {
            throw new RuntimeException("team name already exists");
        }

        Team team = new Team(addTeamRequest.getName(), departmentService.getDepartmentById(addTeamRequest.getDepartmentId()));

        teamRepository.save(team);

        if (addTeamRequest.getUserIds() != null) {
            userService.updateOrCreateUserTeam(team, userService.getUsersByIds(addTeamRequest.getUserIds()));
        }

        if (addTeamRequest.getStreamId() != null) {
            team.setValueStream(valueStreamService.getValueStreamById(addTeamRequest.getStreamId()));
        }


        final List<Long> ids = new LinkedList<>();
        team.getUsers().forEach(user -> user.getUserSkills().forEach(userSkill -> ids.add(userSkill.getSkill().getId())));
        addTeamRequest.setSkillIds(ids.stream().distinct().collect(Collectors.toList()));

        if (addTeamRequest.getSkillIds() != null) {
            team.setSkillTemplate(skillTemplateService.createOrUpdateSkillTemplate(team, skillService.getSkillsByIds(addTeamRequest.getSkillIds())));
        }

        applicationEventPublisher.publishEvent(new TeamAddedEvent(team));

        return new TeamWithUsersResponse(teamRepository.save(team),
                userService.getColleagueResponsesFromUsers(userService.getUsersByIds(addTeamRequest.getUserIds())),
                getTeamSkillTemplateResponseList(team));
    }

    private TeamWithUsersResponse updateTeamAsManager(Team team, UpdateTeamRequest updateTeamRequest) {
        if (getTeamByName(updateTeamRequest.getName()) != null && !team.equals(getTeamByName(updateTeamRequest.getName()))) {
            throw new RuntimeException("team name already exists");
        }

        userService.updateOrCreateUserTeam(team, userService.getUsersByIds(updateTeamRequest.getUserIds()));

        team.setDepartment(departmentService.getDepartmentById(updateTeamRequest.getDepartmentId()));
        team.setName(updateTeamRequest.getName());
        team.setSkillTemplate(skillTemplateService.createOrUpdateSkillTemplate(team,
                skillService.getSkillsByIds(updateTeamRequest.getSkillIds())));

        if (updateTeamRequest.getStreamId() != null) {
            team.setValueStream(valueStreamService.getValueStreamById(updateTeamRequest.getStreamId()));
        }

        applicationEventPublisher.publishEvent(new TeamUpdatedEvent(team));

        return new TeamWithUsersResponse(teamRepository.save(team),
                userService.getColleagueResponsesFromUsers(userService.getUsersByIds(updateTeamRequest.getUserIds())),
                getTeamSkillTemplateResponseList(team));
    }

    public Set<SkillTemplateResponse> getTeamSkillTemplateResponseList(Team team) {
        Optional<SkillTemplate> skillTemplateOptional = skillTemplateService.getSkillTemplateByTeamId(team.getId());

        TreeSet<SkillTemplateResponse> skillTemplateResponses = new TreeSet<>();

        if (skillTemplateOptional.isPresent()) {
            for (Skill skill : skillTemplateOptional.get().getSkills()) {
                TeamSkill teamSkill = teamSkillService.getTeamSkillByTeamAndSkill(team, skill);
                SkillTemplateResponse skillTemplateResponse = new SkillTemplateResponse(new SkillEntityResponse(skill),
                        teamSkill.getSkillCount(), teamSkill.getSkillLevelAverage());

                skillTemplateResponses.add(skillTemplateResponse);
            }
        }
        return skillTemplateResponses;
    }

    public Team getTeamByName(String name) {
        return teamRepository.findByName(name);
    }

    public void updateTeams(Set<Team> oldTeams) {
        oldTeams.forEach(oldTeam ->
                applicationEventPublisher.publishEvent(new TeamUpdatedEvent(oldTeam)));
    }

    public Set<Team> getUsersTeams(List<User> newUsers) {
        Set<Team> teams = new HashSet<>();
        newUsers.stream()
                .filter(user -> user.getTeam().isPresent())
                .forEach(user -> teams.add(user.getTeam().get()));
        return teams;
    }
}
