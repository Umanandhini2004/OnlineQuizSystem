package com.Uma.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Uma.main.model.Result;

@Repository
public interface ResultRepo extends JpaRepository<Result, Integer> {

}
