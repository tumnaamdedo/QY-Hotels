package com.QYhotels.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl {

	public void saveAdminRegistration(AdminRegistration adminregReq) {
		dao.save(adminregReq);
		
	}
	public List<AdminRegistration> getAdminData(){
		return dao.findAll();
	}
}
