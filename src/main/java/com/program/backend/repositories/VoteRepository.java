package com.program.backend.repositories;

import com.program.backend.beans.entity.Vote;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<Vote, Long> {
}
