package software.ulpgc.kata4.architecture.io;

import software.ulpgc.kata4.architecture.model.Pokemon;

import java.io.*;

public class FilePokemonReader implements PokemonReader, AutoCloseable {
    private final PokemonDeserializer deserializer;
    private final BufferedReader reader;

    public FilePokemonReader(File file, PokemonDeserializer deserializer) throws IOException {
        this.deserializer = deserializer;
        this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        this.reader.readLine();
    }

    @Override
    public void close() throws Exception {
        this.reader.close();
    }

    @Override
    public Pokemon read() throws IOException {
        return deserialize(reader.readLine());
    }

    private Pokemon deserialize(String line) {
        return line != null ?
                deserializer.deserialize(line):
                null;
    }
}
