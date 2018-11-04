package com.nagarro.yourmart.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nagarro.yourmart.dto.ImageResponse;
import com.nagarro.yourmart.dto.NewProductRequest;
import com.nagarro.yourmart.dto.UpdateProductRequest;
import com.nagarro.yourmart.entity.Category;
import com.nagarro.yourmart.entity.Image;
import com.nagarro.yourmart.entity.Product;
import com.nagarro.yourmart.entity.Seller;
import com.nagarro.yourmart.service.ImageService;
import com.nagarro.yourmart.service.ProductService;
import com.nagarro.yourmart.service.SellerService;
import com.nagarro.yourmart.util.ModelMapperUtil;

@CrossOrigin
@RestController
public class ProductRestController {

	@Autowired
	private ProductService productService;

	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private ImageService imageService;

	@GetMapping(path = "api/product")
	public List<Product> getAllProducts(@RequestParam(value = "sortBy", required = false) String sortBy,
										@RequestParam(value = "searchBy", required = false) String searchBy,
										@RequestParam(value = "searchValue", required = false) String searchValue,
										@RequestParam(value = "status", required = false) Integer status,
										@RequestParam(value = "category", required = false) Integer category,
										@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
										@RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
										@RequestHeader(value = "authentication") String token) {
		Integer sellerIdFromToken = sellerService.getSellerIdByToken(token);
		List<Product> products = null;
		if(sellerIdFromToken != null) {
			products = productService.getAllProducts(sortBy, searchBy, searchValue, status, category, sellerIdFromToken, offset, limit);			
		}
		return products;
	}
	
	@GetMapping(path="api/product/{id}")
	public Product getProductById(@PathVariable("id") int id,
								  @RequestHeader(value = "authentication") String token) {
		
		Product product = null;
		Integer sellerIdFromToken = sellerService.getSellerIdByToken(token);
		Integer sellerIdFromProduct = productService.getSellerId(id); 
		if(sellerIdFromToken == sellerIdFromProduct ) {
			product = productService.getProductById(id);
		}
		return product;
	}
	
	@PostMapping(path = "api/product")
	public Product addNewProduct(@RequestBody NewProductRequest newProductRequest,
			@RequestHeader(value = "authentication") String token) {

		Product product = null;
		Product newProduct = null;
		Integer sellerId = sellerService.getSellerIdByToken(token);
		if(sellerId != null) {
			product = ModelMapperUtil.convertModel(newProductRequest, Product.class);
			Seller seller = new Seller();
			Category category = new Category();
			seller.setId(sellerId);
			category.setId(newProductRequest.getCategoryId());
			product.setSeller(seller);
			product.setCategory(category);
			newProduct = productService.addNewProduct(product);
		}
		return newProduct;
	}

	@PutMapping(path="api/product/{id}")
	public Product updateProduct(@RequestBody UpdateProductRequest updateProductRequest,
				 				 @RequestHeader(value="authentication") String token,
				 				 @PathVariable("id") int id) {
		Product updatedProductResponse = null;
		Integer sellerIdFromToken = sellerService.getSellerIdByToken(token);
		Integer sellerIdFromProduct = productService.getSellerId(id); 
		if(sellerIdFromToken == sellerIdFromProduct ) {
			Product product = productService.getProductById(id);
			Product updatedProduct = ModelMapperUtil.convertModel(updateProductRequest, Product.class);
			updatedProduct.setId(product.getId());
			updatedProduct.setComment(product.getComment());
			updatedProduct.setSeller(product.getSeller());
			updatedProduct.setCreatedAt(product.getCreatedAt());
			Category category = new Category();
			category.setId(updateProductRequest.getCategoryId());
			updatedProduct.setCategory(category);
			updatedProductResponse = productService.updateProduct(updatedProduct);
		}
		return updatedProductResponse;
	}
	
	@DeleteMapping(path="api/product/{id}")
	public Product deleteProduct(@PathVariable("id") int id,
							  	 @RequestHeader(value="authentication") String token) {
		Product product = null;
		Integer sellerIdFromToken = sellerService.getSellerIdByToken(token);
		Integer sellerIdFromProduct = productService.getSellerId(id); 
		if(sellerIdFromToken == sellerIdFromProduct ) {
			product = productService.deleteProduct(id);
		}
		return product;
	}
	
	@PostMapping(path = "api/product/{productId}/image")
	public void uploadImage(@PathVariable("productId") int id, 
							@RequestParam("image") MultipartFile imageFile) throws IOException {
		Image image = new Image();
		image.setImage(imageFile.getBytes());
		Product product = new Product();
		product.setId(id);
		image.setProduct(product);
		imageService.uploadImage(image);
	}
	
	@GetMapping(path = "api/product/{productId}/image")
	public List<ImageResponse> getProductImages(@PathVariable("productId") int productId) {
		List<Image> images = imageService.getAllProductImages(productId);
		List<ImageResponse> imagesResponse = ModelMapperUtil.convertModelList(images, ImageResponse.class);
		return imagesResponse;
	}
	
	
}
