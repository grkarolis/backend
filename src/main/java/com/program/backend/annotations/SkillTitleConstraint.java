package com.program.backend.annotations;

import com.program.backend.validators.SkillTitleValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SkillTitleValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SkillTitleConstraint {
}
