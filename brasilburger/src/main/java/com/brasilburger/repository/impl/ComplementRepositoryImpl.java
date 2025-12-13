package com.brasilburger.repository.impl;

import com.brasilburger.enums.TypeComplementEnum;
import com.brasilburger.model.Complement;
import com.brasilburger.repository.IComplementRepository;
import com.brasilburger.utils.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class ComplementRepositoryImpl implements IComplementRepository {
    
    private final Connection connection;
    
    public ComplementRepositoryImpl() {
        this.connection = DBConnection.getInstance().getConnection();
    }
    
    @Override
    public Complement create(Complement complement) {
        String sql = "INSERT INTO complement (nom, type, image, prix, archive) VALUES (?, ?::type_complement_enum, ?, ?, ?) RETURNING id";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, complement.getNom());
            stmt.setString(2, complement.getType().getValue());
            stmt.setString(3, complement.getImage());
            stmt.setBigDecimal(4, complement.getPrix());
            stmt.setBoolean(5, complement.getArchive() != null ? complement.getArchive() : false);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                complement.setId(rs.getInt("id"));
                return complement;
            }
            return null;
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la création du complément : " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public Complement findById(Integer id) {
        String sql = "SELECT * FROM complement WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToComplement(rs);
            }
            return null;
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la recherche du complément : " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public List<Complement> findAll() {
        String sql = "SELECT * FROM complement ORDER BY type, id DESC";
        return executeQuery(sql);
    }
    
    @Override
    public List<Complement> findAllNonArchived() {
        String sql = "SELECT * FROM complement WHERE archive = FALSE ORDER BY type, id DESC";
        return executeQuery(sql);
    }
    
    @Override
    public List<Complement> findByType(TypeComplementEnum type) {
        String sql = "SELECT * FROM complement WHERE type = ?::type_complement_enum ORDER BY id DESC";
        return executeQueryWithType(sql, type);
    }
    
    @Override
    public List<Complement> findByTypeNonArchived(TypeComplementEnum type) {
        String sql = "SELECT * FROM complement WHERE type = ?::type_complement_enum AND archive = FALSE ORDER BY id DESC";
        return executeQueryWithType(sql, type);
    }
    
    @Override
    public Complement update(Complement complement) {
        String sql = "UPDATE complement SET nom = ?, type = ?::type_complement_enum, image = ?, prix = ? WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, complement.getNom());
            stmt.setString(2, complement.getType().getValue());
            stmt.setString(3, complement.getImage());
            stmt.setBigDecimal(4, complement.getPrix());
            stmt.setInt(5, complement.getId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0 ? complement : null;
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la mise à jour du complément : " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public boolean archive(Integer id) {
        String sql = "UPDATE complement SET archive = TRUE WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de l'archivage du complément : " + e.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean delete(Integer id) {
        String sql = "DELETE FROM complement WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la suppression du complément : " + e.getMessage());
            System.err.println("   (Le complément est peut-être utilisé dans des compositions)");
            return false;
        }
    }
    
   
    private List<Complement> executeQuery(String sql) {
        List<Complement> complements = new ArrayList<>();
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                complements.add(mapResultSetToComplement(rs));
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la récupération des compléments : " + e.getMessage());
        }
        
        return complements;
    }
    
    
    private List<Complement> executeQueryWithType(String sql, TypeComplementEnum type) {
        List<Complement> complements = new ArrayList<>();
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, type.getValue());
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                complements.add(mapResultSetToComplement(rs));
            }
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la récupération des compléments : " + e.getMessage());
        }
        
        return complements;
    }
    
  
    private Complement mapResultSetToComplement(ResultSet rs) throws SQLException {
        return new Complement(
            rs.getInt("id"),
            rs.getString("nom"),
            TypeComplementEnum.fromString(rs.getString("type")),
            rs.getString("image"),
            rs.getBigDecimal("prix"),
            rs.getBoolean("archive")
        );
    }
}