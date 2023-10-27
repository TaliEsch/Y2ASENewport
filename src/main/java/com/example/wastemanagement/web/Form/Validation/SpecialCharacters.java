package com.example.wastemanagement.web.Form.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = SpecialCharactersValidator.class)
// this annotation can be used on fields and parameters
@Target({FIELD, PARAMETER})
// this annotation is available at runtime
@Retention(RUNTIME)
public @interface SpecialCharacters {
    String message() default "Please make sure you do not use special characters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
