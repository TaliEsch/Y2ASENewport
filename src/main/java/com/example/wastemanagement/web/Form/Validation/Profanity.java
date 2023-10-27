package com.example.wastemanagement.web.Form.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = ProfanityValidator.class)
// this annotation can be used on fields and parameters
@Target({FIELD, PARAMETER})
// this annotation is available at runtime
@Retention(RUNTIME)
public @interface Profanity {
    String message() default "Please check your language here";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
