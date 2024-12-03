package software.ulpgc.kata4.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    private static final String DATABASE_LOCATION = "pokedex.db";

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:" + DATABASE_LOCATION;
        return DriverManager.getConnection(url);
    }
}
