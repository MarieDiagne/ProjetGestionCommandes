package com.brasilburger.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance;
    private Connection connection;

    private static final String URL =
        "jdbc:postgresql://ep-icy-sound-a44jlj19-pooler.us-east-1.aws.neon.tech:5432/neondb"
        + "?sslmode=require&channel_binding=require";

    private static final String USER = "neondb_owner";
    private static final String PASSWORD = "npg_Rf6xZiG9gEkA";

    private DBConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connexion PostgreSQL réussie !");
        } catch (SQLException e) {
            System.err.println("❌ Erreur de connexion à la base de données");
            throw new RuntimeException("Impossible de se connecter à la base de données", e);
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
