package software.ulpgc.kata4.architecture.io.dao;

import software.ulpgc.kata4.architecture.model.Pokemon;

public interface PokemonWriter extends AutoCloseable{
    void write(Pokemon pokemon);
}
