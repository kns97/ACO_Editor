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

    private void todo() {
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
    	sel.setBeginIndex(0);
    	sel.setEndIndex(1);
    	assertEquals(0, sel.getBeginIndex());
    	assertEquals(1, sel.getEndIndex());
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
    @DisplayName("It is possible to establish an incompatible selection")
    void setInvalidSelection() {
    	Selection sel = engine.getSelection();
    	sel.setBeginIndex(3);
    	sel.setEndIndex(1);
    	assertEquals(3, sel.getBeginIndex());
    	assertEquals(1, sel.getEndIndex());
    }
    
    @Test
    @DisplayName("Buffer must be empty after initialization")
    void getEmptySelection() {
        Selection selection = engine.getSelection();
        assertEquals(selection.getBufferBeginIndex(),selection.getBeginIndex());
        assertEquals("",engine.getBufferContents());
    }
    
    @Test
    @DisplayName("Clipboard must be empty after initialization")
    void getEmptyClipboardContents() {
    	assertEquals("", engine.getClipboardContents());
    }
    
    @Test
    
    void getBufferContents() {
        todo();
    }

    @Test
    void cutSelectedText() {
        todo();
    }

    @Test
    void copySelectedText() {
        todo();
    }

    @Test
    void pasteClipboard() {
        todo();
    }
}
