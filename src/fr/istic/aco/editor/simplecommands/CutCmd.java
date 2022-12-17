package fr.istic.aco.editor.simplecommands;

import fr.istic.aco.editor.Engine;

import java.util.logging.Logger;
/**
 * Cut command class
 */
public class CutCmd implements Command{
    private final GreetingsInvoker receiver;
    private Engine engine;
    /**
     * Constructor
     * @param receiver Receiver obj
     * @param e engine obj to execute the command on
     */
    public CutCmd(GreetingsInvoker receiver,Engine e) {
        this.receiver = receiver;
        this.engine = e;
    }
    @Override
    public void execute() {
        try{
            this.engine.cutSelectedText();
        }catch(Exception e){
            Logger.getGlobal().info(e.toString());
        }

    }
}
