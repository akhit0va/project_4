package repository.Cart;

import data.IDB;
import models.CartItem;
import models.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartRepository {
    private final IDB db;

    public CartRepository(IDB db) {
        this.db = db;
    }

    public boolean addItemToCart(long cartId, long itemId, int quantity) {
        try (Connection con = db.getConnection()) {
            String sql = "INSERT INTO cart_items (cart_id, item_id, quantity) VALUES (?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setLong(1, cartId);
            st.setLong(2, itemId);
            st.setInt(3, quantity);

            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    public List<CartItem> getCartItems(long cartId) {
        List<CartItem> cartItems = new ArrayList<>();
        try (Connection con = db.getConnection()) {
            String sql = "SELECT ci.quantity, i.* FROM cart_items ci JOIN items i ON ci.item_id = i.item_id WHERE ci.cart_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setLong(1, cartId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Item item = new Item(
                        rs.getLong("item_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                );
                cartItems.add(new CartItem(null, item, rs.getInt("quantity")));
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return cartItems;
    }

    public boolean checkoutCart(long cartId) {
        try (Connection con = db.getConnection()) {
            // Create Order
            String createOrderSQL = "INSERT INTO orders (user_id, total_price) SELECT c.user_id, SUM(i.price * ci.quantity) FROM cart c JOIN cart_items ci ON c.cart_id = ci.cart_id JOIN items i ON ci.item_id = i.item_id WHERE c.cart_id = ? RETURNING order_id";
            PreparedStatement orderStatement = con.prepareStatement(createOrderSQL);
            orderStatement.setLong(1, cartId);
            ResultSet rs = orderStatement.executeQuery();

            if (rs.next()) {
                long orderId = rs.getLong("order_id");

                // Transfer items to order_items
                String transferSQL = "INSERT INTO order_items (order_id, item_id, quantity, price_per_item) SELECT ?, ci.item_id, ci.quantity, i.price FROM cart_items ci JOIN items i ON ci.item_id = i.item_id WHERE ci.cart_id = ?";
                PreparedStatement transferStatement = con.prepareStatement(transferSQL);
                transferStatement.setLong(1, orderId);
                transferStatement.setLong(2, cartId);
                transferStatement.executeUpdate();

                // Clear cart
                String clearCartSQL = "DELETE FROM cart_items WHERE cart_id = ?";
                PreparedStatement clearStatement = con.prepareStatement(clearCartSQL);
                clearStatement.setLong(1, cartId);
                clearStatement.executeUpdate();

                return true;
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    public void removeItemFromCart(long cartId, int index) {
    }
}
