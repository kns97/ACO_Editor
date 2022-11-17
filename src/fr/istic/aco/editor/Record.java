package fr.istic.aco.editor;

import java.util.List;

public interface Record {
    void startRecord();

    void stopRecord();

    boolean getRecordFlag();

    void setCommands(String commands);
    List<String> getCommands();
}
