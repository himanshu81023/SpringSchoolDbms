package com.mayank.authentication.dao;

import com.mayank.authentication.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StudentDao extends JpaRepository<Student,String> {

    @Query(value="select count(*) from student st where st.class = :name",nativeQuery =true)
    public Integer findSizeByClass(@Param("name") String className);
}
