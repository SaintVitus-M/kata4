package software.ulpgc.kata4.architecture.control;

import software.ulpgc.kata4.app.PokemonTextDisplay;
import software.ulpgc.kata4.architecture.io.CsvPokemonDeserializer;
import software.ulpgc.kata4.architecture.io.FilePokemonReader;
import software.ulpgc.kata4.architecture.io.PokemonReader;
import software.ulpgc.kata4.architecture.io.dao.*;
import software.ulpgc.kata4.architecture.model.Pokemon;
import software.ulpgc.kata4.architecture.view.PokemonDislay;

import java.io.File;
import java.io.IO;
import java.io.IOException;
import java.sql.SQLException;

public class DisplayCommand implements Command {

    @Override
    public void execute() {
        try {
            PokemonDislay display = new PokemonTextDisplay();
            File input = new File("src/main/resources/pokedex.csv");
            File output = new File("pokedex.db");
            if(!output.exists()) {
                initDatabase();
                insertDataFrom(input);
            }
            Select selection = new SelectPokemonDao();
            for(Pokemon pokemon : selection.select()) {
                display.show(pokemon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertDataFrom(File input) throws Exception{
        try(PokemonReader reader = createPokemonReader(input);
        PokemonWriter writer = createPokemonWriter()) {
            while (true) {
                Pokemon pokemon = reader.read();
                if(pokemon==null) break;
                writer.write(pokemon);
            }
        }
    }

    private PokemonWriter createPokemonWriter() throws SQLException{
        return new InsertPokemonDao();
    }

    private PokemonReader createPokemonReader(File input) throws IOException {
        return new FilePokemonReader(input, new CsvPokemonDeserializer());
    }

    private void initDatabase() throws Exception {
        try(DatabaseInit initializer = createDatabaseInit()) {
            initializer.init();
        }
    }

    private DatabaseInit createDatabaseInit() throws SQLException{
        return new CreateDatabaseDao();
    }
}
