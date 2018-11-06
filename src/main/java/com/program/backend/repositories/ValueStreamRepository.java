package com.program.backend.repositories;

import com.program.backend.beans.entity.ValueStream;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValueStreamRepository extends CrudRepository<ValueStream, Long> {

    Optional<ValueStream> findValueStreamById(Long id);
}
