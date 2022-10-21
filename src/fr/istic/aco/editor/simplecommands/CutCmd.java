package fr.istic.aco.editor.simplecommands;

import fr.istic.aco.editor.Engine;

public class CutCmd implements Command{
    private final GreetingsInvoker receiver;
    private Engine engine;

    public CutCmd(GreetingsInvoker receiver,Engine e) {
        this.receiver = receiver;
        this.engine = e;
    }
    @Override
    public void execute() {
        this.engine.cutSelectedText();
    }
}
