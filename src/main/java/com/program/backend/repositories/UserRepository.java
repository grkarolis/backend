package com.program.backend.repositories;

import com.program.backend.beans.entity.Team;
import com.program.backend.beans.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    User findOneById(Long id);

    Iterable<User> findAllByOrderByNameAscSurnameAsc();

    Iterable<User> findAllByIdIsNotOrderByNameAscSurnameAsc(Long id);

    Iterable<User> findAllByTeam(Team team);
}
