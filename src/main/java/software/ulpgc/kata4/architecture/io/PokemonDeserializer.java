package software.ulpgc.kata4.architecture.io;

import software.ulpgc.kata4.architecture.model.Pokemon;

public interface PokemonDeserializer {
    Pokemon deserialize(String line);
}
