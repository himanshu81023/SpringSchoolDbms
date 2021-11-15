package com.mayank.authentication.dao;

import com.mayank.authentication.model.CourseName;
import org.springframework.data.repository.CrudRepository;

public interface CourseNameDao extends CrudRepository<CourseName,String> {
}
