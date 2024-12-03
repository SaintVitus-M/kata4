package software.ulpgc.kata4.architecture.io.dao;

import software.ulpgc.kata4.app.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabaseDao implements DatabaseInit, AutoCloseable {
    private final Connection connection;
    private static final String CreateTableStatement = """
            CREATE TABLE IF NOT EXISTS pokedex (
                idx INTEGER PRIMARY KEY,
                pokemon_id INTEGER,
                name TEXT NOT NULL,
                generation INTEGER,
                status TEXT,
                type_1 TEXT NOT NULL,
                type_2 TEXT
            )
            """;

    public CreateDatabaseDao() throws SQLException {
        this.connection = JDBCUtils.getConnection();
        this.connection.setAutoCommit(false);
    }

    @Override
    public void close() throws Exception {
        this.connection.commit();
    }

    @Override
    public void init() {
        try (Statement statement = connection.createStatement()){
            statement.execute(CreateTableStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
