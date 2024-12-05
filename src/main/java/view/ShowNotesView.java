package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import interface_adapter.ViewManagerModel;
import interface_adapter.show_notes.ShowNotesController;
import interface_adapter.show_notes.ShowNotesState;
import interface_adapter.show_notes.ShowNotesViewModel;

/**
 * The View for the Show Topics Use Case.
 */
public class ShowNotesView extends JPanel implements PropertyChangeListener {
    private final String viewName = "show Notes";

    private final ShowNotesViewModel showTopicsViewModel;
    private ShowNotesController showNotesController;

    private final JList<String> topicsList = new JList<>();
    private final JLabel title;
    private final JButton viewMessages;
    private final JButton newNote;

    private final JButton back;

    private final ViewManagerModel viewManagerModel;

    public ShowNotesView(ShowNotesViewModel showTopicsViewModel, ViewManagerModel viewManagerModel) {
        this.showTopicsViewModel = showTopicsViewModel;
        this.viewManagerModel = viewManagerModel;
        showTopicsViewModel.addPropertyChangeListener(this);

        title = new JLabel(ShowNotesViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        topicsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        final JScrollPane scrollPane = new JScrollPane(topicsList);

        viewMessages = new JButton(ShowNotesViewModel.VIEW_MESSAGES_BUTTON_LABEL);
        newNote = new JButton(ShowNotesViewModel.NEW_NOTE_BUTTON_LABEL);
        back = new JButton("Back");

        topicsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent evt) {
                if (!evt.getValueIsAdjusting()) {
                    final String selectedTopic = topicsList.getSelectedValue();
                    final ShowNotesState currentState = showTopicsViewModel.getState();
                    currentState.setCurrentNotes(selectedTopic);
                    showTopicsViewModel.setState(currentState);

                }
            }
        });

        viewMessages.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        final String currentNote = showTopicsViewModel.getState().getCurrentNotes();
                        showNotesController.switchToAddMessageView(currentNote);
                    }
                }

        );

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource() == back) {
                    viewManagerModel.setState("my clubs");
                    viewManagerModel.firePropertyChanged();
                }
            }
        });

        final JPanel buttons = new JPanel();
        buttons.add(viewMessages);
        buttons.add(newNote);
        buttons.add(back);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(scrollPane);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (evt.getPropertyName().equals("current club")) {
            // corresponds to the switch to show notes view
            showNotesController.execute();
        }
        else if (evt.getPropertyName().equals("error")) {
            final ShowNotesState state = (ShowNotesState) evt.getNewValue();
            JOptionPane.showMessageDialog(this, state.getErrorMessage());
        }

        else {
            final ShowNotesState state = (ShowNotesState) evt.getNewValue();
            topicsList.setListData(state.getTopics().toArray(new String[0]));
            title.setText(ShowNotesViewModel.TITLE_LABEL + showTopicsViewModel.getState().getCurrentClub());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setShowNotesController(ShowNotesController controller) {
        this.showNotesController = controller;
    }
}
