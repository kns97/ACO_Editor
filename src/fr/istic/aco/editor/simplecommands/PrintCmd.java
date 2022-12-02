package fr.istic.aco.editor.simplecommands;

import fr.istic.aco.editor.Engine;

import java.util.Scanner;
import java.util.logging.Logger;

public class PrintCmd implements Command{
    private final GreetingsReceiver receiver;
    private Engine engine;

    public PrintCmd(GreetingsReceiver receiver,Engine e) {
        this.receiver = receiver;
        this.engine = e;
    }
    @Override
    public void execute() {
        try{
            receiver.displayMessage(engine.getBufferContents());
        }catch(Exception e){
            Logger.getGlobal().info(e.toString());
        }

    }
}