package com.program.backend.services;

import com.program.backend.beans.entity.Skill;
import com.program.backend.beans.entity.SkillHeader;
import com.program.backend.beans.request.AddSkillRequest;
import com.program.backend.beans.response.SkillEntityResponse;
import com.program.backend.repositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private SkillHeaderService skillHeaderService;

    public Skill createSkillIfNotExists(AddSkillRequest addSkillRequest) {
        SkillHeader skillHeader = skillHeaderService.getSkillHeaderByTitle(addSkillRequest.getHeader());
        try {
            return getSkillByTitleAndHeader(addSkillRequest.getTitle(), skillHeader);
        } catch (Exception e) {
            Skill skill = new Skill(skillHeader, addSkillRequest.getTitle());
            return skillRepository.save(skill);
        }
    }

    public Skill getSkillByTitleAndHeader(String title, SkillHeader header) {
        Skill skill = skillRepository.findOneByTitleAndSkillHeader(title, header);
        if (skill == null)
            throw new RuntimeException();
        return skill;
    }

    public Iterable<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Iterable<SkillEntityResponse> getSkillEntityResponseList() {
        List<SkillEntityResponse> list = new LinkedList<>();
        for (Skill skill : this.getAllSkills()) {
            list.add(new SkillEntityResponse(skill));
        }
        return list;
    }

    public List<Skill> getSkillsByIds(List<Long> skillsId) {
        assert skillsId != null;

        return skillsId.stream()
                .map(this::getSkillById)
                .collect(Collectors.toList());
    }

    public Skill getSkillById(Long id) {
        Skill skill = skillRepository.findOneById(id);
        if (skill == null)
            throw new RuntimeException();
        return skill;
    }

    public Skill getSkillFromAddSkillRequest(AddSkillRequest addSkillRequest) {
        try {
            if (addSkillRequest.getId() != null)
                return getSkillById(addSkillRequest.getId());
        } catch (Exception e) {

        }
        return createSkillIfNotExists(addSkillRequest);
    }
}
