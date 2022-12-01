package fr.istic.aco.editor.simplecommands;

import fr.istic.aco.editor.Engine;

public class RedoCmd implements Command{
    private final GreetingsInvoker receiver;
    private Engine engine;

    public RedoCmd(GreetingsInvoker receiver, Engine e) {
        this.receiver = receiver;
        this.engine = e;
    }
    @Override
    public void execute() {
        this.engine.redo();
    }
}