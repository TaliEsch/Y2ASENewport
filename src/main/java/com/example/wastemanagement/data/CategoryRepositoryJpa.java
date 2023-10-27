package com.example.wastemanagement.data;

import com.example.wastemanagement.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CategoryRepositoryJpa extends JpaRepository<Category, Integer> {
    // jpa automatically implements this method
    List<Category> findAll();
    // finds the categoryName by categoryID
    @Query("SELECT c.categoryId FROM Category c WHERE c.categoryName = ?1")
    Integer findIdByCategoryName(String categoryName);

    @Query("SELECT c.categoryName FROM Category c")
    ArrayList<String> findAllCategoryName();

}
