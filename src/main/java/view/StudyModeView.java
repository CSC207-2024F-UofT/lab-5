package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interface_adapter.logout.LogoutController;
import interface_adapter.studymode.StudyModeController;
import interface_adapter.studymode.StudyModeState;
import interface_adapter.studymode.StudyModeViewModel;

/**
 * The View when the user select Study Mode.
 */
public class StudyModeView extends JPanel implements ActionListener {
    private final String viewName = "study mode";

    private LogoutController logoutController;
    private final StudyModeViewModel studymodeViewModel;
    private StudyModeController studyModeController;
    private final Color backgroundC = Color.decode("#11212D");

    private JButton backToModeSelection;

    public StudyModeView(StudyModeViewModel studyModeViewModel) {
        this.studymodeViewModel = studyModeViewModel;
        this.setBackground(backgroundC);

        final JPanel titlePanel = createTitle();
        final JPanel moduleTextPanel = createModuleTextPanel();
        final JPanel buttons = createButtonsPanel(studyModeViewModel);
        final JPanel buttonWrapper = createButtonWrapper();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(titlePanel);
        this.add(moduleTextPanel);
        this.add(buttons);
        this.add(buttonWrapper);
    }

    private JPanel createButtonsPanel(StudyModeViewModel studyModeViewModel) {
        final JPanel buttons = new JPanel();
        buttons.setBackground(Color.decode("#11212D"));
        final int buttonsRows = 2;
        final int buttonsCols = 3;
        final int buttonsHgap = 10;
        final int buttonsVgap = 10;
        buttons.setLayout(new GridLayout(buttonsRows, buttonsCols, buttonsHgap, buttonsVgap));

        final String foreColor = "#9BA8AB";
        final String backColor = "#253745";
        final String[] moduleNames = {"Module 1", "Module 2", "Module 3", "Module 4", "Module 5", "Module 6"};
        final JButton[] modules = new JButton[moduleNames.length];

        for (int i = 0; i < moduleNames.length; i++) {
            modules[i] = createModuleButton(moduleNames[i], foreColor, backColor, studyModeViewModel);
            buttons.add(modules[i]);
        }

        return buttons;
    }

    private JButton createModuleButton(String moduleName,
                                       String foreColor,
                                       String backColor,
                                       StudyModeViewModel studyModeViewModel) {
        final JButton moduleButton = new JButton(moduleName);
        moduleButton.setForeground(Color.decode(foreColor));
        moduleButton.setBackground(Color.decode(backColor));

        moduleButton.addActionListener(evt -> {
            final StudyModeState studyModeState = studyModeViewModel.getState();
            studyModeState.setModule(moduleName);
            studyModeViewModel.setState(studyModeState);
            studyModeViewModel.firePropertyChanged();
            studyModeController.execute(studyModeState.getModule());
            studyModeController.switchToStudyModeBeginView();
        });

        return moduleButton;
    }

    private JPanel createButtonWrapper() {
        final JPanel buttonWrapper = new JPanel();
        final int borderTop = 20;
        final int borderBottom = 20;
        final int borderLeft = 20;
        final int borderRight = 20;
        buttonWrapper.setLayout(new BoxLayout(buttonWrapper, BoxLayout.Y_AXIS));
        buttonWrapper.setOpaque(false);
        buttonWrapper.setBorder(BorderFactory.createEmptyBorder(borderTop, borderLeft, borderBottom, borderRight));

        backToModeSelection = new JButton("Back To Mode Selection");
        backToModeSelection.setAlignmentX(Component.CENTER_ALIGNMENT);
        styleBackToModeSelection();
        buttonWrapper.add(backToModeSelection);

        backToModeSelection.addActionListener(
                evt -> studyModeController.switchToModeSelectionView()
        );

        return buttonWrapper;
    }

    private void styleBackToModeSelection() {
        backToModeSelection.setBackground(Color.decode("#9BA8AB"));
        backToModeSelection.setForeground(Color.decode("#253745"));
        backToModeSelection.setFocusPainted(false);
        backToModeSelection.setOpaque(true);
        backToModeSelection.setBorderPainted(false);
    }

    private JPanel createTitle() {
        final JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.setBackground(backgroundC);

        final int titleFontSize = 25;
        final JLabel title = new JLabel("Study Mode\n");
        title.setFont(new Font("Times New Roman", Font.ITALIC, titleFontSize));
        title.setForeground(Color.decode("#4A5C6A"));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(title);
        return titlePanel;
    }

    private JPanel createModuleTextPanel() {
        final JPanel modulePanel = new JPanel();
        modulePanel.setLayout(new BoxLayout(modulePanel, BoxLayout.X_AXIS));
        modulePanel.setBackground(backgroundC);

        final JLabel moduleSelection = new JLabel("<html><div style='text-align: center; font-family: "
                + "\"Times New Roman\"; margin: 10px auto; color: #7DA0CA; font-size: 25'>"
                + "Please select a module from the below: ");
        moduleSelection.setAlignmentX(Component.CENTER_ALIGNMENT);
        modulePanel.add(moduleSelection);
        return modulePanel;
    }

    public String getViewName() {
        return viewName;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setStudyModeController(StudyModeController controller) {
        this.studyModeController = controller;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }
}
