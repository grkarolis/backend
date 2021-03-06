package com.program.backend.beans.entity;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"teams"})
@ToString(exclude = {"teams"})
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @NonNull
    protected String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Team> teams;

    @ManyToOne
    @NonNull
    private Division division;

    public void addTeam(Team team) {
        if (teams == null)
            this.teams = new LinkedList<>();
        this.teams.add(team);
    }
}
