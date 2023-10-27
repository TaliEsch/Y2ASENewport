package com.example.wastemanagement.web.Form.Validation;

import com.example.wastemanagement.data.CategoryRepositoryJpa;
import com.example.wastemanagement.data.HubRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class HubSpecialisingValidator implements ConstraintValidator<HubSpecialising, String> {
    private HubRepositoryJpa jpa;
    @Autowired
    public HubSpecialisingValidator(HubRepositoryJpa jpa) {
        this.jpa = jpa;
    }

    @Override
    public boolean isValid(String specialityVar, ConstraintValidatorContext constraintValidatorContext) {
        if(specialityVar.equals("") || specialityVar == null || specialityVar == " ") {
            return false;
        }
        specialityVar = specialityVar.toLowerCase();
        // make specialityVar lowercase
        var speciality = jpa.findAllUniqueWhichR();
        // make it lowercase
        List<String> specialityList = new ArrayList<>();
        for (String s : speciality) {
            specialityList.add(s.toLowerCase());
        }
        specialityList.addAll(List.of("recycling", "composting", "landfill", "reuse", "tip", "repair", "up-cycling"));
        return specialityList.contains(specialityVar);
    }
}
