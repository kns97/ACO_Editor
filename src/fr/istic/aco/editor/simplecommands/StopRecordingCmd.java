package fr.istic.aco.editor.simplecommands;

import fr.istic.aco.editor.Engine;

import java.util.logging.Logger;

/**
 * StopRecording command class
 */
public class StopRecordingCmd implements Command {
        private final GreetingsInvoker receiver;
        private Engine engine;
    /**
     * Constructor
     * @param receiver receiver
     * @param e engine
     */
        public StopRecordingCmd(GreetingsInvoker receiver, Engine e) {
            this.receiver = receiver;
            this.engine = e;
        }

    /**
     * Execute the command
     */
    @Override
        public void execute() {
            try{
                this.engine.stopRecording();
            }catch(Exception e){
                Logger.getGlobal().info(e.toString());
            }
        }
    }

