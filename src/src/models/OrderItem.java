package models;

public class OrderItem {
    private Order order;
    private Item item;
    private int quantity;
    private double pricePerItem;

    public OrderItem(Order order, Item item, int quantity, double pricePerItem) {
        this.order = order;
        this.item = item;
        this.quantity = quantity;
        this.pricePerItem = pricePerItem;
    }

    // Getters and Setters
}
