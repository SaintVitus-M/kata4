package software.ulpgc.kata4.app;

import software.ulpgc.kata4.architecture.control.Command;
import software.ulpgc.kata4.architecture.control.DisplayCommand;

public class Main {
    public static void main(String[] args){
        Command command = new DisplayCommand();
        command.execute();
    }
}