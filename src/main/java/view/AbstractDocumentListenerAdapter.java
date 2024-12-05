package view;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * An adapter for DocumentListener to simplify implementation.
 */
public abstract class AbstractDocumentListenerAdapter implements DocumentListener {

    /**
     * Abstract method to handle updates. Implement this in subclasses.
     */
    public abstract void onUpdate();

    @Override
    public void insertUpdate(DocumentEvent e) {
        onUpdate();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        onUpdate();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        onUpdate();
    }
}
