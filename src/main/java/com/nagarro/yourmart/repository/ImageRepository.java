package com.nagarro.yourmart.repository;

import java.util.List;

import com.nagarro.yourmart.entity.Image;

public interface ImageRepository {
	
	void uploadImage(Image image);
	List<Image> getAllProductImages(int productId);

}