package com.program.backend.repositories;

import com.program.backend.beans.entity.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {

    Department findOneById(Long id);
}
