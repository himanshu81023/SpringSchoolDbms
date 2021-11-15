package com.mayank.authentication.dao;

import com.mayank.authentication.model.CompositeKeyForScore;
import com.mayank.authentication.model.Score;

import org.springframework.data.repository.CrudRepository;

public interface ScoreDao extends CrudRepository<Score,CompositeKeyForScore>{
    
}
