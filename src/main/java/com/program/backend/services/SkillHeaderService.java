package com.program.backend.services;

import com.program.backend.beans.entity.SkillHeader;
import com.program.backend.beans.response.SkillHeaderResponse;
import com.program.backend.repositories.SkillHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SkillHeaderService {

    @Autowired
    private SkillHeaderRepository skillHeaderRepository;

    public SkillHeader getSkillHeaderByTitle(String title) {
        SkillHeader skillHeader = skillHeaderRepository.findByTitle(title);
        if (skillHeader == null)
            throw new RuntimeException();
        return skillHeader;
    }

    public List<SkillHeaderResponse> getAllSkillHeaderTitles() {
        List<SkillHeaderResponse> list = new LinkedList<>();
        getAllSkillHeaders().iterator().forEachRemaining(skillHeader -> list.add(new SkillHeaderResponse(skillHeader)));
        return list;
    }

    public Iterable<SkillHeader> getAllSkillHeaders() {
        return skillHeaderRepository.findAll();
    }
}
