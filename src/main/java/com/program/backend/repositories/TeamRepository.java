package com.program.backend.repositories;

import com.program.backend.beans.entity.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

    Team findByName(String name);

    Team findTeamById(Long id);
}
