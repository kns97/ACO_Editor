package fr.istic.aco.editor.tests;

import fr.istic.aco.editor.EngineImpl;
import fr.istic.aco.editor.utils.Buffer;
import fr.istic.aco.editor.utils.HistoryManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryTest {
    private EngineImpl engine;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

        engine = new EngineImpl();
    }
    @Test
    @DisplayName("Undo should return the buffer previous to the one active in the engine")
    void undo() {
        engine.insert("insert1");
        String first = engine.getBufferContents();
        engine.insert("insert2");
        engine.undo();
        assertEquals(first,engine.getBufferContents());

    }
    @Test
    @DisplayName("Undo should return the empty buffer if executed the same number of times as the insert")
    void infiniteUndo() {
        for(int i = 0;i<10;i++){
            engine.insert("insert");
        }
        for(int i = 0; i<10;i++){
            engine.undo();
        }

        assertEquals("",engine.getBufferContents());

    }
    @Test
    @DisplayName("Undo should return the empty buffer if executed more times then the insert")
    void underHistoryUndo() {
        for(int i = 0;i<10;i++){
            engine.insert("insert");
        }
        for(int i = 0; i<15;i++){
            engine.undo();
        }

        assertEquals("",engine.getBufferContents());

    }
    @Test
    @DisplayName("Redo should return the  buffer next to the one the engine is using ")
    void redo() {
        engine.insert("insert1");
        engine.undo();
        engine.redo();

        assertEquals("insert1",engine.getBufferContents());

    }
    @Test
    @DisplayName("Redo should return the last buffer in the history if executed more times than the inserts")
    void overHistoryRedo() {
        engine.insert("insert1");
        engine.undo();
        engine.redo();
        engine.redo();
        assertEquals("insert1",engine.getBufferContents());

    }
    @Test
    @DisplayName("Modifying the buffer after one ore more undo should delete the future history and create a new timeline where the insert is the last event")
    void newTimeline() {
        engine.insert("insert1");
        engine.insert("insert2");
        engine.insert("insert3"); //insert3insert2insert1
        engine.undo();//insert2insert1
        engine.undo();//insert1
        engine.insert("newTimeline");
        assertEquals(3,engine.getHistory().getHistory().size());
    }

}
