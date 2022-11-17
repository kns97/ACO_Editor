package fr.istic.aco.editor.simplecommands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.EngineImpl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GreetingsInvokerImplTest {
	
	private GreetingsInvoker in;
	
	@BeforeEach
	void setUp() throws Exception {
		this.in = new GreetingsInvokerImpl();
	}
	
    @Test
    public void testInvoker()  {
        Logger.getGlobal().setLevel(Level.SEVERE);

        Command mockCmd = Mockito.mock(Command.class);
        
        // Setup a mock input stream
        String mockInput = String.format("Sample%nQuit%n");
        InputStream mockReadStream = new ByteArrayInputStream(mockInput.getBytes());
        in.setReadStream(mockReadStream);

        in.addCommand("Sample", mockCmd);

        // Install a command to stop the invoker's loop
        {
            Command quitCmd;

            quitCmd = () -> {
                in.stopLoop();
                Logger.getGlobal().info("Invoker stopped by test case");
            };
            in.addCommand("Quit", quitCmd);
        }

        in.runInvokerLoop();
        Mockito.verify(mockCmd).execute();
    	
    }
    
    @Test
    public void testSelectCmd() {
    	Logger.getGlobal().setLevel(Level.SEVERE);

        Command select = Mockito.mock(SelectCmd.class);
        in.addCommand("Select", select);
    	
    	String mockInput = String.format("Select%n");
        InputStream mockReadStream = new ByteArrayInputStream(mockInput.getBytes());
        in.setReadStream(mockReadStream);
    	
    	in.runInvokerLoop();
    	Mockito.verify(select).execute();
    }
    
    @Test
    public void testCutCmd() {
    	Logger.getGlobal().setLevel(Level.SEVERE);

    	Command cut = Mockito.mock(CutCmd.class);
    	in.addCommand("Cut", cut);
    	
    	String mockInput = String.format("Cut%n");
    	InputStream mockReadStream = new ByteArrayInputStream(mockInput.getBytes());
    	in.setReadStream(mockReadStream);
    	
    	in.runInvokerLoop();
    	Mockito.verify(cut).execute();
    }
    
    @Test
    public void testInsertCmd() {
    	Logger.getGlobal().setLevel(Level.SEVERE);

    	Command insert = Mockito.mock(InsertCmd.class);
    	in.addCommand("Insert", insert);
    	
    	String mockInput = String.format("Insert%n");
    	InputStream mockReadStream = new ByteArrayInputStream(mockInput.getBytes());
    	in.setReadStream(mockReadStream);
    	
    	in.runInvokerLoop();
    	Mockito.verify(insert).execute();
    }
    
    @Test
    public void testPasteCmd() {
    	Logger.getGlobal().setLevel(Level.SEVERE);

    	Command paste = Mockito.mock(PasteCmd.class);
    	in.addCommand("Paste", paste);
    	
    	String mockInput = String.format("Paste%n");
    	InputStream mockReadStream = new ByteArrayInputStream(mockInput.getBytes());
    	in.setReadStream(mockReadStream);
    	
    	in.runInvokerLoop();
    	Mockito.verify(paste).execute();
    }
    
    @Test
    public void testPrintCmd() {
    	Logger.getGlobal().setLevel(Level.SEVERE);
    	
    	Command print = Mockito.mock(PrintCmd.class);
    	in.addCommand("Print", print);
    	
    	String mockInput = String.format("Print%n");
    	InputStream mockReadStream = new ByteArrayInputStream(mockInput.getBytes());
    	in.setReadStream(mockReadStream);
    	
    	in.runInvokerLoop();
    	Mockito.verify(print).execute();
    }
    
    @Test
    public void testCopyCmd() {
    	Logger.getGlobal().setLevel(Level.SEVERE);
    	
    	Command copy = Mockito.mock(CopyCmd.class);
    	in.addCommand("Copy", copy);
    	
    	String mockInput = String.format("Copy%n");
    	InputStream mockReadStream = new ByteArrayInputStream(mockInput.getBytes());
    	in.setReadStream(mockReadStream);
    	
    	in.runInvokerLoop();
    	Mockito.verify(copy).execute();
    }
    
}
