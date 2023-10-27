package com.example.wastemanagement.unit.form;

import com.example.wastemanagement.web.Form.BlogForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.AfterTestClass;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CheckBlogForm {
    private Validator validator;

    // this is re-instantiate before each method
    // used to signal that the annotated method should be executed before each
    // @Test method in the current test class.
    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterTestClass
    public void tearDown() {
        // close the validator factory
        validator = null;
    }

    @Test
    public void shouldGiveErrorWhenTitleIsBlank() {
        BlogForm bf = new BlogForm("", "james", "james@gmail.com", "", "", null, "", "descriptiondescriptiondescription");
        List<ConstraintViolation<BlogForm>> violations = new ArrayList<>(validator.validate(bf));
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.get(0).getMessage(), "Please enter a title");
    }

    @Test
    public void shouldGiveNoErrorWhenUserIsBlank() {
        BlogForm bf = new BlogForm("lack of username", "", "james@gmail.com", "jkkkkk", "Clothing", null, "3124256374", "descriptiondescriptiondescription");
        List<ConstraintViolation<BlogForm>> violations = new ArrayList<>(validator.validate(bf));
        assertTrue(violations.isEmpty());
        assertEquals(violations.size(), 0);
    }

    @Test
    public void shouldGiveErrorWhenEmailIsBlank() {
        BlogForm bf = new BlogForm("lack of email", "no email", "", "jkkkkk", "Clothing", null, "3124256374", "descriptiondescriptiondescription");
        List<ConstraintViolation<BlogForm>> violations = new ArrayList<>(validator.validate(bf));
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 1);
        assertEquals(violations.get(0).getMessage(), "must not be empty");
    }

    @Test
    public void shouldGiveNoErrorsWhenAllOptionalFieldAreEmpty() {
        BlogForm bf = new BlogForm("ffsjkldsgjeai", "", "aa@gmail.com", "", "", null, "", "descriptiondescriptiondescription");
        List<ConstraintViolation<BlogForm>> violations = new ArrayList<>(validator.validate(bf));
        assertTrue(violations.isEmpty());
        assertEquals(violations.size(), 0);
    }
    @Test
    public void shouldGiveErrorsWhenAllFieldsAreEmpty() {
        BlogForm bf = new BlogForm("", "", "", "", "", null, "", "");
        List<ConstraintViolation<BlogForm>> violations = new ArrayList<>(validator.validate(bf));
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 3);
    }

    @Test
    public void shouldNotGiveErrorWhenCategoryIsFromDatabase(){
        BlogForm blogForm = new BlogForm("yo","Joe","email@dkh","","Home & Kitchen",
                null,"","Great");
        List<ConstraintViolation<BlogForm>> violations = new ArrayList<>(validator.validate(blogForm));
        assertTrue(violations.isEmpty());
    }

}


