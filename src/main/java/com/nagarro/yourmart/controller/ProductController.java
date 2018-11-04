package com.nagarro.yourmart.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nagarro.yourmart.entity.Category;
import com.nagarro.yourmart.entity.Product;
import com.nagarro.yourmart.service.ProductService;
import com.nagarro.yourmart.service.SellerService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private SellerService sellerService;

	@RequestMapping(value = "/admin/product", method = RequestMethod.GET)
	public String getAllProduct(ModelMap model, HttpServletRequest request,
			@RequestParam(value = "sortBy", required = false) String sortBy,
			@RequestParam(value = "searchBy", required = false) String searchBy,
			@RequestParam(value = "searchValue", required = false) String searchValue,
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "category", required = false) Integer category,
			@RequestParam(value = "sellerId", required = false) Integer sellerId,
			@RequestParam(value = "sellerCompanyName", required = false) String sellerCompanyName,
			@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {

		HttpSession session = request.getSession(false);
		// if (session != null && session.getAttribute("admin") != null) {
		model.addAttribute("lastSearchValue", searchValue);
		if (searchBy != null) {
			String isProductCodeSelected = searchBy.equals("code") ? "selected" : " ";
			model.addAttribute("isProductCodeSelected", isProductCodeSelected);
			String isProductNameSelected = searchBy.equals("name") ? "selected" : " ";
			model.addAttribute("isProductNameSelected", isProductNameSelected);
			String isProductIdSelected = searchBy.equals("id") ? "selected" : " ";
			model.addAttribute("isProductIdSelected", isProductIdSelected);
		}
		if (status != null) {
			String isNewSelected = status == 1 ? "selected" : " ";
			model.addAttribute("isNewSelected", isNewSelected);
			String isApprovedSelected = status == 2 ? "selected" : " ";
			model.addAttribute("isApprovedSelected", isApprovedSelected);
			String isRejectedSelected = status == 3 ? "selected" : " ";
			model.addAttribute("isRejectedSelected", isRejectedSelected);
			String isReviewSelected = status == 4 ? "selected" : " ";
			model.addAttribute("isReviewSelected", isReviewSelected);
		}
		if (sortBy != null) {
			String isMrpSelected = sortBy.equals("mrp") ? "selected" : " ";
			model.addAttribute("isMrpSelected", isMrpSelected);
			String isSspSelected = sortBy.equals("ssp") ? "selected" : " ";
			model.addAttribute("isSspSelected", isSspSelected);
			String isYmpSelected = sortBy.equals("ymp") ? "selected" : " ";
			model.addAttribute("isYmpSelected", isYmpSelected);
			String isCreationDateSelected = sortBy.equals("createdAt") ? "selected" : " ";
			model.addAttribute("isCreationDateSelected", isCreationDateSelected);
			String isUpdationDateSelected = sortBy.equals("updatedAt") ? "selected" : " ";
			model.addAttribute("isUpdationDateSelected", isUpdationDateSelected);
		}

		List<Product> products = null;
		List<Integer> sellerIds = null;
		List<String> sellerCompanyNames = null;
		List<Category> categories = null;
		System.out.println("sid: " + sellerId);
		if (sellerCompanyName != null && sellerCompanyName.isEmpty()) {
			sellerCompanyName = null;
		}
		products = productService.getAllProducts(sortBy, searchBy, searchValue, status, category, sellerId,
				sellerCompanyName, offset, limit);
		System.out.println(products.size());
		sellerIds = sellerService.getAllSellerIds();
		sellerCompanyNames = sellerService.getAllSellerCompanyNames();
		// categories = (ArrayList<Category>) categoryRepository.getAllCategory();
		model.addAttribute("products", products);
		model.addAttribute("sellerIds", sellerIds);
		model.addAttribute("sellerCompanyNames", sellerCompanyNames);
		model.addAttribute("categories", categories);
		return "productList";
		// }
		// return "redirect:/admin/login";
	}

	@RequestMapping(value = "/admin/product", method = RequestMethod.POST)
	public String approveProducts(HttpServletRequest request) {
		String[] productIds = request.getParameterValues("checkboxes");
		if (productIds.length != 0) {
			for (String productId : productIds) {
				productService.changeProductStatus(Integer.parseInt(productId), 2);
			}
		}
		return "redirect:/admin/product";
	}

	@RequestMapping(value = "/admin/product/{productId}", method = RequestMethod.GET)
	public String productDetailsPage(HttpServletRequest request, ModelMap model, @PathVariable("productId") int id) {
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		return "productDetails";
	}

	@RequestMapping(value = "/admin/product/{productId}/comment", method = RequestMethod.POST)
	public String addComment(@PathVariable("productId") int id, @RequestParam("comment") String comment) {
		productService.addComment(id, comment);
		return "redirect:/admin/product/" + id;
	}
	
	@RequestMapping(value="/admin/product/{productId}/status", method = RequestMethod.POST)
	public String changeStatus(@PathVariable("productId") int id,
							   @RequestParam(value = "CHANGE_TO", required = true) int changeTo) {
		if(changeTo==1 || changeTo==2 || changeTo==3 || changeTo==4 ) {
			productService.changeProductStatus(id, changeTo);
		}
		return "redirect:/admin/product/" + id;
	}

}
