package com.example.wastemanagement.unit.service;

import com.example.wastemanagement.data.CategoryRepositoryJpa;
import com.example.wastemanagement.domain.Category;
import com.example.wastemanagement.domain.Rs;
import com.example.wastemanagement.service.CategoryService;
import com.example.wastemanagement.service.CategoryServiceImpl;
import com.example.wastemanagement.service.RsService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GetCategories {
    private CategoryService categoryService;
    private RsService rsService;
    private CategoryRepositoryJpa categoryRepository;

    // Testing the CategoryService, returns the correct amount of categories
    @Test
    public void shouldGetSixCategories(){

        // given
        Category c1 = new Category(1,"Metal");
        Category c2 = new Category(2,"Glass");
        Category c3 = new Category(3,"Plastic");
        Category c4 = new Category(4,"Paper");
        Category c5 = new Category(5,"Textiles");
        Category c6 = new Category(6,"Food");

        categoryRepository = mock(CategoryRepositoryJpa.class);
        given(categoryRepository.findAll()).willReturn(List.of(c1, c2, c3, c4, c5, c6));

        Rs u1 = new Rs(1, "reuse", "make something new");
        rsService = mock(RsService.class);
        given(rsService.getUsesByCategoryId(1)).willReturn(List.of(u1));

        // Testing that the category service returns the correct number of categories
        categoryService = new CategoryServiceImpl(categoryRepository, rsService);

        // when
        var categories = categoryService.getCategories();

        // then
        assertEquals(6, categories.size());
    }

    @Test
    public void shouldGetThreeCategories() {

        // given
        Category c1 = new Category(1,"Metal");
        Category c2 = new Category(2,"Glass");
        Category c3 = new Category(3,"Plastic");

        categoryRepository = mock(CategoryRepositoryJpa.class);
        given(categoryRepository.findAll()).willReturn(List.of(c1, c2, c3));

        Rs u1 = new Rs(1, "reuse", "make something new");
        rsService = mock(RsService.class);
        given(rsService.getUsesByCategoryId(1)).willReturn(List.of(u1));

        // Testing that the category service returns the correct number of categories
        categoryService = new CategoryServiceImpl(categoryRepository, rsService);

        // when
        var categories = categoryService.getCategories();

        // then
        assertEquals(3, categories.size());
    }

    // Testing the CategoryService, that returns with the correct details
    @Test
    public void shouldGetCategoryWithTheCorrectName(){
        // given
        Category c1 = new Category(1,"Metal");
        Category c2 = new Category(2,"Glass");
        Category c3 = new Category(3,"Plastic");

        categoryRepository = mock(CategoryRepositoryJpa.class);
        given(categoryRepository.findAll()).willReturn(List.of(c1, c2, c3));

        Rs u1 = new Rs(1, "reuse", "make something new");
        rsService = mock(RsService.class);
        given(rsService.getUsesByCategoryId(1)).willReturn(List.of(u1));
        // Testing that the category service
        categoryService = new CategoryServiceImpl(categoryRepository, rsService);

        // when
        var categories = categoryService.getCategories();

        // then
        assertEquals("Metal", categories.get(0).getCategoryName());
        assertEquals("Glass", categories.get(1).getCategoryName());
        assertEquals("Plastic", categories.get(2).getCategoryName());
        assertEquals(3, categories.size());
    }

    @Test
    public void shouldGetCategoryWithTheCorrectId() {
        // given
        Category c1 = new Category(1,"Metal");
        Category c2 = new Category(2,"Glass");
        Category c3 = new Category(3,"Plastic");

        categoryRepository = mock(CategoryRepositoryJpa.class);
        given(categoryRepository.findAll()).willReturn(List.of(c1, c2, c3));

        Rs u1 = new Rs(1, "reuse", "make something new");
        rsService = mock(RsService.class);
        given(rsService.getUsesByCategoryId(1)).willReturn(List.of(u1));

        // Testing that the category service
        categoryService = new CategoryServiceImpl(categoryRepository, rsService);

        // when
        var categories = categoryService.getCategories();

        // then
        // Checking that the id is correct
        assertEquals(1, categories.get(0).getCategoryId());
        assertEquals(2, categories.get(1).getCategoryId());
        assertEquals(3, categories.get(2).getCategoryId());
        // Checking the names are correct
        assertEquals("Metal", categories.get(0).getCategoryName());
        assertEquals("Glass", categories.get(1).getCategoryName());
        assertEquals("Plastic", categories.get(2).getCategoryName());
        // Checking the size is correct
        assertEquals(3, categories.size());
    }
}