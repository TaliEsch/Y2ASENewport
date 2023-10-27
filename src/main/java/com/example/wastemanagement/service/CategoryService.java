package com.example.wastemanagement.service;

import com.example.wastemanagement.domain.Rs;
import com.example.wastemanagement.domain.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
     List<Category> getCategories();
     List<Rs> getRsByCategoryName(String categoryName);



}


