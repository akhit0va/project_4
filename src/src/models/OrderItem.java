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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPricePerItem() {
        return pricePerItem;
    }

    public void setPricePerItem(double pricePerItem) {
        this.pricePerItem = pricePerItem;
    }
}
