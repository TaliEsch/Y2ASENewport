package com.example.wastemanagement.web.Form.Validation;

import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageResponse;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AddressValidator implements ConstraintValidator<Address,String> {
    // format: 123 Street Name, City
    private final static String key = "2a84b10eeb0e4a1fb2762de156c8ba9d";
    private final JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder(key);
    @Override
    public boolean isValid(String address, ConstraintValidatorContext constraintValidatorContext) {
        if(address.equals("") || address == null || address == " ") {
            return false;
        }
        // checks if the postcode actually exists
        JOpenCageForwardRequest request = new JOpenCageForwardRequest(address);
        JOpenCageResponse response = jOpenCageGeocoder.forward(request);
        return response.getResults().size() > 0;
    }
}
