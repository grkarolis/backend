package com.program.backend.beans.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class SkillLevel implements Comparable<SkillLevel> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private Long level;

    @NonNull
    private String title;

    @NonNull
    private String description;

    @Override
    public int compareTo(SkillLevel o) {
        return this.getLevel().compareTo(o.getLevel());
    }
}
