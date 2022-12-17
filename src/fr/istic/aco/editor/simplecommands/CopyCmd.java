package fr.istic.aco.editor.simplecommands;

import fr.istic.aco.editor.Engine;

import java.util.logging.Logger;

/**
 * Copy command class
 */
public class CopyCmd implements Command{
    private final GreetingsInvoker receiver;
    private Engine engine;

    /**
     * Constructor
     * @param receiver Receiver obj
     * @param e engine obj to execute the command on
     */
    public CopyCmd(GreetingsInvoker receiver,Engine e) {
        this.receiver = receiver;
        this.engine = e;
    }

    /**
     * Execute the command
     */
    @Override
    public void execute() {
        try{
            this.engine.copySelectedText();
        }catch(Exception e){
            Logger.getGlobal().info(e.toString());
        }

    }
}
