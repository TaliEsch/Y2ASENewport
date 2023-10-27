package com.example.wastemanagement.unit.form;

import com.example.wastemanagement.data.HubRepositoryJpa;
import com.example.wastemanagement.web.Form.HubForm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CheckHubForm {
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

    // URL - Link
    @Test
    public void shouldGiveErrorWhenLinkIsBlank(){
        // Given the object hubForm is made
        String[] categoriesList= new String[]{"Clothing", "Cleaning"};
        ArrayList<String> categoriesArrayList = new ArrayList<>();
        HubForm hubForm = new HubForm(null,"bla", "", "recycling",categoriesList,
                categoriesArrayList ,"12 high street,newport", "np10 8hl", "01164960780");
        // When all the details are added
        // Then

        // get the field violations only
        ArrayList<ConstraintViolation<HubForm>> violations = new ArrayList<>( validator.validateProperty(hubForm, "link"));
        assertFalse(violations.isEmpty());
        assertEquals(violations.get(0).getPropertyPath().toString(),"link");
    }

    @Test
    public void shouldGiveErrorWhenLinkDoesntMeetURLStandards(){
        // Given the object hubForm is made
        String[] categoriesList= new String[]{"recycling"};
        ArrayList<String> categoriesArrayList = new ArrayList<>();
        HubForm hubForm = new HubForm(null,"yo","www.google.com","recycling",categoriesList, categoriesArrayList,
                "13 high street,Newport","np10 8hl", "01164960780");
        // When all the details are added
        // Then all the violation received
        ArrayList<ConstraintViolation<HubForm>> violations = new ArrayList<>( validator.validateProperty(hubForm, "link"));
        assertFalse(violations.isEmpty());
        assertEquals(violations.get(0).getPropertyPath().toString(),"link");
    }

    @Test
    public void shouldNotGiveErrorOnARealURL(){
        // Given the object hubForm is made
        String[] categoriesList= new String[]{"recycling"};
        ArrayList<String> categoriesArrayList = new ArrayList<>();
        HubForm hubForm = new HubForm(null,"yo","https://www.google.com/","recycling",categoriesList, categoriesArrayList,
                "13 high street,Newport","np10 8hl", "01164960780");
        // When all the details are added
        // Then all the violation received
        ArrayList<ConstraintViolation<HubForm>> violations = new ArrayList<>( validator.validateProperty(hubForm, "link"));
        assertTrue(violations.isEmpty());
    }

    // WhichR
//    @Test
//    public void shouldGiveErrorWhenWhichRIsBlank(){
//        // Given the object hubForm is made
//        String[] categoriesList= new String[]{"recycling"};
//        ArrayList<String> categoriesArrayList = new ArrayList<>();
//        HubForm hubForm = new HubForm(null,"name", "https://www.google.com/", "",categoriesList, categoriesArrayList,
//                "12 high street,newport", "np10 8hl", "01164960780");
//        // When all the details are added
//        // Then all the violation received
//        ArrayList<ConstraintViolation<HubForm>> violations = new ArrayList<>( validator.validateProperty(hubForm, "whichR"));
//        assertFalse(violations.isEmpty());
//        Assertions.assertThat(Objects.equals(violations.get(0).getPropertyPath().toString(), "whichR"));
//    }

//    @Test
//    public void shouldNotGiveErrorWhenWhichR(){
//        // Given the object hubForm is made
//        String[] categoriesList= new String[]{"recycling"};
//        ArrayList<String> categoriesArrayList = new ArrayList<>();
//        HubForm hubForm = new HubForm(null,"name", "https://www.google.com/", "recycling",categoriesList, categoriesArrayList,
//                "12 high street,newport", "np10 8hl", "01164960780");
//        // When all the details are added
//        // Then all the violation received
//        ArrayList<ConstraintViolation<HubForm>> violations = new ArrayList<>( validator.validateProperty(hubForm, "whichR"));
//        assertTrue(violations.isEmpty());
//    }

    // categories
    @Test
    public void shouldGiveErrorWhenCategoriesIsBlank(){
        // Given the object hubForm is made
        String[] categoriesList= new String[]{};
        ArrayList<String> categoriesArrayList = new ArrayList<>();
        HubForm hubForm = new HubForm(null,"name", "https://www.google.com/", "recycling",categoriesList, categoriesArrayList,
                "12 high street,newport", "np10 8hl", "01164960780");
        // When all the details are added
        // Then all the violation received
        ArrayList<ConstraintViolation<HubForm>> violations = new ArrayList<>( validator.validateProperty(hubForm, "categoriesList"));
        assertFalse(violations.isEmpty());
        assertEquals(violations.get(0).getPropertyPath().toString(),"categoriesList");
    }

    @Test
    public void shouldNotGiveErrorWhenMultipleCategories(){
        // Given the object hubForm is made
        String[] categoriesList= new String[]{"recycling", "tip"};
        ArrayList<String> categoriesArrayList = new ArrayList<>();
        HubForm hubForm = new HubForm(null,"name", "https://www.google.com/", "recycling",categoriesList, categoriesArrayList,
                "12 high street,newport", "np10 8hl", "01164960780");
        // When all the details are added
        // Then all the violation received
        ArrayList<ConstraintViolation<HubForm>> violations = new ArrayList<>( validator.validateProperty(hubForm, "categoriesList"));
        assertTrue(violations.isEmpty());
    }

    // Address
    @Test
    public void shouldGiveErrorWhenAddressIsBlank(){
        // Given the object hubForm is made
        String[] categoriesList= new String[]{"recycling"};
        ArrayList<String> categoriesArrayList = new ArrayList<>();
        HubForm hubForm = new HubForm(null,"yo","https://www.google.com/","recycling", categoriesList, categoriesArrayList,
                "","np10 8hl", "01164960780");
        // When all the details are added
        // Then all the violation received
        ArrayList<ConstraintViolation<HubForm>> violations = new ArrayList<>( validator.validateProperty(hubForm, "address"));
        assertFalse(violations.isEmpty());
        // test that either error states
        assertEquals(violations.get(0).getPropertyPath().toString(),"address");
    }

    @Test
    public void shouldAllowWhenAddressDoesMeetTheAddressStandards(){
        // Given the object hubForm is made
        String[] categoriesList= new String[]{"recycling"};
        ArrayList<String> categoriesArrayList = new ArrayList<>();
        HubForm hubForm = new HubForm(null,"yo","https://www.google.com/","recycling",categoriesList, categoriesArrayList,
                "13High street Newport","np10 8hl", "01164960780");
        // When all the details are added
        // Then all the violation received
        ArrayList<ConstraintViolation<HubForm>> violations = new ArrayList<>( validator.validateProperty(hubForm, "address"));
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldNotGiveErrorOnARealAddress(){
        // Given the object hubForm is made
        // space is not optional
        String[] categoriesList= new String[]{"recycling"};
        ArrayList<String> categoriesArrayList = new ArrayList<>();
        HubForm hubForm = new HubForm(null,"yo","https://www.google.com/","recycling",categoriesList, categoriesArrayList,
                "13high street,Newport","np10 8hl", "01164960780");
        // When all the details are added
        // Then all the violation received
        ArrayList<ConstraintViolation<HubForm>> violations = new ArrayList<>( validator.validateProperty(hubForm, "address"));
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldNotGiveErrorOnARealAddressWithoutNumber(){
        // Given the object hubForm is made
        String[] categoriesList= new String[]{"recycling"};
        ArrayList<String> categoriesArrayList = new ArrayList<>();
        HubForm hubForm = new HubForm(null,"yo","https://www.google.com/","recycling",categoriesList, categoriesArrayList,
                "high street,Newport","np10 8hl", "01164960780");
        // When all the details are added
        // Then all the violation received
        ArrayList<ConstraintViolation<HubForm>> violations = new ArrayList<>( validator.validateProperty(hubForm, "address"));
        assertTrue(violations.isEmpty());
    }

    // Postcode
    @Test
    public void shouldGiveErrorWhenPostcodeIsBlank(){
        // Given the object hubForm is made
        String[] categoriesList= new String[]{"recycling"};
        ArrayList<String> categoriesArrayList = new ArrayList<>();
        HubForm hubForm = new HubForm(null,"yo","https://www.google.com/","recycling",categoriesList, categoriesArrayList,
                "high street,Newport","", "01164960780");
        // When all the details are added
        // Then all the violation received
        ArrayList<ConstraintViolation<HubForm>> violations = new ArrayList<>( validator.validateProperty(hubForm, "postcode"));
        assertFalse(violations.isEmpty());
        // test that either error states
        assertEquals(violations.get(0).getPropertyPath().toString(),"postcode");
    }

    @Test
    public void shouldGiveErrorWhenPostCodeDoesntMeetTheAddressStandards(){
        // Given the object hubForm is made
        String[] categoriesList= new String[]{"recycling"};
        ArrayList<String> categoriesArrayList = new ArrayList<>();
        HubForm hubForm = new HubForm(null,"yo","https://www.google.com/","recycling",categoriesList, categoriesArrayList,
                "13 High street,Newport","npgh shl", "01164960780");
        // When all the details are added
        // Then all the violation received
        ArrayList<ConstraintViolation<HubForm>> violations = new ArrayList<>( validator.validateProperty(hubForm, "postcode"));
        assertFalse(violations.isEmpty());
        assertEquals(violations.get(0).getPropertyPath().toString(),"postcode");
    }

    @Test
    public void shouldNotGiveErrorOnARealPostcode(){
        // Given the object hubForm is made
        // space is not optional
        String[] categoriesList= new String[]{"recycling"};
        ArrayList<String> categoriesArrayList = new ArrayList<>();
        HubForm hubForm = new HubForm(null,"yo","https://www.google.com/","recycling",
                categoriesList, categoriesArrayList,"13high street,Newport","np10 8hl", "01164960780");
        // When all the details are added
        // Then all the violation received
        ArrayList<ConstraintViolation<HubForm>> violations = new ArrayList<>( validator.validateProperty(hubForm, "postcode"));
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldNotGiveErrorOnARealPostcodeWithoutSpace(){
        // Given the object hubForm is made
        String[] categoriesList= new String[]{"recycling"};
        ArrayList<String> categoriesArrayList = new ArrayList<>();
        HubForm hubForm = new HubForm(null,"yo","https://www.google.com/","recycling",categoriesList,  categoriesArrayList,
                "high street,Newport","np108hl", "01164960780");
        // When all the details are added
        // Then all the violation received
        ArrayList<ConstraintViolation<HubForm>> violations = new ArrayList<>( validator.validateProperty(hubForm, "postcode"));
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldGiveErrorWhenPostcodeFollowsStandardsButDoesntExist(){
        // Given the object hubForm is made
        String[] categoriesList= new String[]{"recycling"};
        ArrayList<String> categoriesArrayList = new ArrayList<>();
        HubForm hubForm = new HubForm(null, "yo","https://www.google.com/","recycling",categoriesList,categoriesArrayList,
                "high street,Newport","n89 2hz", "01164960780");
        // When all the details are added
        // Then all the violation received
        ArrayList<ConstraintViolation<HubForm>> violations = new ArrayList<>( validator.validateProperty(hubForm, "postcode"));
        assertFalse(violations.isEmpty());
        assertEquals(violations.get(0).getPropertyPath().toString(),"postcode");
    }

    @Test
    public void shouldRejectInvalidPhoneNumber(){
        // Given I submit a new hub with something that's not a phone number in the phone number space
        String[] categoriesList= new String[]{"recycling"};
        ArrayList<String> categoriesArrayList = new ArrayList<>();
        HubForm hubForm = new HubForm(null,"TestHub", "https://www.ThisIsATest", "recycling", categoriesList,  categoriesArrayList,
                "20 Test Rd, Newport", "TE102ST", "ThisIsNOTAPhoneNumber");
        // When the validator is run
        ArrayList<ConstraintViolation<HubForm>> violations = new ArrayList<>( validator.validateProperty(hubForm, "phoneNumber"));
        // It should return violations
        assertFalse(violations.isEmpty());
        Assertions.assertThat(violations.get(0).getPropertyPath().toString().equals("phoneNumber"));
    }

    @Test
    public void shouldRejectEmptyPhoneNumber(){
        // Given I submit an empty phone number
        String[] categoriesList= new String[]{"recycling"};
        ArrayList<String> categoriesArrayList = new ArrayList<>();
        HubForm hubForm = new HubForm(null,"TestHub", "https://www.ThisIsATest", "recycling", categoriesList, categoriesArrayList,
                "20 Test Rd, Newport", "TE102ST", "");
        // When the validator is run
        ArrayList<ConstraintViolation<HubForm>> violations = new ArrayList<>( validator.validateProperty(hubForm, "phoneNumber"));
        // It should return violations
        assertFalse(violations.isEmpty());
        Assertions.assertThat(violations.get(0).getPropertyPath().toString().equals("phoneNumber") ||
                violations.get(1).getPropertyPath().toString().equals("phoneNumber") );
    }


    @Test
    public void shouldAllowPhoneNumberWithSpaces(){
        // Given I submit a new hub with a phone number that includes spaces
        String[] categoriesList= new String[]{"recycling"};
        ArrayList<String> categoriesArrayList = new ArrayList<>();
        HubForm hubForm = new HubForm(null,"TestHub", "https://www.ThisIsATest", "recycling", categoriesList, categoriesArrayList,
                "20 Test Rd, Newport", "TE102ST", "011 649 60780");
        // When the validator is run
        ArrayList<ConstraintViolation<HubForm>> violations = new ArrayList<>( validator.validateProperty(hubForm, "phoneNumber"));
        // It shouldn't return violations
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldAllowNormalPhoneNumber(){
        // Given I submit a normal looking phone number
        String[] categoriesList= new String[]{"recycling"};
        ArrayList<String> categoriesArrayList = new ArrayList<>();
        HubForm hubForm = new HubForm(null,"TestHub", "https://www.ThisIsATest", "recycling", categoriesList, categoriesArrayList,
                "20 Test Rd, Newport", "TE102ST", "01164960780");
        // When the validator is run
        ArrayList<ConstraintViolation<HubForm>> violations = new ArrayList<>( validator.validateProperty(hubForm, "phoneNumber"));
        // It shouldn't return violations
        assertTrue(violations.isEmpty());
    }
}
