package software.ulpgc.katas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    private static final String DATABASE_LOCATION = "src/main/resources/ratings.db";
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlite:" + DATABASE_LOCATION;
        return DriverManager.getConnection(url);
    }
}
