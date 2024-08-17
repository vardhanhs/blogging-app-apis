package com.blogging_apis.blog.service;

import com.blogging_apis.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    //create
    CategoryDto createCategory(CategoryDto categoryDto);

    //update
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    // get
    CategoryDto getCategory(Integer categoryId);

    //get all
    List<CategoryDto> getAllCategories();

    //delete
    void deleteCategory(Integer categoryId);
}
