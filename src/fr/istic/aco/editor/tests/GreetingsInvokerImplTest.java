package fr.istic.aco.editor.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.EngineImpl;
import fr.istic.aco.editor.simplecommands.Command;
import fr.istic.aco.editor.simplecommands.CopyCmd;
import fr.istic.aco.editor.simplecommands.CutCmd;
import fr.istic.aco.editor.simplecommands.DeleteCmd;
import fr.istic.aco.editor.simplecommands.GreetingsInvoker;
import fr.istic.aco.editor.simplecommands.GreetingsInvokerImpl;
import fr.istic.aco.editor.simplecommands.InsertCmd;
import fr.istic.aco.editor.simplecommands.PasteCmd;
import fr.istic.aco.editor.simplecommands.PrintCmd;
import fr.istic.aco.editor.simplecommands.SelectCmd;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Tests on the Invoker
 */
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
    public void InvokerParameters() {
    	Command mockCmd = Mockito.mock(Command.class);
    	IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->{in.addCommand(null, mockCmd);});
    	assertEquals("null parameter", ex.getMessage());
    }
    
    @Test
    public void InvokerParameters2() {
    	IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->{in.addCommand("KeyWord", null);});
    	assertEquals("null parameter", ex.getMessage());
    }
    
    @Test
    public void InvokerInputStream() {
    	IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->{in.setReadStream(null);});
    	assertEquals("null inputStream", ex.getMessage());
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
    
    @Test
    public void testDeleteCmd() {
    	Logger.getGlobal().setLevel(Level.SEVERE);
    	
    	Command delete = Mockito.mock(DeleteCmd.class);
    	in.addCommand("Delete", delete);
    	
    	String mockInput = String.format("Delete%n");
    	InputStream mockReadStream = new ByteArrayInputStream(mockInput.getBytes());
    	in.setReadStream(mockReadStream);
    	
    	in.runInvokerLoop();
    	Mockito.verify(delete).execute();
    }
    
}
