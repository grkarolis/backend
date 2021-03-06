package com.program.backend.beans.entity;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"team"})
@ToString(exclude = "team")
public class SkillTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id", unique = true)
    @NonNull
    private Team team;

    @ManyToMany
    @NonNull
    private List<Skill> skills;

    public void addSkill(@NonNull Skill skill) {
        if (skills == null)
            skills = new LinkedList<>();
        skills.add(skill);
    }
}
