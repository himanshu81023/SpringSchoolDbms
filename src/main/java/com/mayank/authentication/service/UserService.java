package com.mayank.authentication.service;

import java.util.HashSet;
import java.util.Set;

//import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.mayank.authentication.dao.RoleDao;
import com.mayank.authentication.dao.UserDao;
import com.mayank.authentication.model.Role;
import com.mayank.authentication.model.User;

@Service
public class UserService {
	
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User registerNewUser( User user)
	{
	     return userDao.save(user);
	}
	public void initRolesAndUser() {
		Role adminRole = new Role();
	    adminRole.setRoleName("Admin");
	    adminRole.setRoleDescription("Admin role");
	    roleDao.save(adminRole);
	    
//	    Role userRole = new Role();
//	    userRole.setRoleName("User");
//	    userRole.setRoleDescription("Default role for newly created record");
//	    roleDao.save(userRole);
//
	    User adminUser = new User();
	    adminUser.setUserFirstName("admin");
	    adminUser.setUserLastName("admin");
	    adminUser.setUserName("admin123");
	    adminUser.setUserPassword(getEncodedPassword("admin@pass"));
	    Set<Role> adminRoles = new HashSet<>();
	    adminRoles.add(adminRole);
	    adminUser.setRole(adminRoles);
	    userDao.save(adminUser);
	    
//	    User user = new User();
//	    user.setUserFirstName("raj");
//	    user.setUserLastName("sharma");
//	    user.setUserName("raj@123");
//	    user.setUserPassword(getEncodedPassword("raj@123"));
//	    Set<Role> userRoles = new HashSet<>();
//	    userRoles.add(userRole);
//	    user.setRole(userRoles);
//	    userDao.save(user);
//
	}

	public String getEncodedPassword(String password)
	{
//		System.out.println(password);
		String encodedPassword = passwordEncoder.encode(password);
//		System.out.print(encodedPassword);
		return encodedPassword;
	}

}
