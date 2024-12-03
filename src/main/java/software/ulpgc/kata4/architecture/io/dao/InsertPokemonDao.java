package software.ulpgc.kata4.architecture.io.dao;

import software.ulpgc.kata4.app.JDBCUtils;
import software.ulpgc.kata4.architecture.model.Pokemon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class InsertPokemonDao implements PokemonWriter, AutoCloseable{
    private final Connection connection;
    private final PreparedStatement insertStatemment;
    private static final String InsertRecordStatement = """
            INSERT INTO pokedex (idx, pokemon_id, name, generation, status, type_1, type_2)
            VALUES (?,?,?,?,?,?,?)
            """;

    public InsertPokemonDao() throws SQLException {
        this.connection = JDBCUtils.getConnection();
        this.connection.setAutoCommit(false);
        this.insertStatemment = connection.prepareStatement(InsertRecordStatement);
    }

    @Override
    public void close() throws Exception {
        this.connection.commit();
    }

    @Override
    public void write(Pokemon pokemon) {
        try {
            updateStatementWith(pokemon);
            insertStatemment.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateStatementWith(Pokemon pokemon) throws SQLException{
        for(Parameter parameter : toParameters(pokemon)) {
            updateStatementWith(parameter);
        }
    }

    private void updateStatementWith(Parameter parameter) throws SQLException {
        if(isNull(parameter.value)) {
            insertStatemment.setNull(parameter.id, parameter.type);
        } else {
            insertStatemment.setObject(parameter.id, parameter.value);
        }
    }

    private boolean isNull(Object value) {return value instanceof Integer && (Integer) value == -1;}

    private List<Parameter> toParameters(Pokemon pokemon) {
        return List.of(
                new Parameter(1, pokemon.idx(), Types.INTEGER),
                new Parameter(2, pokemon.pokemonId(), Types.INTEGER),
                new Parameter(3, pokemon.name(), Types.LONGNVARCHAR),
                new Parameter(4, pokemon.generation(), Types.INTEGER),
                new Parameter(5, pokemon.status(), Types.LONGNVARCHAR),
                new Parameter(6, pokemon.type_1(), Types.LONGNVARCHAR),
                new Parameter(7, pokemon.type_2(), Types.LONGNVARCHAR)
        );
    }

    private record Parameter(int id, Object value, int type) {}
}
