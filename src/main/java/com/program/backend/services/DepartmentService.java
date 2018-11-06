package com.program.backend.services;

import com.program.backend.beans.entity.*;
import com.program.backend.beans.response.SkillEntityResponse;
import com.program.backend.beans.response.SkillTemplateResponse;
import com.program.backend.beans.response.department.DepartmentEntityResponse;
import com.program.backend.beans.response.department.DepartmentOverviewResponse;
import com.program.backend.beans.response.department.DepartmentResponse;
import com.program.backend.beans.response.user.UserResponse;
import com.program.backend.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TeamSkillService teamSkillService;

    @Autowired
    private UserService userService;

    public Department getDepartmentById(@NotNull Long id) {
        Department department = departmentRepository.findOneById(id);
        if (department == null)
            throw new RuntimeException("department not found");
        return department;
    }

    public DepartmentOverviewResponse getDepartmentOverviewByDepartmentIdAndUser(@NotNull Long id, @NotNull User user) {
        Department department = this.getDepartmentById(id);

        Set<UserResponse> userResponses;

        if (user.getTeam().isPresent() && user.getDepartment().equals(department))
            userResponses = userService.getColleagueResponsesFromDepartment(department);
        else
            userResponses = userService.getNonColleagueResponsesFromDepartment(department);

        return new DepartmentOverviewResponse(new DepartmentResponse(department),
                userResponses, new TreeSet<>(this.aggregateDepartmentSkillTemplateResponsesFromTeams(department.getTeams())));
    }

    public Iterable<DepartmentEntityResponse> getAllDepartmentEntityResponseList() {
        return this.getAllDepartments().stream().map(DepartmentEntityResponse::new).collect(toList());
    }

    public Collection<SkillTemplateResponse> aggregateDepartmentSkillTemplateResponsesFromTeams(List<Team> departmentTeams) {
        return departmentTeams.stream()
                .map(team -> team.getSkillTemplate().getSkills().stream().map(skill -> getSkillTemplateResponse(team, skill)))
                .flatMap(Function.identity())
                .collect(Collectors.toMap(SkillTemplateResponse::getSkill, Function.identity(), this::getMergedResponse))
                .values();
    }

    private SkillTemplateResponse getMergedResponse(SkillTemplateResponse firstResponse, SkillTemplateResponse secondResponse) {
        return new SkillTemplateResponse(firstResponse.getSkill(), getUserCount(firstResponse, secondResponse), getAverageLevel(firstResponse, secondResponse));
    }

    private SkillTemplateResponse getSkillTemplateResponse(Team team, Skill skill) {
        return new SkillTemplateResponse(new SkillEntityResponse(skill),
                teamSkillService.getTeamSkillCount(team, skill),
                teamSkillService.getTeamAverageSkillLevel(team, skill));
    }

    private int getUserCount(SkillTemplateResponse firstResponse, SkillTemplateResponse secondResponse) {
        return firstResponse.getUserCounter() + secondResponse.getUserCounter();
    }

    private double getAverageLevel(SkillTemplateResponse firstResponse, SkillTemplateResponse secondResponse) {
        return ((firstResponse.getUserCounter() * firstResponse.getAverageLevel()) +
                (secondResponse.getUserCounter() * secondResponse.getAverageLevel())) / getUserCount(firstResponse, secondResponse);
    }

    private List<Department> getAllDepartments() {
        return (List<Department>) departmentRepository.findAll();
    }

}