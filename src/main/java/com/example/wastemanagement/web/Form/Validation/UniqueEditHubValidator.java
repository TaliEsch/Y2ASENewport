package com.example.wastemanagement.web.Form.Validation;

import com.example.wastemanagement.data.HubRepositoryJpa;
import com.example.wastemanagement.web.Form.HubForm;
import org.springframework.beans.factory.annotation.Autowired;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEditHubValidator implements ConstraintValidator<UniqueEditHub, HubForm> {
    private final HubRepositoryJpa jpaRepo;
    @Autowired
    public UniqueEditHubValidator(HubRepositoryJpa hubRepositoryJpa) {
        this.jpaRepo = hubRepositoryJpa;
    }

    @Override
    public boolean isValid(HubForm hubForm, ConstraintValidatorContext constraintValidatorContext){

        if(hubForm.getHubId() == null){
            // Copies over validation from UniqueHubValidator, it is necce
            var hub = hubForm.getHubName();
            var hubs = jpaRepo.findAllHubs();
            return !hubs.contains(hub);
        }else{
            var hubId = hubForm.getHubId();
            var hubToEdit = jpaRepo.findHubByHubId(hubId);
            var hubToEditName = hubToEdit.getHubName();
            if(hubToEditName.equals(hubForm.getHubName())){
                return true;
            }else{
                var hub = hubForm.getHubName();
                var hubs = jpaRepo.findAllHubs();
                return !hubs.contains(hub);
            }
        }
    }
}
