package software.ulpgc.kata4.architecture.io.dao;

import software.ulpgc.kata4.architecture.model.Pokemon;

import java.util.List;

public interface Select {
    List<Pokemon> select();
}
