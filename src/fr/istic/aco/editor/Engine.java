package fr.istic.aco.editor;

import fr.istic.aco.editor.utils.Buffer;
import fr.istic.aco.editor.utils.HistoryManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Main API for the text editing engine
 *
 * @author plouzeau
 * @version 1.0
 */

public interface Engine {

    /**
     * Provides access to the selection control object
     * @return the selection object
     */
    Selection getSelection();

    /**
     * Provides the whole contents of the buffer, as a string
     * @return a copy of the buffer's contents
     */
    String getBufferContents();

    /**
     * Provides the clipboard contents
     * @return a copy of the clipboard's contents
     */
    String getClipboardContents();

    /**
     * Removes the text within the interval
     * specified by the selection control object,
     * from the buffer.
     */
    void cutSelectedText();

    /**
     * Copies the text within the interval
     * specified by the selection control object
     * into the clipboard.
     */
    void copySelectedText();

    /**
     * Replaces the text within the interval specified by the selection object with
     * the contents of the clipboard.
     */
    void pasteClipboard();

    /**
     * Inserts a string in the buffer, which replaces the contents of the selection
     * @param s the text to insert
     */
    void insert(String s);

    /**
     * Removes the contents of the selection in the buffer
     */
    void delete();

    /**
     * Set the selection to start and end at certain indexes
     * @param start beginning index of the selection
     * @param stop ending index of the selection
     */
    void setSelection(int start,int stop);

    /**
     * Start recording the sequence of commands
     */
    void startRecording();

    /**
     * Stop recording the sequence of commands
     */
    void stopRecording();

    /**
     * Check if the engine is recording the sequence of commands
     * @return wheter the engine is recording or not
     */
    boolean isRecording();

    /**
     * Recover the list of recorded commands
     * @return the list of recorded commands
     */
    ArrayList<String> replay();

    /**
     * Return to previous state in the buffer
     */
    void undo();

    /**
     * Forward to the next state in the buffer
     */
    void redo();

    /**
     * Set a buffer object as the buffer used by the engine
     * @param b buffer to be set
     */
    void setBuffer(Buffer b);

    /**
     * Retrieve the history of states of the buffer
     * @return the list of states of the buffer
     */
    HistoryManager getHistory();
}
