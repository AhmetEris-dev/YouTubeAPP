package com.ahmete.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Veritabanı bağlantısını sağlayan sınıftır.
 * Singleton tasarım deseni ile tek bir veritabanı bağlantısı sağlanır.
 */
public class ConnectionProvider implements AutoCloseable {

    // Singleton örneğini tutan static değişken.
    private static ConnectionProvider instance;

    // Veritabanı bağlantısını yöneten değişken.
    private Connection connection;

    // Private constructor ile sınıfın dışarıdan nesne oluşturulması engellenir.
    private ConnectionProvider(){
        this.connection = DatabaseConnection.getInstance().getConnection();
    }

    /**
     * Singleton instance'ını almak için kullanılan metod.
     * @return ConnectionProvider Singleton instance
     */
    public static synchronized ConnectionProvider getInstance() {
        if (instance == null) {
            instance = new ConnectionProvider();
        }
        return instance;
    }

    /**
     * Verilen SQL sorgusu için PreparedStatement döner.
     * @param sql SQL sorgusu
     * @return PreparedStatement SQL sorgusunu çalıştırmak için kullanılır
     * @throws SQLException SQL hatası
     */
    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        if (connection == null || connection.isClosed()) {

            throw new SQLException("Connection is closed");
        }
        return connection.prepareStatement(sql);
    }

    /**
     * Veritabanı bağlantısını sağlar.
     * @return Connection Veritabanı bağlantısı
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Veritabanı bağlantısını kapatır.
     */
    @Override
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
  
}