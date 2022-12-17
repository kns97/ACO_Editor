package fr.istic.aco.editor.simplecommands;

import fr.istic.aco.editor.Engine;

import java.util.logging.Logger;
/**
 * StartRecording command class
 */
public class StartRecordingCmd implements Command {
    private final GreetingsInvoker receiver;
    private Engine engine;
    /**
     * Constructor
     * @param receiver receiver
     * @param e engine
     */
    public StartRecordingCmd(GreetingsInvoker receiver, Engine e) {
        this.receiver = receiver;
        this.engine = e;
    }

    /**
     * Execute the command
     */
    @Override
    public void execute() {
        try{
            this.engine.startRecording();
        }catch(Exception e){
            Logger.getGlobal().info(e.toString());
        }

    }
}
