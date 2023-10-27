package com.example.wastemanagement.unit.service;

import com.example.wastemanagement.data.RsRepositoryJpa;
import com.example.wastemanagement.domain.Category;
import com.example.wastemanagement.domain.Rs;
import com.example.wastemanagement.service.CategoryService;
import com.example.wastemanagement.service.CategoryServiceImpl;
import com.example.wastemanagement.service.RsService;
import com.example.wastemanagement.service.RsServiceImpl;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class GetUses {
    private CategoryService categoryService;
    private RsService rsService;
    private RsRepositoryJpa useRepository;

    // Testing the useService, returns the correct number of uses for a category
    @Test
    public void shouldGetOneUse(){
        // given
        Rs u1 = new Rs(1, "reuse", "make something new");

        useRepository = mock(RsRepositoryJpa.class);
        given(useRepository.findUseByCategoryId(1)).willReturn(List.of(u1));

        Category c1 = new Category(1, "Metal");
        Category c2 = new Category(2, "Glass");
        Category c3 = new Category(3, "Plastic");
        Category c4 = new Category(4, "Paper");
        Category c5 = new Category(5, "Textiles");
        Category c6 = new Category(6, "Food");

        categoryService = mock(CategoryServiceImpl.class);
        given(categoryService.getCategories()).willReturn(List.of(c1, c2, c3, c4, c5, c6));


        // Testing that the use service returns the correct number of uses for a given category
        rsService = new RsServiceImpl(useRepository);

        // when
        var uses = rsService.getUsesByCategoryId(1);

        // then
        assertEquals(1, uses.size());
    }

    @Test
    public void shouldGetSixUse() {
        // given
        int categoryId = 2;
        Rs u1 = new Rs(categoryId, "reuse", "give it a new use, make it into something else");
        Rs u2 = new Rs(categoryId, "recycle", "make something new");
        Rs u3 = new Rs(categoryId, "compost", "make it into compost");
        Rs u4 = new Rs(categoryId, "donate", "give it to charity");
        Rs u5 = new Rs(categoryId, "upcycle", "make something new");
        Rs u6 = new Rs(categoryId, "repurpose", "make something new");

        useRepository = mock(RsRepositoryJpa.class);
        given(useRepository.findUseByCategoryId(categoryId)).willReturn(List.of(u1, u2, u3, u4, u5, u6));

        Category c1 = new Category(1, "Metal");
        Category c2 = new Category(2, "Glass");

        categoryService = mock(CategoryServiceImpl.class);
        given(categoryService.getCategories()).willReturn(List.of(c1, c2));


        // Testing that the use service returns the correct number of uses for a given category
        rsService = new RsServiceImpl(useRepository);

        // when
        var uses = rsService.getUsesByCategoryId(categoryId);

        // then
        assertEquals(6, uses.size());
    }

    @Test
    public void shouldGetFourUses(){
        // given
        int categoryId = 2;
        Rs u1 = new Rs(categoryId, "reuse", "give it a new use, make it into something else");
        Rs u2 = new Rs(categoryId, "recycle", "make something new");
        Rs u3 = new Rs(3, "compost", "make it into compost");
        Rs u4 = new Rs(1, "donate", "give it to charity");
        Rs u5 = new Rs(categoryId, "upcycle", "make something new");
        Rs u6 = new Rs(categoryId, "repurpose", "make something new");

        // saves it to the useRepository
        useRepository = mock(RsRepositoryJpa.class);
        given(useRepository.findUseByCategoryId(categoryId)).willReturn(List.of(u1, u2, u5, u6));
        given(useRepository.findUseByCategoryId(3)).willReturn(List.of(u3));
        given(useRepository.findUseByCategoryId(1)).willReturn(List.of(u4));

        Category c1 = new Category(1, "Metal");
        Category c2 = new Category(2, "Glass");
        Category c3 = new Category(3, "Plastic");

        categoryService = mock(CategoryServiceImpl.class);
        given(categoryService.getCategories()).willReturn(List.of(c1, c2, c3));
        // Testing that the use service returns the correct number of uses for a given category
        rsService = new RsServiceImpl(useRepository);

        // when
        var uses = rsService.getUsesByCategoryId(categoryId);

        // then
        assertEquals(4, uses.size());
    }

    // Testing the useService, return the correct uses for a category

    @Test
    public void shouldGetTheCorrectDetailsForEachUse(){
        // given
        int categoryId = 2;
        Rs u1 = new Rs(categoryId, "reuse", "give it a new use, make it into something else");
        Rs u2 = new Rs(categoryId, "recycle", "make something new");
        Rs u3 = new Rs(3, "compost", "make it into compost");
        Rs u4 = new Rs(1, "donate", "give it to charity");
        Rs u5 = new Rs(categoryId, "upcycle", "make something new");
        Rs u6 = new Rs(categoryId, "repurpose", "make something new");

        // saves it to the useRepository
        useRepository = mock(RsRepositoryJpa.class);
        given(useRepository.findUseByCategoryId(categoryId)).willReturn(List.of(u1, u2, u5, u6));
        given(useRepository.findUseByCategoryId(3)).willReturn(List.of(u3));
        given(useRepository.findUseByCategoryId(1)).willReturn(List.of(u4));

        Category c1 = new Category(1, "Metal");
        Category c2 = new Category(2, "Glass");
        Category c3 = new Category(3, "Plastic");

        categoryService = mock(CategoryServiceImpl.class);
        given(categoryService.getCategories()).willReturn(List.of(c1, c2, c3));
        // Testing that the use service returns the correct details for each of the uses for a given category
        rsService = new RsServiceImpl(useRepository);

        // when
        var uses = rsService.getUsesByCategoryId(categoryId);

        // then
        assertEquals("reuse", uses.get(0).getName());
        assertEquals("recycle", uses.get(1).getName());
        assertEquals("upcycle", uses.get(2).getName());
        assertEquals("repurpose", uses.get(3).getName());

        assertEquals("give it a new use, make it into something else", uses.get(0).getDescription());
        assertEquals("make something new", uses.get(1).getDescription());
        assertEquals("make something new", uses.get(2).getDescription());
        assertEquals("make something new", uses.get(3).getDescription());

        assertEquals(2, uses.get(0).getCategoryId());
        assertEquals(2, uses.get(1).getCategoryId());
        assertEquals(2, uses.get(2).getCategoryId());
        assertEquals(2, uses.get(3).getCategoryId());
    }

}
