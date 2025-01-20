package software.ulpgc.katas.architecture.io;

public interface Deserializer<Source, Target> {
    Target deserialize(Source content);
}
