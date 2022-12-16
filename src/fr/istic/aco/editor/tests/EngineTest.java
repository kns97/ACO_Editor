package fr.istic.aco.editor.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.istic.aco.editor.Engine;
import fr.istic.aco.editor.EngineImpl;
import fr.istic.aco.editor.Selection;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class EngineTest {

    private Engine engine;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        
    	engine = new EngineImpl();
        
    }
    
    @Test
    @DisplayName("Selection has valid numbers upon initialization")
    void getDefaultSelection() {
    	Selection sel = engine.getSelection();
    	assertEquals(0, sel.getBeginIndex());
    	assertEquals(0, sel.getEndIndex());
    	assertEquals(0, sel.getBufferBeginIndex());
    	assertEquals(0, sel.getBufferEndIndex());
    	assertEquals("", sel.getBuffer().getText());
    }
    
    @Test
    @DisplayName("Selection allows for modification of the indexes")
    void setValidSelection() {
    	engine.insert("this is a test");
    	engine.setSelection(2, 3);
    	assertEquals(2, engine.getSelection().getBeginIndex());
    	assertEquals(3, engine.getSelection().getEndIndex());
    }
    
    @Test
    @DisplayName("An exception must be thrown when attempting to input negative start index")
    void setNegativeStartSelection() {
    	Selection sel = engine.getSelection();
    	IndexOutOfBoundsException ex = assertThrows(IndexOutOfBoundsException.class, ()->{sel.setBeginIndex(-1);});
		assertEquals("One or more arguments are invalid", ex.getMessage());
    }
    
    @Test
    @DisplayName("An exception must be thrown when attempting to input negative end index")
    void setNegativeEndSelection() {
    	Selection sel = engine.getSelection();
    	IndexOutOfBoundsException ex = assertThrows(IndexOutOfBoundsException.class, ()->{sel.setEndIndex(-1);});
		assertEquals("One or more arguments are invalid", ex.getMessage());
    }
    
    @Test
    @DisplayName("Exception must be thrown for invalid selection beginIndex")
    void setInvalidSelectionBegin() {
    	Selection sel = engine.getSelection();
    	IndexOutOfBoundsException ex = assertThrows(IndexOutOfBoundsException.class, ()->{sel.setBeginIndex(3);});
    	assertEquals("One or more arguments are invalid", ex.getMessage());
    }
    
    @Test
    @DisplayName("Exception must be thrown for invalid selection endIndex")
    void setInvalidSelectionEnd() {
    	Selection sel = engine.getSelection();
    	IndexOutOfBoundsException ex = assertThrows(IndexOutOfBoundsException.class, ()->{sel.setEndIndex(3);});
    	assertEquals("One or more arguments are invalid", ex.getMessage());
    }
    
    @Test
    @DisplayName("Buffer must be empty after initialization")
    void getEmptySelection() {
        assertEquals("",engine.getBufferContents());
    }
    
    @Test
    @DisplayName("Clipboard must be empty after initialization")
    void getClipboardContentsEmpty() {
    	assertEquals("", engine.getClipboardContents());
    }
    

    @Test
    @DisplayName("Buffer can be written from the engine")
    void writeInBuffer() {
    	engine.insert("This is a test");
        assertEquals("This is a test",engine.getBufferContents());
    }
    
    @Test
    void insertRegularSelection() {
    	engine.insert("This is a test");
    	engine.setSelection(5, 7);
    	engine.insert("has");
    	assertEquals("This has a test", engine.getBufferContents());
    }
    
    @Test
    void insertEdgeStart() {
    	engine.insert("This is a test");
    	engine.setSelection(0, 4);
    	engine.insert("That");
    	assertEquals("That is a test", engine.getBufferContents());
    }
    
    @Test
    void insertEdgeEnd() {
    	engine.insert("This is a test");
    	engine.setSelection(10, 14);
    	engine.insert("thing");
    	assertEquals("This is a thing", engine.getBufferContents());
    }
    
    @Test
    void deleteRegularSelection() {
    	engine.insert("This is a test");
    	engine.setSelection(8, 10);
    	engine.delete();
    	assertEquals("This is test", engine.getBufferContents());
    }
    
    @Test
    void deleteEdgeStartStart() {
    	engine.insert("This is a test");
    	engine.setSelection(0, 5);
    	engine.delete();
    	assertEquals("is a test", engine.getBufferContents());
    }
    
    @Test
    void deleteEdgeEndStart() {
    	engine.insert("This is a test");
    	engine.setSelection(14, 14);
    	engine.delete();
    	assertEquals("This is a test", engine.getBufferContents());
    }
    
    @Test
    void deleteEdgeEnd() {
    	engine.insert("This is a test");
    	engine.setSelection(10, 14);
    	engine.delete();
    	assertEquals("This is a ", engine.getBufferContents());
    }
    
    @Test
    void deleteEmpty() {
    	engine.delete();
    }
    
    
    
    @Test
    @DisplayName("Clipboard must get copied selection")
    void copySelectedText() {
    	engine.insert("This is a test");
    	engine.setSelection(0, 4);
    	engine.copySelectedText();
    	assertEquals("This", engine.getClipboardContents());
    }
    
    
    @Test
    @DisplayName("cutting should remove the selected section")
    void cutSelectedText() {
        engine.insert("This is a test");
        engine.setSelection(0, 4);
        engine.cutSelectedText();
        assertEquals("This", engine.getClipboardContents());
        assertEquals(" is a test", engine.getBufferContents());
    }
    
    @Test
    void pasteRegularSelection() {
    	engine.insert("This is a test");
        engine.setSelection(0, 4);
        engine.cutSelectedText();
        engine.setSelection(5, 7);
        engine.pasteClipboard();
        assertEquals("This", engine.getClipboardContents());
        assertEquals(" is aThisest", engine.getBufferContents());
    }
    
    @Test
    @DisplayName("paste should add the clipboard into the selection")
    void pasteedgeStart() {
    	engine.insert("This is a test");
        engine.setSelection(0, 4);
        engine.cutSelectedText();
        int index = 10;
        engine.setSelection(index, index);
        engine.pasteClipboard();
        assertEquals("This", engine.getClipboardContents());
        assertEquals(" is a testThis", engine.getBufferContents());
    }
    
    @Test
    @DisplayName("paste test2")
    void pasteEdgeEnd() {
    	engine.insert("This is a test");
        engine.setSelection(0, 4);
        engine.cutSelectedText();
        engine.setSelection(9, 10);
        engine.pasteClipboard();
        assertEquals("This", engine.getClipboardContents());
        assertEquals(" is a tesThis", engine.getBufferContents());
    }
    
    @Test
    @DisplayName("paste should add the clipboard into the selection")
    void deleteSection() {
    	engine.insert("This is a test");
    	engine.setSelection(0, 4);
        engine.delete();
        assertEquals(" is a test", engine.getBufferContents());
    }
    
    @Test
    void recordInsert() {
    	engine.startRecording();
    	engine.insert("This is a test");
    	engine.stopRecording();
    	List<String> test = engine.replay();
    	for(String value: test) {
    		assertEquals("Insert: This is a test", value);
    	}
    }
    
    @Test
    void recordSelect() {
    	engine.insert("This is a test");
    	engine.startRecording();
    	engine.setSelection(0,4);
    	engine.stopRecording();
    	List<String> test = engine.replay();
    	for(String value: test) {
    		assertEquals("setSelection: 0 4", value);
    	}
    }
    
    @Test
    void recordCut() {
    	engine.insert("This is a test");
    	engine.setSelection(0, 4);
    	engine.startRecording();
    	engine.cutSelectedText();
    	engine.stopRecording();
    	List<String> test = engine.replay();
    	for(String value: test) {
    		assertEquals("Cut: This", value);
    	}
    }
    
    @Test
    void recordCopy() {
    	engine.insert("This is a test");
    	engine.setSelection(0, 4);
    	engine.startRecording();
    	engine.copySelectedText();
    	engine.stopRecording();
    	List<String> test = engine.replay();
    	for(String value: test) {
    		assertEquals("copySelectedText: This", value);
    	}
    }
    
    @Test
    void recordDelete() {
    	engine.insert("This is a test");
    	engine.setSelection(0, 4);
    	engine.startRecording();
    	engine.delete();
    	engine.stopRecording();
    	List<String> test = engine.replay();
    	for(String value: test) {
    		assertEquals("delete:  is a test", value);
    	}
    }
    
    @Test
    void recordPaste() {
    	engine.insert("This is a test");
        engine.setSelection(0, 4);
        engine.cutSelectedText();
        engine.startRecording();
        engine.pasteClipboard();
        engine.stopRecording();
        List<String> test = engine.replay();
    	for(String value: test) {
    		assertEquals("PasteClipboard: This", value);
    	}
    }
    
    @Test
    void recordChain() {
    	engine.startRecording();
    	engine.insert("This is a test");
    	engine.setSelection(0, 4);
    	engine.copySelectedText();
    	engine.cutSelectedText();
    	engine.pasteClipboard();
    	engine.delete();
    	engine.getBufferContents();
    	engine.stopRecording();
    	List<String> test = engine.replay();
    	String[] data = new String[7];
    	data[0] = "Insert: This is a test";
    	data[1] = "setSelection: 0 4";
    	data[2] = "copySelectedText: This";
    	data[3] = "Cut: This";
    	data[4] = "PasteClipboard: This";
    	data[5] = "delete: a test";
    	data[6] = "PrintBufferContents: a test";
    	int i = 0;
    	for(String value : test) {
    		assertEquals(data[i], value);
    		i++;
    	}
    }
    
    @Test
    void IndexOutOfBoundsNatural() {
    	engine.insert("This is a test");
    	engine.setSelection(10, 14);
    	engine.cutSelectedText();
    	engine.copySelectedText();
    	engine.cutSelectedText();
    	assertEquals("This is a ", engine.getBufferContents());
    }
    
    
}
