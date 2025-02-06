package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Long cartId;
    private User user;
    private List<CartItem> cartItems;
    private LocalDateTime createdAt;

    public Cart(Long cartId, User user) {
        this.cartId = cartId;
        this.user = user;
        this.cartItems = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }


    public void addItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
    }

    public void removeItem(CartItem cartItem) {
        this.cartItems.remove(cartItem);
    }

    // Getters and Setters
}
