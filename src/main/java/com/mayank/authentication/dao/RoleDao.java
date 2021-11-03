package com.mayank.authentication.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mayank.authentication.model.Role;

@Repository 
public interface RoleDao extends CrudRepository<Role,String>{
	
}
