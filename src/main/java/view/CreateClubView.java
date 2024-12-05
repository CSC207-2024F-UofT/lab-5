package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_club.CreateClubController;
import interface_adapter.create_club.CreateClubState;
import interface_adapter.create_club.CreateClubViewModel;

/**
 * The CreateClubView.
 */
public class CreateClubView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "create club";
    private final CreateClubViewModel createClubViewModel;
    private final int size2 = 24;

    private final JTextField clubNameInputField = new JTextField(15);
    private final JLabel clubNameErrorField = new JLabel();

    private final JTextField clubDesInputField = new JTextField(15);
    private final JLabel clubDesErrorField = new JLabel();

    private final JButton createButton;
    private final JButton cancelButton;
    private CreateClubController createClubController;
    private final ViewManagerModel viewManagerModel;

    public CreateClubView(CreateClubViewModel createClubViewModel, ViewManagerModel viewManagerModel) {
        this.createClubViewModel = createClubViewModel;
        this.createClubViewModel.addPropertyChangeListener(this);
        this.viewManagerModel = viewManagerModel;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        final JLabel banner = new JLabel("Create a Club!", SwingConstants.CENTER);
        banner.setFont(new Font("Brush Script MT", Font.ITALIC, getSize2()));
        banner.setOpaque(true);
        banner.setBackground(Color.WHITE);
        banner.setForeground(Color.BLACK);

        final LabelTextPanel clubNameInfo = new LabelTextPanel(
                new JLabel("Club Name"), clubNameInputField);
        final LabelTextPanel clubDesInfo = new LabelTextPanel(
                new JLabel("Club Description"), clubDesInputField);

        clubNameInfo.setBackground(Color.WHITE);
        clubDesInfo.setBackground(Color.WHITE);

        final JPanel buttons = new JPanel();
        createButton = new JButton("create");
        buttons.add(createButton);
        cancelButton = new JButton("cancel");
        buttons.add(cancelButton);

        createButton.addActionListener(evt -> {
            final CreateClubState currentState = createClubViewModel.getState();
            createClubController.execute(
                    currentState.getBookclub(),
                    currentState.getClubDescription(),
                    currentState.getUsername()
            );
        });

        cancelButton.addActionListener(evt -> {
            viewManagerModel.setState("logged in");
            viewManagerModel.firePropertyChanged();
        });

        this.add(banner);
        this.add(clubNameInfo);
        this.add(clubNameErrorField);
        this.add(clubDesInfo);
        this.add(buttons);
        buttons.setBackground(Color.WHITE);
        this.setBackground(Color.WHITE);

        clubNameListener();
        clubDesListener();
    }

    private void clubNameListener() {
        clubNameInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final CreateClubState currentState = createClubViewModel.getState();
                currentState.setBookclub(clubNameInputField.getText());
                createClubViewModel.setState(currentState);
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

    private void clubDesListener() {
        clubDesInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final CreateClubState currentState = createClubViewModel.getState();
                currentState.setClubDescription(clubDesInputField.getText());
                createClubViewModel.setState(currentState);
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
        if ("state".equals(evt.getPropertyName())) {
            final CreateClubState state = (CreateClubState) evt.getNewValue();
            clubNameInputField.setText(state.getBookclub());
            clubDesInputField.setText(state.getClubDescription());
            clubNameErrorField.setText(state.getErrorMessage());
        }
        else if ("error".equals(evt.getPropertyName())) {
            final String error = createClubViewModel.getState().getErrorMessage();
            JOptionPane.showMessageDialog(this, error);
            System.err.println("Error: " + error);
        }
        else if ("success".equals(evt.getPropertyName())) {
            final String successMessage = createClubViewModel.getState().getSuccessMessage();
            final int option = JOptionPane.showOptionDialog(this,
                    successMessage,
                    "Success",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new Object[]{"OK"},
                    "OK");
            if (option == 0) {
                createClubController.switchToLoggedInView();
            }
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setController(CreateClubController createController) {
        this.createClubController = createController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public int getSize2() {
        return size2;
    }
}
