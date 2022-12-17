package fr.istic.aco.editor;

import java.util.ArrayList;

/**
 * Replay interface
 */
public interface Record {
    /**
     * Start recording the sequence of commands
     */
    void startRecord();

    /**
     * Stop recording the sequence of commands
     */
    void stopRecord();

    /**
     * Get the recording state
     * @return the recording state of the recording object
     */
    boolean getRecordFlag();

    /**
     * Set the command
     * @param command command to be added
     */
    void setCommands(String command);

    /**
     * Get list of commands
     * @return the list of recorded commands
     */
    ArrayList<String> getCommands();
}
