package com.example.wastemanagement.web.Form.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

// this uses the AddressValidator class to validate the address
@Constraint(validatedBy = AddressValidator.class)
// this annotation can be used on fields and parameters
@Target({FIELD, PARAMETER})
// this annotation is available at runtime
@Retention(RUNTIME)
public @interface Address {
    String message() default "Invalid address, please enter a valid address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
