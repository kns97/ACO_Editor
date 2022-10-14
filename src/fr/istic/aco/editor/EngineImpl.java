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
        buffer = new Buffer(0);
        selection = new SelectionImpl(buffer.getId());
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
    public void cutSelectedText() {
        int start = selection.getBeginIndex();
        int stop = selection.getEndIndex();
        String text = buffer.getText();
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
    public void copySelectedText() {
        int start = selection.getBeginIndex();
        int stop = selection.getEndIndex();
        String text = buffer.getText();
        String ctext = text.substring(start,stop);
        clipboard.setText(ctext);
    }

    /**
     * Replaces the text within the interval specified by the selection object with
     * the contents of the clipboard.
     */
    @Override
    public void pasteClipboard() {
        int start = selection.getBeginIndex();
        int stop = selection.getEndIndex();
        String text = buffer.getText();
        String btext = text.substring(0,start) + clipboard.getText() +text.substring(stop);
        buffer.setText(btext);

    }

    /**
     * Inserts a string in the buffer, which replaces the contents of the selection
     *
     * @param s the text to insert
     */
    @Override
    public void insert(String s) {
        int start = selection.getBeginIndex();
        int stop = selection.getEndIndex();
        String text = buffer.getText();
        String btext = text.substring(0,start) + s +text.substring(stop);
        buffer.setText(btext);
    }

    /**
     * Removes the contents of the selection in the buffer
     */
    @Override
    public void delete() {
        int start = selection.getBeginIndex();
        int stop = selection.getEndIndex();
        String text = buffer.getText();
        String btext = text.substring(0,start)+text.substring(stop);
        buffer.setText(btext);
    }
}
