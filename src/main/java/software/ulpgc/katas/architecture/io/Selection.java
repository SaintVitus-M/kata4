package software.ulpgc.katas.architecture.io;

import software.ulpgc.katas.architecture.model.RatedTitle;

import java.util.List;

public interface Selection extends AutoCloseable{
    List<RatedTitle> select();
}
