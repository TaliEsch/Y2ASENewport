package com.example.wastemanagement.unit.form;

import com.example.wastemanagement.web.Form.BlogForm;
import com.example.wastemanagement.web.Form.indexForm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class CheckIndexForm {
//    @Id
//    @Column(name = "index_Id")
//    private Long itemId;
//    @NotEmpty
//    private String description;
//    @NotEmpty
//    private byte[] image1;
//    @NotEmpty
//    private byte[] image2;
//    @NotEmpty
//    private byte[] image3;
//    @NotEmpty
//    private byte[] image4;

    private Validator validator;

    // this is re-instantiate before each method
    // used to signal that the annotated method should be executed before each
    // @Test method in the current test class.
    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    public void shouldGiveErrorWhenEverythingIsBlank() {
        indexForm bf = new indexForm("", null, null, null, null);
        List<ConstraintViolation<indexForm>> violations = new ArrayList<>(validator.validate(bf));
        assertFalse(violations.isEmpty());
        assertEquals(violations.size(), 5);
    }
}
