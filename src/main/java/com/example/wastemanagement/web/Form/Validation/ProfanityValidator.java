package com.example.wastemanagement.web.Form.Validation;

import com.example.wastemanagement.service.GlobalValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ProfanityValidator implements ConstraintValidator<Profanity, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        GlobalValidation global = new GlobalValidation();
        return global.Validate(value);
    }
}
