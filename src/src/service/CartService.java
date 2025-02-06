package service;

import models.CartItem;
import repository.Cart.CartRepository;

import java.util.List;

public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void addItemToCart(long cartId, long itemId, int quantity) {
        cartRepository.addItemToCart(cartId, itemId, quantity);
    }

    public List<CartItem> getCartItems(long cartId) {
        return cartRepository.getCartItems(cartId);
    }

    public void removeItemFromCart(long cartId, int index) {
        cartRepository.removeItemFromCart(cartId, index);
    }

    public boolean checkout(long cartId) {
        return cartRepository.checkoutCart(cartId);
    }
}
