package com.nagarro.yourmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.yourmart.dto.SellerLoginRequest;
import com.nagarro.yourmart.dto.SellerLoginResponse;
import com.nagarro.yourmart.dto.SellerResponse;
import com.nagarro.yourmart.dto.SellerStatusChangeRequest;
import com.nagarro.yourmart.entity.Seller;
import com.nagarro.yourmart.service.SellerService;
import com.nagarro.yourmart.util.ModelMapperUtil;

@CrossOrigin
@RestController
public class SellerRestController {

	@Autowired
	private SellerService sellerService;
	
	@PostMapping(path="api/seller/register")
	public void registerSeller(@RequestBody Seller seller) {
		sellerService.addNewSeller(seller);
	}
	
	@PostMapping(path="api/seller/login")
	public SellerLoginResponse authenticateSeller(@RequestBody SellerLoginRequest sellerLoginRequest) {
		Seller seller = sellerService.authenticate(sellerLoginRequest.getId(), sellerLoginRequest.getPassword());
		SellerLoginResponse sellerLoginResponse = ModelMapperUtil.convertModel(seller, SellerLoginResponse.class);
		return sellerLoginResponse;
	}  
	
//	@GetMapping(path="api/seller/{id}")
//	public Seller getSellerById(@PathVariable("id") int id) {
//		return sellerService.getSellerById(id);
//	}
	
	@GetMapping(path="api/seller/ids")
	public List<Integer> getSellerIds() {
		return sellerService.getAllSellerIds();
	}
	
	@GetMapping(path="api/seller/companyNames")
	public List<String> getSellerCompanyNames() {
		return sellerService.getAllSellerCompanyNames();
	}
	
	@PostMapping(path="api/seller/status")
	public SellerResponse changeStatus(@RequestBody SellerStatusChangeRequest sellerStatusChangeRequest) {
		Seller seller =  sellerService.changeSellerStatus(sellerStatusChangeRequest.getId(), sellerStatusChangeRequest.getStatus());
		SellerResponse sellerResponse = ModelMapperUtil.convertModel(seller, SellerResponse.class);
		return sellerResponse;
	}
	
}
