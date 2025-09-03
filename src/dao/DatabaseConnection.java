package dao;

import javax.swing.filechooser.FileSystemView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DATABASE_PATH = String.format("jdbc:sqlite:%s", FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "/LineUpLabDatabase.db");

    public static Connection getConn() throws SQLException {
        return DriverManager.getConnection(DATABASE_PATH);
    }

    public static void initTables() throws SQLException {
        try (Connection conn = getConn()) {
            PreparedStatement ps = conn.prepareStatement(
                """
                CREATE TABLE IF NOT EXISTS 'Jogadores' (
                    'Id' INTEGER NOT NULL UNIQUE,
                    'Nome' VARCHAR NOT NULL,
                    'NumCamisa' TINYINT NOT NULL,
                    PRIMARY KEY('id')
                );
                """
            );

            ps.executeUpdate();
        }
    }
}
