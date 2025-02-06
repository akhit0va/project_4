package ux;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import models.Order;
import service.OrderService;

import java.util.List;

import static ux.Main.getSceneManager;

public class OrderController {
    @FXML private ListView<String> orderListView;
    @FXML private Button backToCart;
    private OrderService orderService;
    private int userId = 1;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
        loadOrders();
    }

    private void loadOrders() {
        orderListView.getItems().clear();
        List<Order> orders = orderService.getUserOrders(userId);

        for (Order order : orders) {
            String orderDisplay = "Order #" + order.getOrderId() + " - Total: $" + order.getTotalPrice() + " - Status: " + order.getStatus();
            orderListView.getItems().add(orderDisplay);
        }
    }

    @FXML
    private void goBackToCart() {
        getSceneManager().switchScene("/ux/views/menu.fxml");
    }
}
