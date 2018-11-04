package com.nagarro.yourmart.service;

import java.util.List;

import com.nagarro.yourmart.entity.Image;

public interface ImageService {
	
	void uploadImage(Image image);
	List<Image> getAllProductImages(int productId);
	
}
