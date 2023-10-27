package com.example.wastemanagement.unit.form;

import com.example.wastemanagement.web.Form.BuyRentEmailForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestClass;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CheckBuyRentEmailFormTest {
    private Validator validator;

    /* Initialises the validator before each usage, calls the validatorFactory for a default
    *  validator and sets it to be validator. */
    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // Resets the validator after each usage
    @AfterTestClass
    public void tearDown() {
        validator = null;
    }

    @Test
    public void rejectEmailIfNullTest(){
        // Given the email field is null
        BuyRentEmailForm buyRentEmailForm = new BuyRentEmailForm(1L, "message", null);
        // When the form is validated
        List<ConstraintViolation<BuyRentEmailForm>> violations = new ArrayList<>(validator.validate(buyRentEmailForm));
        // Then the form should not be valid
        assertFalse(violations.isEmpty());
    }

    @Test
    public void rejectEmailIfWrongFormatTest(){
        // Given the email field is in the wrong format
        BuyRentEmailForm buyRentEmailForm = new BuyRentEmailForm(1L, "message", "jeffjeff");
        // When the form is validated
        List<ConstraintViolation<BuyRentEmailForm>> violations = new ArrayList<>(validator.validate(buyRentEmailForm));
        // Then the form should not be valid
        assertFalse(violations.isEmpty());
    }

    @Test
    public void allowEmailIfCorrect(){
        // Given the email field is correct
        BuyRentEmailForm buyRentEmailForm = new BuyRentEmailForm(1L, "message", "email@example.com");
        // When the form is validated
        List<ConstraintViolation<BuyRentEmailForm>> violations = new ArrayList<>(validator.validate(buyRentEmailForm));
        // Then the form should be valid
        assertTrue(violations.isEmpty());
    }

    @Test
    public void rejectMessageIfEmpty(){
        // Given the email field is null
        BuyRentEmailForm buyRentEmailForm = new BuyRentEmailForm(1L, "", "email@example.com");
        // When the form is validated
        List<ConstraintViolation<BuyRentEmailForm>> violations = new ArrayList<>(validator.validate(buyRentEmailForm));
        // Then the form should not be valid
        assertFalse(violations.isEmpty());
    }
}
