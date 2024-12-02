package software.ulpgc.kata4.architecture.model;

public record Pokemon(
        int idx,
        int pokemonId,
        String name,
        int generation,
        String status,
        String type_1,
        String type_2
) {
    @Override
    public String toString() {
        return idx + "\t" +
                pokemonId + "\t" +
                name + "\t" +
                generation + "\t" +
                status + "\t" +
                type_1 + "\t" +
                type_2;
    }
}
