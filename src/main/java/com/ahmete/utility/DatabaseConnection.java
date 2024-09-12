package com.ahmete.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Veritabanı bağlantısını yöneten Singleton sınıfıdır.
 * Bu sınıf, uygulama genelinde tek bir veritabanı bağlantısı sağlar ve bağlantıyı yönetir.
 */
public class DatabaseConnection {

    // Singleton nesnesi için statik referans.
    private static DatabaseConnection instance;

    // Veritabanı bağlantı bilgileri.
    public static final String dbName = "YoutubeAppDB";
    public static final String url = "jdbc:postgresql://localhost:5432/" + dbName;
    public static final String username = "postgres";
    public static final String password = "root";

    // Veritabanı bağlantı nesnesi.
    private Connection connection;

    /**
     * Private constructor ile dışarıdan nesne oluşturulması engellenir.
     * Bu constructor, veritabanına bağlantı kurar.
     */
    private DatabaseConnection() {
        try {
            // Veritabanı bağlantısını oluşturur.
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            // Bağlantı oluşturulurken bir hata oluşursa, bu hata dışarı fırlatılır.
            throw new RuntimeException("Veritabanı bağlantısı oluşturulamadı", e);
        }
    }

    /**
     * Singleton instance'ını döndüren metod.
     * Eğer instance daha önce oluşturulmamışsa, yeni bir instance oluşturur.
     * @return Singleton DatabaseConnection instance.
     */
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    /**
     * Veritabanı bağlantısını döndürür.
     * Eğer bağlantı mevcutsa, bu metod bağlantıyı sağlar.
     * @return Veritabanı bağlantısı.
     */

    public Connection getConnection() {
        try {
            if(connection.isClosed()) {
            connection = ConnectionProvider.getInstance().getConnection();
        }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return connection;


    }
}