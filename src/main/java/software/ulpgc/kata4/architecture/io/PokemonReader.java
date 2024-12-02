package software.ulpgc.kata4.architecture.io;

import software.ulpgc.kata4.architecture.model.Pokemon;

import java.io.IOException;

public interface PokemonReader extends AutoCloseable {
    Pokemon read() throws IOException;
}
