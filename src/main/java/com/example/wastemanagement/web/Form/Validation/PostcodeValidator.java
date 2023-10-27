package com.example.wastemanagement.web.Form.Validation;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageResponse;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PostcodeValidator implements ConstraintValidator<Postcode,String> {
    // works with and without spaces
    // format: AB1 2CD
    private final static String key = "2a84b10eeb0e4a1fb2762de156c8ba9d";
    private final JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder(key);
    @Override
    public boolean isValid(String postcode, ConstraintValidatorContext constraintValidatorContext) {
        // checks that is matches the pattern
        if (postcode.matches("[a-zA-Z]{1,2}[0-9]{1,2}[a-zA-Z]?\\s?[0-9][a-zA-Z]{2}")) {
            // checks if the postcode actually exists
            JOpenCageForwardRequest request = new JOpenCageForwardRequest(postcode);
            JOpenCageResponse response = jOpenCageGeocoder.forward(request);
            return response.getResults().size() > 0;
        }
        return false;
    }
}
