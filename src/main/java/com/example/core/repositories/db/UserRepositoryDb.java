package com.example.core.repositories.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.core.data.entities.Paiement;
import com.example.core.data.entities.User;
import com.example.core.repositories.list.interfaces.IUserRepository;
import com.example.core.repository.impl.RepositoryDb;

public class UserRepositoryDb extends RepositoryDb<User> implements IUserRepository {

    public UserRepositoryDb() {
        super("users", User.class);
    }

    @Override
    public User findByLogin(String login) {
        String sql = "SELECT * FROM " + tableName + " WHERE login = ?";
        User user = null;

        this.getConnection(); // Ouvrir la connexion
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setString(1, login);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    try {
                        user = convertToObject(rs); // Convertir les données en un objet User
                    } catch (IllegalAccessException e) {
                        e.printStackTrace(); // Gérer l'exception IllegalAccessException ici
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(); // Fermer la connexion après utilisation
        }
        return user;
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        String query = "SELECT * FROM " + tableName + " WHERE login = ? AND password = ?";
        User user = null;

        this.getConnection();
        try (PreparedStatement stmt = this.connection.prepareStatement(query)) {
            stmt.setString(1, login);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    try {
                        user = convertToObject(rs);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } // L'exception est maintenant propagée
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();
        }
        return user;
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        User user = null;

        this.getConnection();  // Obtenir la connexion
        try (PreparedStatement stmt = this.connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    try {
                        user = convertToObject(rs);
                    } catch (IllegalAccessException ex) {
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection();  // Fermer la connexion
        }
        return user;
    }
}
