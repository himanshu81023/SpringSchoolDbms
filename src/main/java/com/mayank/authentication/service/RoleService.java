package com.mayank.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mayank.authentication.dao.RoleDao;
import com.mayank.authentication.model.Role;

@Service
public class RoleService {
     
	@Autowired
	private RoleDao roleDao;
	 public Role createNewRole(Role role)
	 {
		 return roleDao.save(role);
		  
	 }
}
