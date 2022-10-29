package fr.istic.aco.editor.simplecommands;

import fr.istic.aco.editor.Engine;

import java.util.Scanner;

public class PrintCmd implements Command{
    private final GreetingsReceiver receiver;
    private Engine engine;

    public PrintCmd(GreetingsReceiver receiver,Engine e) {
        this.receiver = receiver;
        this.engine = e;
    }
    @Override
    public void execute() {
        receiver.displayMessage(engine.getBufferContents());
    }
}