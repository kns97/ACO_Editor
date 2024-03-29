package fr.istic.aco.editor.simplecommands;

/**
 * Created by plouzeau on 2014-06-01.
 */

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.EngineImpl;

import java.util.logging.Logger;

/**
 * A simple demo application for the Greetings Command design pattern example
 */
public class GreetingsConfigurator {

    private GreetingsInvoker invoker;
    private GreetingsReceiver receiver;

    /**
     * Create the client instance
     * @param lineArgs env
     */
    public static void main(String lineArgs[]) {

        GreetingsConfigurator client = new GreetingsConfigurator();
        client.run();

    }

    /**
     * Run the main cli loop
     */
    private void run() {

        Logger.getGlobal().info("Starting...");
        invoker = new GreetingsInvokerImpl();
        receiver = new GreetingsReceiver();
        invoker.setReadStream(System.in);
        configureCommands();
        invoker.runInvokerLoop();
    }

    /**
     * Configure the commands to be used by the user
     */
    private void configureCommands() {
        Engine e = new EngineImpl();
        invoker.addCommand("Quit", new QuitCmd(invoker));
        invoker.addCommand("Hello", new HelloCmd(receiver));
        invoker.addCommand("Copy", new CopyCmd(invoker,e));
        invoker.addCommand("Cut", new CutCmd(invoker,e));
        invoker.addCommand("Paste", new PasteCmd(invoker,e));
        invoker.addCommand("Insert", new InsertCmd(invoker,e));
        invoker.addCommand("Select", new SelectCmd(invoker,e));
        invoker.addCommand("Print", new PrintCmd(receiver,e));
        invoker.addCommand("Delete", new DeleteCmd(invoker, e));
        invoker.addCommand("StartRecord", new StartRecordingCmd(invoker,e));
        invoker.addCommand("StopRecord", new StopRecordingCmd(invoker,e));
        invoker.addCommand("Replay", new ReplayCmd(receiver,e));
        invoker.addCommand("Undo", new UndoCmd(invoker,e));
        invoker.addCommand("Redo", new RedoCmd(invoker,e));

        // An example of Java 8 lambdas
        invoker.addCommand("Test", () -> System.err.println("Test : "+this.toString()));

    }
}
