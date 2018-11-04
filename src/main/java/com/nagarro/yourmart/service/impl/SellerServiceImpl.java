package com.nagarro.yourmart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.yourmart.entity.Seller;
import com.nagarro.yourmart.repository.SellerRepository;
import com.nagarro.yourmart.service.SellerService;

@Service
public class SellerServiceImpl implements SellerService {
	
	@Autowired
	private SellerRepository sellerRepository;

	@Override
	public List<Seller> getAllSellers(String sortBy, String searchBy, String searchValue, Integer status, int offset,
			int limit) {
		System.out.println(status);
		return sellerRepository.getAllSellers(sortBy, searchBy, searchValue, status, offset, limit);
	}

	@Override
	public Seller getSellerById(int id) {
		return sellerRepository.getSellerById(id);
	}

	@Override
	public Seller authenticate(int id, String password) {
		return sellerRepository.authenticate(id, password);
	}

	@Override
	public void addNewSeller(Seller seller) {
		seller.setStatus(1);
		sellerRepository.addNewSeller(seller);
	}

	@Override
	public Seller changeSellerStatus(int sellerId, int status) {
		return sellerRepository.changeSellerStatus(sellerId, status);
	}

	@Override
	public List<Integer> getAllSellerIds() {
		return sellerRepository.getAllSellerIds();
	}

	@Override
	public List<String> getAllSellerCompanyNames() {
		return sellerRepository.getAllSellerCompanyNames();
	}

	@Override
	public Integer getSellerIdByToken(String token) {
		return sellerRepository.getSellerIdByToken(token);
	}

}
