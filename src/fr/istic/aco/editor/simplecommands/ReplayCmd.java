package fr.istic.aco.editor.simplecommands;

import fr.istic.aco.editor.Engine;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
/**
 * Replay command class
 */
public class ReplayCmd implements Command {
    private final GreetingsReceiver receiver;
    private Engine engine;
    /**
     * Constructor
     * @param receiver receiver
     * @param e engine
     */
    public ReplayCmd(GreetingsReceiver receiver,Engine e) {
        this.receiver = receiver;
        this.engine = e;
    }

    /**
     * Execute the command
     */
    @Override
    public void execute() {
        try {
            List<String> commands = this.engine.replay();
            for (String s : commands) {
                receiver.displayMessage(s);
            }
        }catch(Exception e){
            Logger.getGlobal().info(e.toString());
        }
    }
}