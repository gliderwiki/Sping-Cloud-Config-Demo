package com.example.demo.service;

import com.example.demo.domain.entity.StockUser;
import com.example.demo.domain.repository.StockUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockUserRepository stockUserRepository;

    public List<StockUser> findByUserId(String userId) {
        return stockUserRepository.findByUserId(userId);
    }

    public StockUser save(StockUser stockUser) {
        return stockUserRepository.save(stockUser);
    }

    public Optional<StockUser> findById(Integer id) {
        return stockUserRepository.findById(id);
    }

    public void deleteById(Integer id) {
        stockUserRepository.deleteById(id);
    }
}