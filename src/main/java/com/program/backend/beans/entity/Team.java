package com.program.backend.beans.entity;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<User> users;

    @ManyToOne
    @NonNull
    private Department department;

    @ManyToOne
    private ValueStream valueStream;

    @OneToOne(mappedBy = "team")
    private SkillTemplate skillTemplate;

    public Optional<ValueStream> getValueStream() {
        return Optional.ofNullable(valueStream);
    }

    public void addUser(User user) {
        if (users == null)
            this.users = new LinkedList<>();
        this.users.add(user);
    }

}
