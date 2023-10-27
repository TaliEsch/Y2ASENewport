package com.example.wastemanagement.web.Form.Validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PriceValidator implements ConstraintValidator<Price,String> {

    @Override
    // maximum digits before decimal point is 8
    public boolean isValid(String price, ConstraintValidatorContext constraintValidatorContext) {
        return price.matches("^[0-9]+(\\.[0-9]{2})?$");
    }
}
