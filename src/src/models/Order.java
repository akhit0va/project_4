package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long orderId;
    private User user;
    private List<OrderItem> orderItems;
    private double totalPrice;
    private String status;
    private LocalDateTime createdAt;

    public Order(Long orderId, User user, double totalPrice) {
        this.orderId = orderId;
        this.user = user;
        this.totalPrice = totalPrice;
        this.orderItems = new ArrayList<>();
        this.status = "Pending";
        this.createdAt = LocalDateTime.now();
    }

    // Methods to add and remove order items
    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }

    public void removeOrderItem(OrderItem orderItem) {
        this.orderItems.remove(orderItem);
    }

    // Getters and Setters

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
