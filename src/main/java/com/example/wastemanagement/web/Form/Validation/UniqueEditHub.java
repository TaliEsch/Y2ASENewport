package com.example.wastemanagement.web.Form.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UniqueEditHubValidator.class)
@Target({TYPE, FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface UniqueEditHub {


    String message() default "This Hub already exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
