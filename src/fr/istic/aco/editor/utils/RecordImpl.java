package fr.istic.aco.editor.utils;

import fr.istic.aco.editor.Record;

import java.util.ArrayList;

/**
 * Class to handle the recording
 */
public class RecordImpl implements Record {
    private boolean recordFlag;
    private ArrayList<String> commands;

    /**
     * Constructor
     */
    public RecordImpl() {
        this.recordFlag = false;
        this.commands = new ArrayList<>();
    }

    /**
     * Getter for the commands saved
     * @return arraylist of command saved
     */
    public ArrayList<String> getCommands() {
        return this.commands;
    }

    /**
     * Add a new command to history
     * @param command command to be added
     */
    public void setCommands(String command){
       this.commands.add(command);
    }

    /**
     * Getter for the record flag
     * @return recording state
     */
    public boolean getRecordFlag() {
        return recordFlag;
    }

    /**
     * Start the recording of the sequence of commands
     */
    public void startRecord(){
        if(recordFlag == false){
            /*
            * Clear the previous recorded comments before starting a new recording.
            * If it is already recording, do nothing :)
            */
            commands.clear();
        }
        recordFlag = true;
    }

    /**
     * Stop the recording of the sequence of commands
     */
    public void stopRecord(){
        recordFlag = false;
    }

}

