package com.example.wastemanagement.web.Form.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

// It will validate using the regex in PhoneNumberValidator.class
@Constraint(validatedBy = PhoneNumberValidator.class)
// It can be used for fields
@Target({FIELD})
// Will be retained/available during runtime
@Retention(RUNTIME)
public @interface PhoneNumber {

    String message() default "Invalid Phone Number, please enter a valid phone number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
