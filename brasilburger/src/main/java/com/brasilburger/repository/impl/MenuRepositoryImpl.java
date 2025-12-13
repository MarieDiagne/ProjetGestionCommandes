
package com.brasilburger.repository.impl;

import com.brasilburger.model.Menu;
import com.brasilburger.repository.IMenuRepository;
import com.brasilburger.utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuRepositoryImpl implements IMenuRepository {

    private final Connection connection;

    public MenuRepositoryImpl() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    @Override
    public Menu create(Menu menu) {
        String sql = "INSERT INTO menu (nom, image, archive) VALUES (?, ?, ?) RETURNING id";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, menu.getNom());
            stmt.setString(2, menu.getImage());
            stmt.setBoolean(3, menu.getArchive() != null ? menu.getArchive() : false);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                menu.setId(rs.getInt("id"));
                return menu;
            }
            return null;
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la création du menu : " + e.getMessage());
            return null;
        }
    }

    @Override
    public Menu findById(Integer id) {
        String sql = "SELECT * FROM menu WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToMenu(rs);
            }
            return null;
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la recherche du menu : " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Menu> findAll() {
        String sql = "SELECT * FROM menu ORDER BY id DESC";
        List<Menu> menus = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                menus.add(mapResultSetToMenu(rs));
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la récupération des menus : " + e.getMessage());
        }

        return menus;
    }

    @Override
    public List<Menu> findAllNonArchived() {
        String sql = "SELECT * FROM menu WHERE archive = FALSE ORDER BY id DESC";
        List<Menu> menus = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                menus.add(mapResultSetToMenu(rs));
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la récupération des menus : " + e.getMessage());
        }

        return menus;
    }

    @Override
    public Menu update(Menu menu) {
        String sql = "UPDATE menu SET nom = ?, image = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, menu.getNom());
            stmt.setString(2, menu.getImage());
            stmt.setInt(3, menu.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0 ? menu : null;
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la mise à jour du menu : " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean archive(Integer id) {
        String sql = "UPDATE menu SET archive = TRUE WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de l'archivage du menu : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM menu WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la suppression du menu : " + e.getMessage());
            return false;
        }
    }

    private Menu mapResultSetToMenu(ResultSet rs) throws SQLException {
        return new Menu(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getString("image"),
                rs.getBoolean("archive"));
    }
}