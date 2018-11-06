package com.program.backend.beans.response.department;

import com.program.backend.beans.entity.Department;
import com.program.backend.beans.response.Response;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class DepartmentResponse extends Response {

    @NonNull
    private Long id;

    @NonNull
    private String name;

    public DepartmentResponse(@NonNull Department department) {
        setId(department.getId());
        setName(department.getName());
    }
}
