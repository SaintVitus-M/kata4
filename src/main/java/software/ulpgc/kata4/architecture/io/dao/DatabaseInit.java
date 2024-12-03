package software.ulpgc.kata4.architecture.io.dao;

public interface DatabaseInit extends AutoCloseable {
    void init();
}
