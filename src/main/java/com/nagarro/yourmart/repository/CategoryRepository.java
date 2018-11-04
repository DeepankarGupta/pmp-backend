package com.nagarro.yourmart.repository;

import java.util.List;

import com.nagarro.yourmart.entity.Category;

public interface CategoryRepository {
	
	List<Category> getAllCategories(int offset, int limit);
	Category getCategoryById(int id);
	Category addNewCategory(Category category);
	Category updateCategory(Category category);
	Category deleteCategory(int id);

}