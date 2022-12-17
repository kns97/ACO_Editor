package fr.istic.aco.editor.simplecommands;

/**
 * Created by plouzeau on 2014-06-01.
 */
public class HelloCmd implements Command {

    private final GreetingsReceiver receiver;
    /**
     * Constructor
     * @param receiver Receiver obj
     */
    public HelloCmd(GreetingsReceiver receiver) {
        this.receiver = receiver;
    }

    /**
     * Displays hello
     */
    @Override
    public void execute() {
        receiver.displayMessage("Hello");
    }
}
