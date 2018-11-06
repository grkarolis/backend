package com.program.backend.services;

import com.program.backend.beans.entity.SkillLevel;
import com.program.backend.beans.response.SkillLevelResponse;
import com.program.backend.repositories.SkillLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Service
public class SkillLevelService {

    public static int DEFAULT_SKILL_LEVEL = 1;

    @Autowired
    private SkillLevelRepository skillLevelRepository;

    public Iterable<SkillLevel> getAll() {
        return skillLevelRepository.findAll();
    }

    public SkillLevel getByLevel(Long level) {
        return skillLevelRepository.findByLevel(level);
    }

    public Iterable<SkillLevel> getAllByLevelGreaterThanOrEqual(Long level) {
        return skillLevelRepository.findAllByLevelGreaterThanEqual(level);
    }

    public SkillLevel getById(Long id) {
        return skillLevelRepository.findOneById(id);
    }

    public SkillLevel getDefault() {
        Long defaultLevelNo = new Long(DEFAULT_SKILL_LEVEL);
        SkillLevel defaultSkillLevel = skillLevelRepository.findByLevel(defaultLevelNo);
        if (defaultSkillLevel == null)
            throw new RuntimeException();
        return defaultSkillLevel;
    }

    public List<SkillLevelResponse> getSkillLevelResponseList() {
        List<SkillLevelResponse> skillLevelResponses = new LinkedList<>();
        for (SkillLevel skillLevel: this.getAll()) {
            skillLevelResponses.add(new SkillLevelResponse(skillLevel));
        }
        skillLevelResponses.sort(Comparator.comparing(SkillLevelResponse::getId));
        return skillLevelResponses;
    }
}
