package software.ulpgc.katas.architecture.io;


import software.ulpgc.katas.architecture.model.RatedTitle;

public class TitleDeserializer implements Deserializer<String, RatedTitle> {
    @Override
    public RatedTitle deserialize(String line) {
        return toTitle(line);
    }

    private RatedTitle toTitle(String line) {
        return toTitle(line.split("\t"));
    }

    private RatedTitle toTitle(String[] split) {
        return new RatedTitle(
                split[0],
                toDouble(split[1]),
                toInt(split[2])
        );
    }

    private int toInt(String s) {
        return Integer.parseInt(s);
    }

    private double toDouble(String s) {return Double.parseDouble(s);}
}
