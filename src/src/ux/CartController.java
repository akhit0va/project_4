package ux;

import data.PostgresDB;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import models.CartItem;
import repository.Cart.CartRepository;
import service.CartService;
import static ux.Main.getSceneManager;

import java.util.List;

public class CartController {
    @FXML private ListView<String> cartListView;
    @FXML private Button removeSelected;
    @FXML private Button checkout;
    @FXML private Label totalPriceLabel;

    private CartService cartService;
    private long cartId = 1; // Assuming user has a single cart for simplicity

    public CartController() {
        this.cartService = new CartService(new CartRepository(PostgresDB.getInstance()));
    }

    public void setCartService(CartService cartService) {
        this.cartService = cartService;
        loadCartItems();
    }

    private void loadCartItems() {
        cartListView.getItems().clear();
        List<CartItem> cartItems = cartService.getCartItems(cartId);
        double totalPrice = 0.0;

        for (CartItem item : cartItems) {
            String itemDisplay = item.getItem().getName() + " - Qty: " + item.getQuantity() + " ($" + item.getItem().getPrice() + ")";
            cartListView.getItems().add(itemDisplay);
            totalPrice += item.getQuantity() * item.getItem().getPrice();
        }

        totalPriceLabel.setText("Total: $" + totalPrice);
    }

    @FXML
    private void checkout() {
        if (cartService.checkout(cartId)) {
            showAlert("Success", "Order placed successfully!", Alert.AlertType.INFORMATION);
            getSceneManager().switchScene("/ui/views/order.fxml"); // Redirects to orders page
        } else {
            showAlert("Error", "Failed to place order.", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }

    @FXML
    private void removeSelected() {
        int selectedIndex = cartListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            cartService.removeItemFromCart(cartId, selectedIndex);
            loadCartItems();
        } else {
            showAlert("Error", "No item selected.", Alert.AlertType.ERROR);
        }
    }
}
