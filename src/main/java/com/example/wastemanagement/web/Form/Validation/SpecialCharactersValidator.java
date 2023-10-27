package com.example.wastemanagement.web.Form.Validation;

import com.example.wastemanagement.service.GlobalValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SpecialCharactersValidator implements ConstraintValidator<SpecialCharacters,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        GlobalValidation global = new GlobalValidation();
        return global.ValidateSpecialCharacters(value);
    }
}
