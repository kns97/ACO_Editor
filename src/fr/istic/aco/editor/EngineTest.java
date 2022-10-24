package fr.istic.aco.editor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    }
    
    @Test
    @DisplayName("Selection allows for modification of the indexes")
    void setValidSelection() {
    	Selection sel = engine.getSelection();
    	engine.insert("this is a test");
    	sel.setBeginIndex(2);
    	sel.setEndIndex(3);
    	assertEquals(2, sel.getBeginIndex());
    	assertEquals(3, sel.getEndIndex());
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
    @DisplayName("Clipboard must get copied selection")
    void copySelectedText() {
    	engine.insert("This is a test");
    	Selection sel = engine.getSelection();
    	sel.setBeginIndex(0);
    	sel.setEndIndex(4);
    	engine.copySelectedText();
    	assertEquals("This", engine.getClipboardContents());
    }
    
    
    @Test
    @DisplayName("cutting should remove the selected section")
    void cutSelectedText() {
        engine.insert("This is a test");
        Selection sel = engine.getSelection();
        sel.setBeginIndex(0);
        sel.setEndIndex(4);
        engine.cutSelectedText();
        assertEquals("This", engine.getClipboardContents());
        assertEquals(" is a test", engine.getBufferContents());
    }
    

    @Test
    @DisplayName("paste should add the clipboard into the selection")
    void pasteClipboard() {
    	engine.insert("This is a test");
        Selection sel = engine.getSelection();
        sel.setBeginIndex(0);
        sel.setEndIndex(4);
        engine.cutSelectedText();
        int index = engine.getBufferContents().length();
        sel.setBeginIndex(index);
        sel.setEndIndex(index);
        engine.pasteClipboard();
        assertEquals("This", engine.getClipboardContents());
        assertEquals(" is a testThis", engine.getBufferContents());
    }
    
    @Test
    @DisplayName("paste should add the clipboard into the selection")
    void deleteSection() {
    	engine.insert("This is a test");
        Selection sel = engine.getSelection();
        sel.setBeginIndex(0);
        sel.setEndIndex(4);
        engine.delete();
        assertEquals(" is a test", engine.getBufferContents());
    }
    
    
    
    
}
