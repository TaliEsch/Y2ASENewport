package com.example.wastemanagement.web.Form.Validation;

import com.example.wastemanagement.data.CategoryRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;

@Service
public class CategoryValidator implements ConstraintValidator<Category, String[]> {
    private CategoryRepositoryJpa jpa;
//    @Autowired
    public CategoryValidator(CategoryRepositoryJpa jpa) {
        this.jpa = jpa;
    }

    @Override
    public void  initialize(Category  constraintAnnotation) {

    }

    public boolean isValid(String[] categoryList, ConstraintValidatorContext constraintValidatorContext) {
        // gets all categories from database, and then retrieve just the CategoryNames into a list
        ArrayList<String> categories = jpa.findAllCategoryName();
        for (String category : categoryList) {
            if (!categories.contains(category)) {
                return false;
            }
        }
        return true;
    }
}
