package com.program.backend.repositories;

import com.program.backend.beans.entity.Reviewer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewersRepository extends CrudRepository<Reviewer, Long> {

    Reviewer findOneById(Long id);
}
