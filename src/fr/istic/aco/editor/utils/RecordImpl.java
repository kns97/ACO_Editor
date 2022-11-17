package fr.istic.aco.editor.utils;

import fr.istic.aco.editor.Record;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecordImpl implements Record {
    private boolean recordFlag;
    //private Buffer buffer;
    private List<String> commands;

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(String commands) {
        this.commands.add(commands);
    }

    public RecordImpl() {
        this.recordFlag = false;
        this.commands = new ArrayList<>();
    }

    public boolean getRecordFlag() {
        return recordFlag;
    }

    public void startRecord(){
        recordFlag = true;
        //TODO: clear the current content of record buffer - reinitialise
    }

    public void stopRecord(){
        recordFlag = false;
        //KNS Note: Engine in charge of replay, not record
    }

}

