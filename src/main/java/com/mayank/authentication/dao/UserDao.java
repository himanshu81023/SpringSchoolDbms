package com.mayank.authentication.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mayank.authentication.model.User;

@Repository
public interface UserDao extends CrudRepository<User,String>{

}
