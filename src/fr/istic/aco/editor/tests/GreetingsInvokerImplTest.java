package fr.istic.aco.editor.tests;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import fr.istic.aco.editor.simplecommands.Command;
import fr.istic.aco.editor.simplecommands.GreetingsInvoker;
import fr.istic.aco.editor.simplecommands.GreetingsInvokerImpl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GreetingsInvokerImplTest {


    @Test
    public void testInvoker()  {
        Logger.getGlobal().setLevel(Level.SEVERE);
        GreetingsInvoker invoker = new GreetingsInvokerImpl();

        Command mockCmd = Mockito.mock(Command.class);

        // Setup a mock input stream
        String mockInput = String.format("Sample%nQuit%n");
        InputStream mockReadStream = new ByteArrayInputStream(mockInput.getBytes());
        invoker.setReadStream(mockReadStream);

        invoker.addCommand("Sample", mockCmd);

        // Install a command to stop the invoker's loop
        {
            Command quitCmd;

            quitCmd = () -> {
                invoker.stopLoop();
                Logger.getGlobal().info("Invoker stopped by test case");
            };
            invoker.addCommand("Quit", quitCmd);
        }

        invoker.runInvokerLoop();
        Mockito.verify(mockCmd).execute();
    }
}
