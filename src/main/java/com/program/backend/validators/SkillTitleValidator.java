package com.program.backend.validators;

import com.program.backend.annotations.SkillTitleConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SkillTitleValidator implements ConstraintValidator<SkillTitleConstraint, String> {
    @Override
    public void initialize(SkillTitleConstraint constraintAnnotation){}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        return value != null && !value.isEmpty();
    }
}
