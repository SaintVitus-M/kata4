package software.ulpgc.katas;

import software.ulpgc.katas.architecture.io.SelectTitlesDao;
import software.ulpgc.katas.architecture.io.Selection;
import software.ulpgc.katas.architecture.model.RatedTitle;
import software.ulpgc.katas.architecture.view.SystemTitleDisplay;
import software.ulpgc.katas.architecture.view.TitleDisplay;

public class Main {
    public static void main(String[] args) {
        try (Selection dbQuery = new SelectTitlesDao()){
            TitleDisplay display = new SystemTitleDisplay();
            for (RatedTitle title : dbQuery.select()) {
                display.show(title);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}