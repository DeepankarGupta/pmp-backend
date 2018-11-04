package com.nagarro.yourmart.service;

import java.util.List;

import com.nagarro.yourmart.entity.Seller;

public interface SellerService {
	
	List<Seller> getAllSellers(String sortBy, String searchBy, String searchValue, Integer status, int offset, int limit);
	Seller getSellerById(int id);
	Seller authenticate(int id, String password);
	Integer getSellerIdByToken(String token);
	void addNewSeller(Seller seller);
	Seller changeSellerStatus(int sellerId, int status);
	List<Integer> getAllSellerIds();
	List<String>  getAllSellerCompanyNames();

}