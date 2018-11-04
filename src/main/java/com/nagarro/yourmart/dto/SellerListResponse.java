package com.nagarro.yourmart.dto;

import java.util.List;

public class SellerListResponse {
	
	private List<SellerResponse> sellers;
	private Long sellersCount;
	
	public List<SellerResponse> getSellers() {
		return sellers;
	}
	public void setSellers(List<SellerResponse> sellers) {
		this.sellers = sellers;
	}
	public Long getSellersCount() {
		return sellersCount;
	}
	public void setSellersCount(Long sellersCount) {
		this.sellersCount = sellersCount;
	}
	

}
