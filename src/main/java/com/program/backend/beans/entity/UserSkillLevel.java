package com.program.backend.beans.entity;

import com.program.backend.beans.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class UserSkillLevel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @NonNull
    private UserSkill userSkill;

    @ManyToOne
    @NonNull
    private SkillLevel skillLevel;

    private String motivation;

    private Status status = Status.APPROVED;

    @CreationTimestamp
    private Date validFrom;

    private Date validUntil;

    @OneToMany(mappedBy = "userSkillLevel")
    private List<Vote> votes = new LinkedList<>();

    public void setPending() {
        this.status = Status.PENDING;
    }

    public void setApproved() {
        this.status = Status.APPROVED;
    }

    public void setDisapproved() {
        this.status = Status.DISAPPROVED;
    }

    public String getCurrentSkillLevelStatus() {
        return status.toString();
    }
}
