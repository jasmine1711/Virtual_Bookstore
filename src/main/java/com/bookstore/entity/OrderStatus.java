package com.bookstore.entity;

// ✅ Must be public so CartService can use it
public enum OrderStatus {
    PENDING, 
    PROCESSING, 
    SHIPPED, 
    DELIVERED, 
    CANCELLED
}