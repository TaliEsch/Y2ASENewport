package com.example.wastemanagement.service;

import com.example.wastemanagement.data.CategoryRepositoryJpa;
import com.example.wastemanagement.domain.Rs;
import com.example.wastemanagement.domain.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepositoryJpa categoryRepositoryJpa;
    private final RsService rsService;

    public CategoryServiceImpl(CategoryRepositoryJpa categoryRepositoryJpa, RsService rsService) {
        this.categoryRepositoryJpa = categoryRepositoryJpa;
        this.rsService = rsService;
    }

    // get categories from category repository jpa
    @Override
    public List<Category> getCategories() {
        return categoryRepositoryJpa.findAll();
    }

    // Using the foreign key, category id, to get the uses
    @Override
    public List<Rs> getRsByCategoryName(String categoryName) {
        var categoryId =  categoryRepositoryJpa.findIdByCategoryName(categoryName);
        if (categoryId == null) {
            System.out.println("categoryId is null because categoryName "+categoryName+" does not exist");
            return null;
        }
        var applications = rsService.getUsesByCategoryId(categoryId);
        return applications;
    }
}
