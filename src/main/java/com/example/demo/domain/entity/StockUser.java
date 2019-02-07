package com.example.demo.domain.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="stock_user", catalog = "test")
public class StockUser {

    public StockUser() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
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