package com.nagarro.yourmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nagarro.yourmart.dto.CategoryCountResponse;
import com.nagarro.yourmart.entity.Category;
import com.nagarro.yourmart.service.CategoryService;

@Controller
public class CategoriesController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/admin/category", method = RequestMethod.GET)
	public String getAllCategories(ModelMap model,
								   @RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
								   @RequestParam(value = "limit", required = false, defaultValue = "10") int limit){
		
		List<CategoryCountResponse> categories = null;
		categories = categoryService.getAllCategoriesWithCount(offset, limit);
		model.addAttribute("categories",categories);
		return "categoryList";
	}
	
	@RequestMapping(value = "/admin/category", method = RequestMethod.POST)
	public String addNewCategory(@RequestBody Category category) {
		Category newCategory = categoryService.addNewCategory(category);
		return "redirect:/admin/category";
	}
	
	@RequestMapping(value = "/admin/category/{id}", method = RequestMethod.PUT)
	public String updateCategory(@PathVariable("id") int id, @RequestBody Category category) {
		Category updatedCategory = categoryService.updateCategory(category);
		return "redirect:/admin/category";
	}
	
	@RequestMapping(value = "/admin/category/{id}/delete", method = RequestMethod.POST)
	public String deleteCategory(@PathVariable("id") int id) {
		Category deletedCategory = categoryService.deleteCategory(id);
		return "redirect:/admin/category";
	}
	

}
