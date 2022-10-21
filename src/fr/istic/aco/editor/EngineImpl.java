package fr.istic.aco.editor;

import fr.istic.aco.editor.utils.Buffer;
import fr.istic.aco.editor.utils.Clipboard;
import fr.istic.aco.editor.utils.SelectionImpl;

public class EngineImpl implements Engine {
    /**
     * Provides access to the selection control object
     *
     * @return the selection object
     */
    private Buffer buffer;
    private Selection selection;
    private Clipboard clipboard;

    public EngineImpl(){
     
    	buffer = new Buffer();
        selection = new SelectionImpl(buffer);
        clipboard = new Clipboard();
    
    }

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

        return buffer.getText();
    
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
		
		if(start > text.length()) clipboard.setText("");
		
		if(stop > text.length()) clipboard.setText(text.substring(start));
		
		clipboard.setText(text.substring(start, stop));
        String btext = text.substring(0,start)+text.substring(stop);
        String ctext = text.substring(start,stop);
        buffer.setText(btext);
        clipboard.setText(ctext);
    
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
		
		if(start > text.length()) clipboard.setText("");
		
		if(stop > text.length()) clipboard.setText(text.substring(start));
		
		clipboard.setText(text.substring(start, stop));
    
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
        
        if( stop < start || stop < 0 || start < 0) throw new IndexOutOfBoundsException("Values are not valid");
		
		if( text.length() <= start || text.length() == 0) {
			
			buffer.setText(text + clipboard.getText());
			
		}else if (text.length() <= stop){
		
			String splitString = text.substring(0, start);
			buffer.setText(splitString + clipboard.getText());
		
		}else{
			
		    String btext = text.substring(0,start) + clipboard.getText() +text.substring(stop);
		    buffer.setText(btext);
        
		}
        
  

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
    }

    /**
     * Removes the contents of the selection in the buffer
     */
    @Override
    public void delete() throws IndexOutOfBoundsException{
        
    	int start = selection.getBeginIndex();
        int stop = selection.getEndIndex();        
        String text = buffer.getText();
        
        if( stop < start || stop < 0 || start < 0) throw new IndexOutOfBoundsException("Values are not valid");
		
		if( text.length() <= start || text.length() == 0) {
			
			return;
			
		}else if (text.length() <= stop){
		
			String splitString = text.substring(0, start);
			buffer.setText(splitString);
		
		}else{
			
			String btext = text.substring(0,start)+text.substring(stop);
	        buffer.setText(btext);	
        
		}
    }
}
