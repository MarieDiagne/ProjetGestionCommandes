// ========== Implémentation ==========
package com.brasilburger.repository.impl;

import com.brasilburger.model.Composition;
import com.brasilburger.model.Menu;
import com.brasilburger.model.Burger;
import com.brasilburger.model.Complement;
import com.brasilburger.repository.ICompositionRepository;
import com.brasilburger.utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompositionRepositoryImpl implements ICompositionRepository {

    private final Connection connection;

    public CompositionRepositoryImpl() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    @Override
    public Composition create(Composition composition) {
        String sql = "INSERT INTO composition (menu_id, burger_id, boisson_id, frites_id) VALUES (?, ?, ?, ?) RETURNING id";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, composition.getMenuId());
            stmt.setInt(2, composition.getBurgerId());
            stmt.setInt(3, composition.getBoissonId());
            stmt.setInt(4, composition.getFritesId());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                composition.setId(rs.getInt("id"));
                return composition;
            }
            return null;
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la création de la composition : " + e.getMessage());
            if (e.getMessage().contains("boisson_id") || e.getMessage().contains("BOISSON")) {
                System.err.println("   Le complément sélectionné pour la boisson n'est pas de type BOISSON.");
            }
            if (e.getMessage().contains("frites_id") || e.getMessage().contains("FRITES")) {
                System.err.println("   Le complément sélectionné pour les frites n'est pas de type FRITES.");
            }
            return null;
        }
    }

    @Override
    public Composition findById(Integer id) {
        String sql = "SELECT c.*, " +
                "m.nom as menu_nom, m.image as menu_image, m.archive as menu_archive, " +
                "b.nom as burger_nom, b.description as burger_desc, b.prix as burger_prix, b.image as burger_image, b.archive as burger_archive, "
                +
                "bo.nom as boisson_nom, bo.type as boisson_type, bo.prix as boisson_prix, bo.image as boisson_image, bo.archive as boisson_archive, "
                +
                "f.nom as frites_nom, f.type as frites_type, f.prix as frites_prix, f.image as frites_image, f.archive as frites_archive "
                +
                "FROM composition c " +
                "LEFT JOIN menu m ON c.menu_id = m.id " +
                "LEFT JOIN burger b ON c.burger_id = b.id " +
                "LEFT JOIN complement bo ON c.boisson_id = bo.id " +
                "LEFT JOIN complement f ON c.frites_id = f.id " +
                "WHERE c.id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToComposition(rs);
            }
            return null;
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la recherche de la composition : " + e.getMessage());
            return null;
        }
    }

    @Override
    public Composition findByMenuId(Integer menuId) {
        String sql = "SELECT c.*, " +
                "m.nom as menu_nom, m.image as menu_image, m.archive as menu_archive, " +
                "b.nom as burger_nom, b.description as burger_desc, b.prix as burger_prix, b.image as burger_image, b.archive as burger_archive, "
                +
                "bo.nom as boisson_nom, bo.type as boisson_type, bo.prix as boisson_prix, bo.image as boisson_image, bo.archive as boisson_archive, "
                +
                "f.nom as frites_nom, f.type as frites_type, f.prix as frites_prix, f.image as frites_image, f.archive as frites_archive "
                +
                "FROM composition c " +
                "LEFT JOIN menu m ON c.menu_id = m.id " +
                "LEFT JOIN burger b ON c.burger_id = b.id " +
                "LEFT JOIN complement bo ON c.boisson_id = bo.id " +
                "LEFT JOIN complement f ON c.frites_id = f.id " +
                "WHERE c.menu_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, menuId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToComposition(rs);
            }
            return null;
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la recherche de la composition : " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Composition> findAll() {
        String sql = "SELECT c.*, " +
                "m.nom as menu_nom, m.image as menu_image, m.archive as menu_archive, " +
                "b.nom as burger_nom, b.description as burger_desc, b.prix as burger_prix, b.image as burger_image, b.archive as burger_archive, "
                +
                "bo.nom as boisson_nom, bo.type as boisson_type, bo.prix as boisson_prix, bo.image as boisson_image, bo.archive as boisson_archive, "
                +
                "f.nom as frites_nom, f.type as frites_type, f.prix as frites_prix, f.image as frites_image, f.archive as frites_archive "
                +
                "FROM composition c " +
                "LEFT JOIN menu m ON c.menu_id = m.id " +
                "LEFT JOIN burger b ON c.burger_id = b.id " +
                "LEFT JOIN complement bo ON c.boisson_id = bo.id " +
                "LEFT JOIN complement f ON c.frites_id = f.id " +
                "ORDER BY c.id DESC";

        List<Composition> compositions = new ArrayList<>();

        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                compositions.add(mapResultSetToComposition(rs));
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la récupération des compositions : " + e.getMessage());
        }

        return compositions;
    }

    @Override
    public Composition update(Composition composition) {
        String sql = "UPDATE composition SET burger_id = ?, boisson_id = ?, frites_id = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, composition.getBurgerId());
            stmt.setInt(2, composition.getBoissonId());
            stmt.setInt(3, composition.getFritesId());
            stmt.setInt(4, composition.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0 ? composition : null;
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la mise à jour de la composition : " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM composition WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la suppression de la composition : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteByMenuId(Integer menuId) {
        String sql = "DELETE FROM composition WHERE menu_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, menuId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la suppression de la composition : " + e.getMessage());
            return false;
        }
    }

    private Composition mapResultSetToComposition(ResultSet rs) throws SQLException {
        Composition composition = new Composition(
                rs.getInt("id"),
                rs.getInt("menu_id"),
                rs.getInt("burger_id"),
                rs.getInt("boisson_id"),
                rs.getInt("frites_id"));

        // Charger les relations si disponibles
        Menu menu = new Menu(
                rs.getInt("menu_id"),
                rs.getString("menu_nom"),
                rs.getString("menu_image"),
                rs.getBoolean("menu_archive"));
        composition.setMenu(menu);

        Burger burger = new Burger(
                rs.getInt("burger_id"),
                rs.getString("burger_nom"),
                rs.getString("burger_desc"),
                rs.getBigDecimal("burger_prix"),
                rs.getString("burger_image"),
                rs.getBoolean("burger_archive"));
        composition.setBurger(burger);

        Complement boisson = new Complement(
                rs.getInt("boisson_id"),
                rs.getString("boisson_nom"),
                com.brasilburger.enums.TypeComplementEnum.fromString(rs.getString("boisson_type")),
                rs.getString("boisson_image"),
                rs.getBigDecimal("boisson_prix"),
                rs.getBoolean("boisson_archive"));
        composition.setBoisson(boisson);

        Complement frites = new Complement(
                rs.getInt("frites_id"),
                rs.getString("frites_nom"),
                com.brasilburger.enums.TypeComplementEnum.fromString(rs.getString("frites_type")),
                rs.getString("frites_image"),
                rs.getBigDecimal("frites_prix"),
                rs.getBoolean("frites_archive"));
        composition.setFrites(frites);

        return composition;
    }
}