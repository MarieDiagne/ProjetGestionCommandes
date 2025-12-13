package com.brasilburger.repository.impl;

import com.brasilburger.model.Burger;
import com.brasilburger.repository.IBurgerRepository;
import com.brasilburger.utils.DBConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BurgerRepositoryImpl implements IBurgerRepository {

    private final Connection connection;

    public BurgerRepositoryImpl() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    @Override
    public Burger create(Burger burger) {
        String sql = "INSERT INTO burger (nom, description, prix, image, archive) VALUES (?, ?, ?, ?, ?) RETURNING id";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, burger.getNom());
            stmt.setString(2, burger.getDescription());
            stmt.setBigDecimal(3, burger.getPrix());
            stmt.setString(4, burger.getImage());
            stmt.setBoolean(5, burger.getArchive() != null ? burger.getArchive() : false);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                burger.setId(rs.getInt("id"));
                return burger;
            }
            return null;
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la création du burger : " + e.getMessage());
            return null;
        }
    }

    @Override
    public Burger findById(Integer id) {
        String sql = "SELECT * FROM burger WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToBurger(rs);
            }
            return null;
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la recherche du burger : " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Burger> findAll() {
        String sql = "SELECT * FROM burger ORDER BY id DESC";
        List<Burger> burgers = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                burgers.add(mapResultSetToBurger(rs));
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la récupération des burgers : " + e.getMessage());
        }

        return burgers;
    }

    @Override
    public List<Burger> findAllNonArchived() {
        String sql = "SELECT * FROM burger WHERE archive = FALSE ORDER BY id DESC";
        List<Burger> burgers = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                burgers.add(mapResultSetToBurger(rs));
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la récupération des burgers : " + e.getMessage());
        }

        return burgers;
    }

    @Override
    public Burger update(Burger burger) {
        String sql = "UPDATE burger SET nom = ?, description = ?, prix = ?, image = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, burger.getNom());
            stmt.setString(2, burger.getDescription());
            stmt.setBigDecimal(3, burger.getPrix());
            stmt.setString(4, burger.getImage());
            stmt.setInt(5, burger.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0 ? burger : null;
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la mise à jour du burger : " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean archive(Integer id) {
        String sql = "UPDATE burger SET archive = TRUE WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de l'archivage du burger : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM burger WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la suppression du burger : " + e.getMessage());
            System.err.println("   (Le burger est peut-être utilisé dans des compositions)");
            return false;
        }
    }

   
    private Burger mapResultSetToBurger(ResultSet rs) throws SQLException {
        return new Burger(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getString("description"),
                rs.getBigDecimal("prix"),
                rs.getString("image"),
                rs.getBoolean("archive"));
    }
}