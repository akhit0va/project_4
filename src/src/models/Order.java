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
}
