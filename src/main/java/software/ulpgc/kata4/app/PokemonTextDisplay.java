package software.ulpgc.kata4.app;

import software.ulpgc.kata4.architecture.model.Pokemon;
import software.ulpgc.kata4.architecture.view.PokemonDislay;

public class PokemonTextDisplay implements PokemonDislay {
    @Override
    public void show(Pokemon pokemon) {
        System.out.println(pokemon);
    }
}
