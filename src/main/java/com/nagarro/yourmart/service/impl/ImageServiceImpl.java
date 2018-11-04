package com.nagarro.yourmart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.yourmart.entity.Image;
import com.nagarro.yourmart.repository.ImageRepository;
import com.nagarro.yourmart.service.ImageService;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	private ImageRepository imageRepository;
	
	@Override
	public void uploadImage(Image image) {
		imageRepository.uploadImage(image);
	}

	@Override
	public List<Image> getAllProductImages(int productId) {
		return imageRepository.getAllProductImages(productId);
	}

}
