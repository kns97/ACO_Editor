package fr.istic.aco.editor.simplecommands;

import fr.istic.aco.editor.Engine;

import java.util.logging.Logger;
/**
 * Undo command class
 */
public class UndoCmd implements Command{
    private final GreetingsInvoker receiver;
    private Engine engine;
    /**
     * Constructor
     * @param receiver receiver
     * @param e engine
     */
    public UndoCmd(GreetingsInvoker receiver, Engine e) {
        this.receiver = receiver;
        this.engine = e;
    }

    /**
     * Execute the command
     */
    @Override
    public void execute() {
        try{
            this.engine.undo();
        }catch(Exception e){
            Logger.getGlobal().info(e.toString());
        }
    }
}