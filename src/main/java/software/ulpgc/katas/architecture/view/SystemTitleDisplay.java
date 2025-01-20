package software.ulpgc.katas.architecture.view;

import software.ulpgc.katas.architecture.model.RatedTitle;

public class SystemTitleDisplay implements TitleDisplay{
    @Override
    public void show(RatedTitle title) {
        System.out.println(title);
    }
}
