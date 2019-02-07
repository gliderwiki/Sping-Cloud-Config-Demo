package com.example.demo.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="stock_user", catalog = "test")
@ApiModel(description = "Stock user detail ")
public class StockUser {

    public StockUser() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(notes = "The database generated stock user ID")
    private Integer id;

    @Column(name = "user_id")
    @ApiModelProperty(notes = "Stock User ID (Unique value)")
    private String userId;

    @Column(name = "stock")
    @ApiModelProperty(notes = "Stock Quantity per user")
    private Integer stock;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @ApiModelProperty(notes = "database row created date")
    private Date createdAt;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "StockUser{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", stock=" + stock +
                ", createdAt=" + createdAt +
                '}';
    }
}