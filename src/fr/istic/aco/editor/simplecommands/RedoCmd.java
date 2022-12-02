package fr.istic.aco.editor.simplecommands;

import fr.istic.aco.editor.Engine;

import java.util.logging.Logger;

public class RedoCmd implements Command{
    private final GreetingsInvoker receiver;
    private Engine engine;

    public RedoCmd(GreetingsInvoker receiver, Engine e) {
        this.receiver = receiver;
        this.engine = e;
    }
    @Override
    public void execute() {

        try{
            this.engine.redo();
        }catch(Exception e){
            Logger.getGlobal().info(e.toString());
        }
    }
}