package fr.istic.aco.editor.simplecommands;

import fr.istic.aco.editor.Engine;
import java.util.ArrayList;
import java.util.List;

public class ReplayCmd implements Command {
    private final GreetingsReceiver receiver;
    private Engine engine;

    public ReplayCmd(GreetingsReceiver receiver,Engine e) {
        this.receiver = receiver;
        this.engine = e;
    }
    @Override
    public void execute() {
        List<String> commands = this.engine.replay();
        for(String s : commands){
            receiver.displayMessage(s);
        }
    }
}