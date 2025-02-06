package repository.Order;

import data.IDB;
import models.Order;
import models.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private final IDB db;

    public OrderRepository(IDB db) {
        this.db = db;
    }

    public boolean createOrder(Order order) {
        try (Connection con = db.getConnection()) {
            String sql = "INSERT INTO orders (user_id, total_price) VALUES (?, ?)";
            PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, order.getUser().getUserId());
            st.setDouble(2, order.getTotalPrice());

            int affectedRows = st.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = st.getGeneratedKeys();
                if (generatedKeys.next()) {
                    long orderId = generatedKeys.getLong(1);
                    for (OrderItem item : order.getOrderItems()) {
                        addOrderItem(orderId, item);
                    }
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    private void addOrderItem(long orderId, OrderItem item) {
        try (Connection con = db.getConnection()) {
            String sql = "INSERT INTO order_items (order_id, item_id, quantity, price_per_item) VALUES (?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setLong(1, orderId);
            st.setLong(2, item.getItem().getItemId());
            st.setInt(3, item.getQuantity());
            st.setDouble(4, item.getPricePerItem());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }
}
