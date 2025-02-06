package repository.User;

import data.IDB;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final IDB db;  // Dependency Injection

    public UserRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createUser(User user) {
        try (Connection con = db.getConnection()) {
            String sql = "INSERT INTO users(username, email, password_hash) VALUES (?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, user.getUsername());
            st.setString(2, user.getEmail());
            st.setString(3, user.getPasswordHash());

            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public User getUser(int id) {
        try (Connection con = db.getConnection()) {
            String sql = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password_hash")
                );
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        try (Connection con = db.getConnection()) {
            String sql = "UPDATE users SET username = ?, email = ?, password_hash = ? WHERE user_id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, user.getUsername());
            st.setString(2, user.getEmail());
            st.setString(3, user.getPasswordHash());
            st.setInt(4, user.getUserId());

            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection con = db.getConnection()) {
            String sql = "SELECT * FROM users";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password_hash")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return users;
    }
}
