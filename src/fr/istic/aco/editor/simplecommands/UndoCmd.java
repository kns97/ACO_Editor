package fr.istic.aco.editor.simplecommands;

import fr.istic.aco.editor.Engine;

import java.util.logging.Logger;

public class UndoCmd implements Command{
    private final GreetingsInvoker receiver;
    private Engine engine;

    public UndoCmd(GreetingsInvoker receiver, Engine e) {
        this.receiver = receiver;
        this.engine = e;
    }
    @Override
    public void execute() {
        try{
            this.engine.undo();
        }catch(Exception e){
            Logger.getGlobal().info(e.toString());
        }
    }
}