package com.mayank.authentication.dao;

import com.mayank.authentication.model.Staff;
import org.springframework.data.repository.CrudRepository;

public interface StaffDao extends CrudRepository<Staff,String> {
}
