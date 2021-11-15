package com.mayank.authentication.dao;

import com.mayank.authentication.model.Classes;
import org.springframework.data.repository.CrudRepository;

public interface ClassesDao extends CrudRepository<Classes,String> {
}
