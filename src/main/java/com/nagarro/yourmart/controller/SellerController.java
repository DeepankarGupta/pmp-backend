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

import com.nagarro.yourmart.dto.SellerResponse;
import com.nagarro.yourmart.entity.Seller;
import com.nagarro.yourmart.service.SellerService;
import com.nagarro.yourmart.util.ModelMapperUtil;

@Controller
@RequestMapping(value = "admin")
public class SellerController {

	@Autowired
	private SellerService sellerService;

	@RequestMapping(value = "seller", method = RequestMethod.GET)
	public String getAllSellers(ModelMap model, HttpServletRequest request,
			@RequestParam(value = "sortBy", required = false, defaultValue = "status") String sortBy,
			@RequestParam(value = "searchBy", required = false) String searchBy,
			@RequestParam(value = "searchValue", required = false) String searchValue,
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
			@RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
		
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("admin") != null) {
			model.addAttribute("lastSearchValue", searchValue);
			if (searchBy != null) {
				String isCompanyNameSelected = searchBy.equals("companyName") ? "selected" : " ";
				model.addAttribute("isCompanyNameSelected", isCompanyNameSelected);
				String isOwnerNameSelected = searchBy.equals("ownerName") ? "selected" : " ";
				model.addAttribute("isOwnerNameSelected", isOwnerNameSelected);
				String isPhoneNumberSelected = searchBy.equals("phoneNumber") ? "selected" : " ";
				model.addAttribute("isPhoneNumberSelected", isPhoneNumberSelected);
			}
			if (status != null) {
				String isNeedApprovalSelected = status == 1 ? "selected" : " ";
				model.addAttribute("isNeedApprovalSelected", isNeedApprovalSelected);
				String isApprovedSelected = status == 2 ? "selected" : " ";
				model.addAttribute("isApprovedSelected", isApprovedSelected);
				String isRejectedSelected = status == 3 ? "selected" : " ";
				model.addAttribute("isRejectedSelected", isRejectedSelected);
			}
			if (sortBy != null) {
				String isIdSelected = sortBy.equals("id") ? "selected" : " ";
				model.addAttribute("isIdSelected", isIdSelected);
				String isRegistrationTimeSelected = sortBy.equals("createdAt") ? "selected" : " ";
				model.addAttribute("isRegistrationTimeSelected", isRegistrationTimeSelected);
			}
			
			List<Seller> sellers = sellerService.getAllSellers(sortBy, searchBy, searchValue, status, offset, limit);
			List<SellerResponse> sellerList = ModelMapperUtil.convertModelList(sellers, SellerResponse.class);
			model.addAttribute("sellers", sellerList);
			return "sellerList";
		}
		return "redirect:/admin/login";
	}

	@RequestMapping(value = "seller", method = RequestMethod.POST)
	public String approveSellers(HttpServletRequest request) {
		String[] sellerIds = request.getParameterValues("checkboxes");
		if (sellerIds.length != 0) {
			for (String sellerId : sellerIds) {
				sellerService.changeSellerStatus(Integer.parseInt(sellerId), 2);
			}
		}
		return "redirect:/admin/seller";
	}

	@RequestMapping(value = "seller/{sellerId}", method = RequestMethod.GET)
	public String sellerDetailsPage(ModelMap model, HttpServletRequest request, @PathVariable("sellerId") int id) {
		Seller seller = sellerService.getSellerById(id);
		SellerResponse sellerResponse = ModelMapperUtil.convertModel(seller, SellerResponse.class);
		model.addAttribute("seller", sellerResponse);
		return "sellerDetails";
	}

	@RequestMapping(value = "seller/{id}/status", method = RequestMethod.POST)
	public String changeStatus(@PathVariable("id") int id,
			@RequestParam(value = "CHANGE_TO", required = true) int changeTo) {
		if (changeTo == 1 || changeTo == 2 || changeTo == 3) {
			sellerService.changeSellerStatus(id, changeTo);
		}
		return "redirect:/admin/seller/" + id;
	}
}
