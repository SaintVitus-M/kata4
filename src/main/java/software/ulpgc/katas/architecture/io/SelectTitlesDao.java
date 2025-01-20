package software.ulpgc.katas.architecture.io;

import software.ulpgc.katas.JDBCUtils;
import software.ulpgc.katas.architecture.model.RatedTitle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectTitlesDao implements AutoCloseable, Selection{
    private Connection connection;
    private static final String SelectQuery = """
            SELECT * FROM title_ratings
            """;

    public SelectTitlesDao() throws SQLException {
        this.connection = JDBCUtils.getConnection();
        this.connection.setAutoCommit(false);
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }

    @Override
    public List<RatedTitle> select() {
        return doSelection();
    }

    private List<RatedTitle> doSelection() {
        List<RatedTitle> titles = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SelectQuery);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                RatedTitle title = new RatedTitle(
                        resultSet.getString("tConst"),
                        resultSet.getDouble("rating"),
                        resultSet.getInt("num_votes")
                );
                titles.add(title);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return titles;
    }
}
