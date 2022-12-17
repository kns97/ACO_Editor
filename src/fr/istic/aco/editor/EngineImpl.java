package fr.istic.aco.editor;

import fr.istic.aco.editor.utils.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the engine
 */
public class EngineImpl implements Engine {

    private Buffer buffer;
    private Selection selection;
    private Clipboard clipboard;
    private Record record;
    private HistoryManager history;

    /**
     * Constructor
     */
    public EngineImpl(){
     
    	buffer = new Buffer();
        selection = new SelectionImpl(buffer);
        clipboard = new Clipboard();
        record = new RecordImpl();
        history = new HistoryManager();

        history.addBuffer(new Buffer(""));

    }
    /**
     * Provides access to the selection control object
     *
     * @return the selection object
     */
    @Override
    public Selection getSelection() {
     
    	return selection;
    
    }

    /**
     * Provides the whole contents of the buffer, as a string
     *
     * @return a copy of the buffer's contents
     */
    @Override
    public String getBufferContents() {

        String text = buffer.getText();

        if(isRecording()){
            record.setCommands("PrintBufferContents: "+text);
        }
        return text;
    
    }

    /**
     * Provides the clipboard contents
     *
     * @return a copy of the clipboard's contents
     */
    @Override
    public String getClipboardContents() {

        return clipboard.getText();
    
    }

    /**
     * Removes the text within the interval
     * specified by the selection control object,
     * from the buffer.
     */
    @Override
    public void cutSelectedText() throws IndexOutOfBoundsException{

    	int start = selection.getBeginIndex();
        int stop = selection.getEndIndex();
        String text = buffer.getText();
        
        if(start > stop || start < 0 || stop < 0) throw new IllegalArgumentException("One or more arguments are invalid");
		
		if(start >= text.length()) {
			clipboard.setText("");
			}
		else if(stop >= text.length()) {
			clipboard.setText(text.substring(start));;
			buffer.setText(text.substring(0, start));
		}
		else {
        String btext = text.substring(0,start)+text.substring(stop);
        String ctext = text.substring(start,stop);
        buffer.setText(btext);
        clipboard.setText(ctext);
		}
        if(isRecording()){
            record.setCommands("Cut: "+clipboard.getText());
        }
        HistoryHandler();



    }

    /**
     * Copies the text within the interval
     * specified by the selection control object
     * into the clipboard.
     */
    @Override
    public void copySelectedText() throws IndexOutOfBoundsException{
        
    	int start = selection.getBeginIndex();
        int stop = selection.getEndIndex();
        String text = buffer.getText();
        
        if(start > stop || start < 0 || stop < 0) throw new IllegalArgumentException("One or more arguments are invalid");
		
		if(start >= text.length()) {
			clipboard.setText("");
		}
		
		else if(stop >= text.length()) {
			clipboard.setText(text.substring(start));
		}
		else {
			clipboard.setText(text.substring(start, stop));	
		}
		
        if(isRecording()){
            record.setCommands("copySelectedText: "+ clipboard.getText());
        }
    
    }

    /**
     * Replaces the text within the interval specified by the selection object with
     * the contents of the clipboard.
     */
    @Override
    public void pasteClipboard() throws IndexOutOfBoundsException{
    	int start = selection.getBeginIndex();
        int stop = selection.getEndIndex();
        String text = buffer.getText();
        
        if( stop < start || stop < 0 || start < 0) throw new IndexOutOfBoundsException("One or more arguments are invalid");
		
		if( text.length() <= start || text.length() == 0) {
			
			buffer.setText(text + clipboard.getText());
			
		}else if (text.length() <= stop){
		
			String splitString = text.substring(0, start);
			buffer.setText(splitString + clipboard.getText());
		
		}else{
			
		    String btext = text.substring(0,start) + clipboard.getText() +text.substring(stop);
		    buffer.setText(btext);
        
		}

        if(isRecording()){
            record.setCommands("PasteClipboard: "+ clipboard.getText());
        }
        HistoryHandler();
    }

    /**
     * Inserts a string in the buffer, which replaces the contents of the selection
     *
     * @param s the text to insert
     */
    @Override
    public void insert(String s) throws IndexOutOfBoundsException{
    	int start = selection.getBeginIndex();
        int stop = selection.getEndIndex();
        String text = buffer.getText();
        
        if( stop < start || stop < 0 || start < 0) throw new IndexOutOfBoundsException("Values are not valid");
		
		if( text.length() <= start || text.length() == 0) {
			
			buffer.setText(text + s);

		}else if (text.length() <= stop){
		
			String splitString = text.substring(0, start);
			buffer.setText(splitString + s);

		}else{
			
        String btext = text.substring(0,start) + s +text.substring(stop);
        buffer.setText(btext);

		}

        HistoryHandler();

        if(isRecording()){
            record.setCommands("Insert: "+s);
        }


    }

    /**
     * Removes the contents of the selection in the buffer
     */
    @Override
    public void delete() throws IndexOutOfBoundsException{
        
    	int start = selection.getBeginIndex();
        int stop = selection.getEndIndex();        
        String text = buffer.getText();
        String splitString="";
        
        if( stop < start || stop < 0 || start < 0) throw new IndexOutOfBoundsException("Values are not valid");
		
		if( text.length() <= start || text.length() == 0) {
			return;
		}else if (text.length() <= stop){
            splitString = text.substring(0, start);
            buffer.setText(splitString);
		}else{
            splitString = text.substring(0,start)+text.substring(stop);
            buffer.setText(splitString);
		}
        HistoryHandler();

        if(isRecording()){
            record.setCommands("delete: "+splitString);
        }
    }
    /**
     * Set the selection to start and end at certain indexes
     * @param start beginning index of the selection
     * @param stop ending index of the selection
     */
    @Override
    public void setSelection(int start, int stop) {
        this.selection.setBeginIndex(start);
        this.selection.setEndIndex(stop);

        if(isRecording()){
            record.setCommands("setSelection: "+this.selection.getBeginIndex() + " " + this.selection.getEndIndex());
        }
    }

    /**
     * Starts the record
     */
    public void startRecording() {
        this.record.startRecord();
    }

    /**
     * Stop the record
     */
    @Override
    public void stopRecording() {
        this.record.stopRecord();
    }

    /**
     * Returns status of recording
     * @return true if the recording is active, false otherwise
     */
    @Override
    public boolean isRecording() {
        return this.record.getRecordFlag();
    }

    /**
     * Provides the recorded contents
     *
     * @return a copy of the record's contents in format  « command:buffertext »
     */
    @Override
    public ArrayList<String> replay() {
        return this.record.getCommands();
    }
    /**
     * Return to previous state in the buffer
     */
    @Override
    public void undo() {
        Buffer previous = history.getPreviousBuffer();
        if(previous != null)
            this.setBuffer(new Buffer(previous.getText()));
    }
    /**
     * Forward to the next state in the buffer
     */
    @Override
    public void redo() {
        Buffer next = history.getNextBuffer();
        if(next != null)
            this.setBuffer(new Buffer(next.getText()));
    }
    /**
     * Set a buffer object as the buffer used by the engine
     * @param b buffer to be set
     */
    @Override
    public void setBuffer(Buffer b) {
        this.buffer = b;
    }

    /**
     * Function to be executed every time the buffer is modified
     */
    private void HistoryHandler() {
        history.addBuffer(buffer);
        this.setBuffer(new Buffer(buffer.getText()));

    }
    /**
     * Retrieve the history of states of the buffer
     * @return the list of states of the buffer
     */
    public HistoryManager getHistory(){
        return history;
    }


}
