package com.mayank.authentication.dao;

import com.mayank.authentication.model.Notice;
import org.springframework.data.repository.CrudRepository;

public interface NoticeDao extends CrudRepository<Notice,String> {
}
