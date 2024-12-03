package software.ulpgc.kata4.architecture.io.dao;

import software.ulpgc.kata4.app.JDBCUtils;
import software.ulpgc.kata4.architecture.model.Pokemon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectPokemonDao implements Select, AutoCloseable {
    private Connection connection;
    private static final String SelectQuery = """
            SELECT * FROM pokedex
            """;

    public SelectPokemonDao() throws SQLException {
        this.connection = JDBCUtils.getConnection();
        this.connection.setAutoCommit(false);
    }

    @Override
    public void close() throws Exception {
        this.connection.commit();
    }

    @Override
    public List<Pokemon> select() {
        return getPokemons();
    }

    private List<Pokemon> getPokemons() {
        List<Pokemon> pokemonList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SelectQuery);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Pokemon pokemon = new Pokemon(
                        resultSet.getInt("idx"),
                        resultSet.getInt("pokemon_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("generation"),
                        resultSet.getString("status"),
                        resultSet.getString("type_1"),
                        resultSet.getString("type_2")
                );
                pokemonList.add(pokemon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pokemonList;
    }
}
