package com.nagarro.yourmart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.yourmart.repository.AdminRepository;
import com.nagarro.yourmart.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public String authenticate(String userId, String password) {
		return adminRepository.authenticate(userId, password);
	}

}
