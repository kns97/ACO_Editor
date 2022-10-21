package fr.istic.aco.editor.simplecommands;

import fr.istic.aco.editor.Engine;

public class PasteCmd implements Command{
    private final GreetingsInvoker receiver;
    private Engine engine;

    public PasteCmd(GreetingsInvoker receiver,Engine e) {
        this.receiver = receiver;
        this.engine = e;
    }
    @Override
    public void execute() {
        this.engine.pasteClipboard();
    }
}
