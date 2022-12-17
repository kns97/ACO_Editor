package fr.istic.aco.editor.simplecommands;

/**
 * Created by plouzeau on 2014-06-01.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple example of invoker for the Command design pattern.
 */
public class GreetingsInvokerImpl implements GreetingsInvoker{
    private Map<String, Command> commands = new HashMap<>();
    private boolean stopLoop = false;
    private InputStream inputStream;
    private BufferedReader bufferedReader;
    /**
     * Starts the reading of the read stream set by setReadStream operation
     */
    @Override
    public void runInvokerLoop() {
        while (!stopLoop) {
            String userInput = null;
            try {
                userInput = readUserInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(userInput == null) {
                stopLoop = true;
                break;
            }
            Command cmdToExecute = commands.get(userInput);
            if (cmdToExecute != null) {
                cmdToExecute.execute();
            }
        }
    }
    /**
     * Stops the read stream loop now.
     */
    @Override
    public void stopLoop() {
        stopLoop = true;
    }

    /**
     * Read the input of the user
     * @return
     * @throws IOException
     */
    private String readUserInput() throws IOException {
        return bufferedReader.readLine();
    }

    /**
     * Registers a new keyword/command pair
     *
     * @param keyword a non-null String
     * @param cmd     a non-null Command reference
     * @throws IllegalArgumentException for null parameters
     */
    @Override
    public void addCommand(String keyword, Command cmd) {
        if ((keyword == null) || (cmd == null)) {
            throw new IllegalArgumentException("null parameter");
        }
        commands.put(keyword,cmd);
    }
    /**
     * Sets the read stream that be be used by runInvokerLoop
     *
     * @param inputStream the read stream
     * @throws IllegalArgumentException if inputStream is null
     */
    @Override
    public void setReadStream(InputStream inputStream) {
        if(inputStream == null) {
            throw new IllegalArgumentException("null inputStream");
        }
        this.inputStream = inputStream;
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }
}
