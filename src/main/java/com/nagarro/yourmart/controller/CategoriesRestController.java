package com.nagarro.yourmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.yourmart.entity.Category;
import com.nagarro.yourmart.service.CategoryService;

@CrossOrigin
@RestController
public class CategoriesRestController {
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping(path = "api/category")
	public List<Category> getAllCategories(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
										   @RequestParam(value = "limit", required = false, defaultValue = "10") int limit){
		List<Category> categories = null;
		categories = categoryService.getAllCategories(offset, limit);
		return categories;
	}
}
