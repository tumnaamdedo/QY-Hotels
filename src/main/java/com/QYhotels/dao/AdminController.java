package com.QYhotels.main.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.QYhotels.main.Beans.AdminRegistration;
import com.QYhotels.main.Dao.AdminRepository;
import com.QYhotels.main.Service.AdminRegService;


@RestController
@RequestMapping("/Admin")
public class AdminController {

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private AdminRegService udv;

	@PostMapping(value="/registration")
	public String createAdmin(@RequestBody AdminRegistration adminregReq, AdminRegistration adminregistration) {
		adminregistration.setRole("Admin");
		udv.saveAdminRegistration(adminregReq);
		return "success";
		
	}
		@GetMapping(value="/getAdminList")
		public List<AdminRegistration> getAdminData(){
			return udv.getAdminData();
		}
	
		
}
		
