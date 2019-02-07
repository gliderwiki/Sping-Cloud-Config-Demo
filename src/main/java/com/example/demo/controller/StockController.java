package com.example.demo.controller;

import com.example.demo.domain.entity.StockUser;
import com.example.demo.framework.exception.ResourceNotFoundException;
import com.example.demo.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class StockController {


    @Autowired
    private StockService stockService;


    @GetMapping("/hello")
    public String hello() {
        return "Stock-db-service Hello World";
    }


    @GetMapping("/user")
    public List<StockUser> stockUserList(@RequestParam String userId) {
        return stockService.findByUserId(userId);
    }

    @PostMapping("/user")
    public StockUser addStockUser(@Valid @RequestBody StockUser stockUser) {
        stockUser.setCreatedAt(new Date());
        StockUser stockUserEntity = stockService.save(stockUser);

        return stockUserEntity;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<StockUser> stockUser(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        StockUser stockUser = stockService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock user not found for this id = " + id));
        return ResponseEntity.ok().body(stockUser);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<StockUser> updateStockUser(@PathVariable(value = "id") Integer id,
                                                     @Valid @RequestBody StockUser stockDetail) throws ResourceNotFoundException {
        StockUser stockUser = stockService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock user not found for this id = " + id));

        stockUser.setStock(stockDetail.getStock());
        stockUser.setUserId(stockDetail.getUserId());
        stockUser.setCreatedAt(new Date());

        final StockUser updateStockUser = stockService.save(stockUser);

        return ResponseEntity.ok(updateStockUser);
    }


    @DeleteMapping("/user/{id}")
    public Map<String, Boolean> deleteStockUser(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        StockUser stockUser = stockService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock user not found for this id = " + id));

        stockService.deleteById(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
