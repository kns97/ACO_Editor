package fr.istic.aco.editor.utils;

import fr.istic.aco.editor.Record;

import java.util.ArrayList;

public class RecordImpl implements Record {
    private boolean recordFlag;
    private ArrayList<String> commands;
    public RecordImpl() {
        this.recordFlag = false;
        this.commands = new ArrayList<>();
    }
    public ArrayList<String> getCommands() {
        return this.commands;
    }

    public void setCommands(String command){
       this.commands.add(command);
    }

    public boolean getRecordFlag() {
        return recordFlag;
    }

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

    public void stopRecord(){
        recordFlag = false;
    }

}

