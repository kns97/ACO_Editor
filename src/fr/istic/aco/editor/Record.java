package fr.istic.aco.editor;

import java.util.ArrayList;

public interface Record {
    void startRecord();

    void stopRecord();

    boolean getRecordFlag();

    void setCommands(String command);
    ArrayList<String> getCommands();
}
