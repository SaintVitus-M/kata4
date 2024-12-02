package software.ulpgc.kata4.architecture.io;

import software.ulpgc.kata4.architecture.model.Pokemon;

public class CsvPokemonDeserializer implements PokemonDeserializer {

    @Override
    public Pokemon deserialize(String line) {
        return deserialize(line.split(","));
    }

    private Pokemon deserialize(String[] fields) {
        return new Pokemon(
                toInt(fields[0]),
                toInt(fields[1]),
                fields[2],
                toInt(fields[5]),
                fields[6],
                fields[9],
                fields[10]
        );
    }

    private int toInt(String field) {
        try {
            return Integer.parseInt(field);
        } catch(NumberFormatException e) {
            return -1;
        }
    }
}
