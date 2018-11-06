package com.program.backend.beans.entity;

import com.program.backend.beans.enums.Role;
import com.program.backend.beans.enums.SkillStatus;
import com.program.backend.beans.request.RegisterUserRequest;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Entity
@Indexed
@Data
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Field
    @NonNull
    private String name;

    @Field
    @NonNull
    private String surname;

    @NonNull
    private String password;

    @NonNull
    private String email;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Role role = Role.EMPLOYEE;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<UserSkill> userSkills;

    @ManyToOne
    private Team team;

    public User(RegisterUserRequest registerUserRequest) {
        setName(registerUserRequest.getName());
        setSurname(registerUserRequest.getSurname());
        setEmail(registerUserRequest.getEmail());
        setPassword(registerUserRequest.getPassword());
    }

    public void addUserSkill(UserSkill userSkill) {
        if (userSkills == null)
            userSkills = new LinkedList<>();
        userSkills.add(userSkill);
    }

    public Optional<Team> getTeam() {
        return Optional.ofNullable(team);
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Department getDepartment() {
        if (team != null) {
            return team.getDepartment();
        } else {
            return null;
        }
    }

    public List<UserSkill> getActiveUserSkills() {
        return this.userSkills.stream()
                .filter(userSkill -> userSkill.getSkillStatus() == SkillStatus.ACTIVE)
                .collect(Collectors.toList());
    }
}
