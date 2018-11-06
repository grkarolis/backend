package com.program.backend.beans.entity;


import com.program.backend.beans.enums.SkillStatus;
import lombok.*;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@Indexed
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "userSkillLevels")
@ToString(exclude = "userSkillLevels")
public class UserSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @NonNull
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @NonNull
    private Skill skill;

    @NonNull
    @Enumerated(EnumType.STRING)
    private SkillStatus skillStatus = SkillStatus.ACTIVE;

    @OneToMany(mappedBy = "userSkill", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<UserSkillLevel> userSkillLevels;

    public String getTitle() {
        return skill.getTitle();
    }

    public void addUserSkillLevel(UserSkillLevel userSkillLevel) {
        if (userSkillLevels == null)
            userSkillLevels = new LinkedList<>();
        userSkillLevels.add(userSkillLevel);
    }
}
