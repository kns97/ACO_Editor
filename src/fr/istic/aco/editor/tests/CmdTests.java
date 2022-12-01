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
import fr.istic.aco.editor.simplecommands.GreetingsReceiver;
import fr.istic.aco.editor.simplecommands.InsertCmd;
import fr.istic.aco.editor.simplecommands.PasteCmd;
import fr.istic.aco.editor.simplecommands.PrintCmd;
import fr.istic.aco.editor.simplecommands.SelectCmd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CmdTests {
	private Engine e;
	private GreetingsInvoker invoker;
	private GreetingsReceiver receiver;
	private ByteArrayOutputStream out = new ByteArrayOutputStream();
	private final PrintStream original = System.out;
	private ByteArrayInputStream in;
	
	@BeforeEach
	void prep() {
		System.setOut(new PrintStream(out));
		e = new EngineImpl();
		invoker = new GreetingsInvokerImpl();
		receiver = new GreetingsReceiver();
	}
	
	@Test
	void insertCmdTest() {
		in = new ByteArrayInputStream("This is a test".getBytes());
		System.setIn(in);
		Command cmd = new InsertCmd(this.invoker, this.e);
		cmd.execute();

		assertEquals("Enter the text to insert: Buffer text updated to: This is a test\n", out.toString());
		assertEquals("This is a test", e.getBufferContents());
	}
	
	@Test
	void selectCmdTest() {

		e.insert("This is a test");
		in = new ByteArrayInputStream(String.format("0%n4%n").getBytes());
		System.setIn(in);
		Command cmd = new SelectCmd(this.invoker, this.e);
		cmd.execute();
		assertEquals("Enter start of the selection: Enter end of the selection: ", out.toString());
		assertEquals(0, e.getSelection().getBeginIndex());
		assertEquals(4, e.getSelection().getEndIndex());
	}
	
	@Test
	void cutCmdTest() {
		e.insert("This is a test");
		e.setSelection(0, 4);
		Command cmd = new CutCmd(this.invoker, this.e);
		cmd.execute();
		assertEquals(" is a test", e.getBufferContents());
		assertEquals("This", e.getClipboardContents());
	}
	
	@Test
	void copyCmdTest() {
		e.insert("This is a test");
		e.setSelection(0, 4);
		Command cmd = new CopyCmd(this.invoker, this.e);
		cmd.execute();
		assertEquals("This", e.getClipboardContents());
	}
	
	@Test
	void PasteCmdTest() {
		e.insert("This is a test");
		e.setSelection(0, 4);
		e.copySelectedText();
		e.setSelection(14, 14);
		Command cmd = new PasteCmd(this.invoker, this.e);
		cmd.execute();
		assertEquals("This is a testThis", e.getBufferContents());
	}
	
	@Test
	void DeleteCmdTest() {
		e.insert("This is a test");
		e.setSelection(0, 4);
		Command cmd = new DeleteCmd(this.invoker, this.e);
		cmd.execute();
		assertEquals(" is a test", e.getBufferContents());
	}
	
	@Test
	void PrintCmdTest() {
		e.insert("This is a test");
		Command cmd = new PrintCmd(this.receiver, this.e);
		
	}
	
}
