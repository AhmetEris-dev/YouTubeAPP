package com.ahmete.utility;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Veritabanı şemasını oluşturmak için kullanılan sınıftır.
 * Bu sınıf, gerekli tabloları veritabanında oluşturur.
 */
public class DatabaseSchema {

    /**
     * Tabloları oluşturur.
     */
    public static void createTables() {
        // "User" tablosu için SQL sorgusu
        String createUserTable = "CREATE TABLE IF NOT EXISTS tbl_user (" +
                "id BIGSERIAL PRIMARY KEY, " + // PostgreSQL'de BIGSERIAL kullanılır
                "name VARCHAR(255), " +
                "surname varchar(255), " +
                "email varchar(255) UNIQUE," +
                "username varchar(30) UNIQUE, " +
                "password varchar(16),"+
                "state INTEGER  DEFAULT 1, " +
                "createat BIGINT DEFAULT EXTRACT(epoch FROM now()), " +
                "updateat BIGINT DEFAULT EXTRACT(epoch FROM now()) " +
                ");";

        // "video" tablosu için SQL sorgusu
        String createVideoTable = "CREATE TABLE IF NOT EXISTS tbl_video (" +
                "id BIGSERIAL PRIMARY KEY, " + // PostgreSQL'de BIGSERIAL kullanılır
                "user_id BIGINT NOT NULL REFERENCES tbl_user(id) , " +
                "title varchar(255), " +
                "description varchar(255) , " +
                "state INTEGER DEFAULT 1, " +
                "createat BIGINT DEFAULT EXTRACT(epoch FROM now()), " +
                "updateat BIGINT DEFAULT EXTRACT(epoch FROM now()) " +
                ");";
        //"Like" tablosu için SQL sorgusu
        String createLikeTable="CREATE TABLE IF NOT EXISTS tbl_like (" +
                "id BIGSERIAL PRIMARY KEY,"+
                "user_id BIGINT NOT NULL REFERENCES tbl_user(id), " +
                "video_id BIGINT NOT NULL REFERENCES tbl_video(id), " +
                "status INTEGER DEFAULT 0, " +
                "state INTEGER DEFAULT 1, " +
                "createat BIGINT DEFAULT EXTRACT(epoch FROM now()), " +
                "updateat BIGINT DEFAULT EXTRACT(epoch FROM now()) " +
                ");";
        //"comment" tablosu için SQL sorgusu
        String createCommentTable="CREATE TABLE IF NOT EXISTS tbl_comment (" +
                "id BIGSERIAL PRIMARY KEY,"+
                "user_id BIGINT NOT NULL REFERENCES tbl_user(id), " +
                "video_id BIGINT NOT NULL REFERENCES tbl_video(id), " +
                "commenttext text DEFAULT 1, " +
                "status INTEGER DEFAULT 0, " +
                "state INTEGER DEFAULT 1, " +
                "createat BIGINT DEFAULT EXTRACT(epoch FROM now()), " +
                "updateat BIGINT DEFAULT EXTRACT(epoch FROM now()) " +
                ");";

        try {
            ConnectionProvider connectionProvider = ConnectionProvider.getInstance();
            Connection connection = connectionProvider.getConnection(); // getConnection() metodu eklenmiş
            Statement statement = connection.createStatement();
            // Tablo oluşturma sorgularını çalıştırıyoruz
            statement.execute(createUserTable);
            statement.execute(createVideoTable);
            statement.execute(createLikeTable);
            statement.execute(createCommentTable);
            System.out.println("Tables created successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}