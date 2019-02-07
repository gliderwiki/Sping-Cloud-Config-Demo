package com.example.demo.controller;

import com.example.demo.domain.entity.StockUser;
import com.example.demo.framework.exception.ResourceNotFoundException;
import com.example.demo.service.StockService;
import io.swagger.annotations.*;
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
@Api(value="스톡옵션 관리 시스템", description="각 사용자들에게 부여된 스톡옵션을 관리한다.")
public class StockController {


    @Autowired
    private StockService stockService;


    @ApiOperation(value = "View a list of grant stock option", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/user")
    public List<StockUser> stockUserList(@RequestParam String userId) {
        return stockService.findByUserId(userId);
    }

    @ApiOperation(value = "Add Stock User")
    @PostMapping("/user")
    public StockUser addStockUser(@ApiParam(value = "Stock User store in database table", required = true) @Valid @RequestBody StockUser stockUser) {
        stockUser.setCreatedAt(new Date());
        StockUser stockUserEntity = stockService.save(stockUser);

        return stockUserEntity;
    }

    @ApiOperation(value = "Get an Stock User by Id")
    @GetMapping("/user/{id}")
    public ResponseEntity<StockUser> stockUser(@ApiParam(value = "Stock User id from which stockuser object will retrieve", required = true)
                                               @PathVariable("id") Integer id) throws ResourceNotFoundException {
        StockUser stockUser = stockService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock user not found for this id = " + id));
        return ResponseEntity.ok().body(stockUser);
    }

    @ApiOperation(value = "Update Stock User by Id")
    @PutMapping("/user/{id}")
    public ResponseEntity<StockUser> updateStockUser(@ApiParam(value = "Stock User Id to update stockUser object", required = true)
                                                     @PathVariable(value = "id") Integer id,
                                                     @ApiParam(value = "Update stockUser object", required = true)
                                                     @Valid @RequestBody StockUser stockDetail) throws ResourceNotFoundException {
        StockUser stockUser = stockService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock user not found for this id = " + id));

        stockUser.setStock(stockDetail.getStock());
        stockUser.setUserId(stockDetail.getUserId());
        stockUser.setCreatedAt(new Date());

        final StockUser updateStockUser = stockService.save(stockUser);

        return ResponseEntity.ok(updateStockUser);
    }


    @ApiOperation(value = "Delete an Stock User")
    @DeleteMapping("/user/{id}")
    public Map<String, Boolean> deleteStockUser(@ApiParam(value = "StockUser Id from which stockuser object will delete from database table", required = true)
                                                @PathVariable("id") Integer id) throws ResourceNotFoundException {
        StockUser stockUser = stockService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stock user not found for this id = " + id));

        stockService.deleteById(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
