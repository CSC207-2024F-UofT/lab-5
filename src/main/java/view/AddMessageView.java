package view;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.AbstractMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_message.AddMessageController;
import interface_adapter.add_message.AddMessageState;
import interface_adapter.add_message.AddMessageViewModel;

/**
 * The View for when the user is adding/viewing messages.
 */

public class AddMessageView extends JPanel implements PropertyChangeListener {
    private static final Integer FONTSIZE = 20;
    private static final Integer ROWS = 25;
    private static final Integer COLUMNS = 25;
    private static final Integer TIME = 500;

    private final String viewName = "add message";

    private final AddMessageViewModel addMessageViewModel;
    private final JTextField messageInputField = new JTextField(25);
    private AddMessageController addMessageController;
    private JTextArea messagesArea = new JTextArea(ROWS, COLUMNS);
    private final JLabel title;
    private final Timer timer;
    private final JButton post;

    private final JButton back;

    private final ViewManagerModel viewManagerModel;

    public AddMessageView(AddMessageViewModel addMessageViewModel, ViewManagerModel viewManagerModel) {
        this.addMessageViewModel = addMessageViewModel;
        this.viewManagerModel = viewManagerModel;
        addMessageViewModel.addPropertyChangeListener(this);

        title = new JLabel(AddMessageViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        post = new JButton(AddMessageViewModel.POST_BUTTON_LABEL);
        back = new JButton("Back");

        final JPanel textboxPanel = new JPanel();
        textboxPanel.add(messageInputField);
        textboxPanel.add(post);
        textboxPanel.add(back);

        messagesArea.setLineWrap(true);
        messagesArea.setWrapStyleWord(true);
        messagesArea.setEditable(false);
        messagesArea.setFont(new Font("Arial", Font.PLAIN, FONTSIZE));
        final JScrollPane scrollPane = new JScrollPane(messagesArea);
        final JPanel messagesPanel = new JPanel();
        messagesPanel.add(scrollPane);

        post.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        final AddMessageState currentState = addMessageViewModel.getState();
                        addMessageController.execute(currentState.getText(), currentState.getCurrentUsername());
                        messageInputField.setText("");
                    }
                }
        );
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == back) {
                    viewManagerModel.setState("show Notes");
                    viewManagerModel.firePropertyChanged();
                }
            }
        }
        );

        timer = new Timer(TIME, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // This code runs every 500ms
                addMessageController.showMessages(addMessageViewModel.getState().getCurrentNote());
            }
        });

        addMessageListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(messagesPanel);
        this.add(textboxPanel);
    }

    private void addMessageListener() {
        messageInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final AddMessageState currentState = addMessageViewModel.getState();
                currentState.setText(messageInputField.getText());
                addMessageViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        timer.start();
        final AddMessageState state = (AddMessageState) evt.getNewValue();
        final List<AbstractMap.SimpleEntry<String, String>> messagesList = state.getMessagesList();
        messagesArea.setText("");
        for (AbstractMap.SimpleEntry<String, String> entry: messagesList) {
            final String username = entry.getKey();
            final String text = entry.getValue();
            messagesArea.append(username + ": " + text + "\n");
        }
        // current discussion is set by show discussions usecase
        title.setText(AddMessageViewModel.TITLE_LABEL + addMessageViewModel.getState().getCurrentNote());

    }

    public String getViewName() {
        return viewName;
    }

    public void setAddMessageController(AddMessageController controller) {
        this.addMessageController = controller;
    }

}
