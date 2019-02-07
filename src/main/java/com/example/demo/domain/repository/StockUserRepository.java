package com.example.demo.domain.repository;

import com.example.demo.domain.entity.StockUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockUserRepository extends CrudRepository<StockUser, Integer> {

    List<StockUser> findByUserId(String userId);
}