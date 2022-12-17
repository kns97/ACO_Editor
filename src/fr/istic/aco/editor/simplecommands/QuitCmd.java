package fr.istic.aco.editor.simplecommands;

/**
 * Created by plouzeau on 2014-06-01.
 */
public class QuitCmd implements Command {
    private final GreetingsInvoker receiver;

    /**
     * Constructor
     * @param receiver receiver
     */
    public QuitCmd(GreetingsInvoker receiver) {
        this.receiver = receiver;
    }

    /**
     * Execute the command to exit the program
     */
    @Override
    public void execute() {
        receiver.stopLoop();
    }
}
