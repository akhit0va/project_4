package repository.Item;

import data.IDB;
import models.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemRepository {
    private final IDB db;

    public ItemRepository(IDB db) {
        this.db = db;
    }

    public boolean createItem(Item item) {
        try (Connection con = db.getConnection()) {
            String sql = "INSERT INTO items (name, description, price, stock) VALUES (?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, item.getName());
            st.setString(2, item.getDescription());
            st.setDouble(3, item.getPrice());
            st.setInt(4, item.getStock());

            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        try (Connection con = db.getConnection()) {
            String sql = "SELECT * FROM items";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                items.add(new Item(
                        rs.getLong("item_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return items;
    }
}
