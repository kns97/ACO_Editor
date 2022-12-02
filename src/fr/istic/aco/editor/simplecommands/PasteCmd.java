package fr.istic.aco.editor.simplecommands;

import fr.istic.aco.editor.Engine;

import java.util.logging.Logger;

public class PasteCmd implements Command{
    private final GreetingsInvoker receiver;
    private Engine engine;

    public PasteCmd(GreetingsInvoker receiver,Engine e) {
        this.receiver = receiver;
        this.engine = e;
    }
    @Override
    public void execute() {
        try{
            this.engine.pasteClipboard();
        }catch(Exception e){
            Logger.getGlobal().info(e.toString());
        }
    }
}
