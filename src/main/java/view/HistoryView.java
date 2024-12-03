package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.change_password.LoggedInState;
import interface_adapter.history.HistoryController;
import interface_adapter.history.HistoryState;
import interface_adapter.history.HistoryViewModel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * The view of translation history.
 */
public class HistoryView extends JPanel implements PropertyChangeListener {
    private final String viewname = "history";
    private HistoryViewModel historyViewModel;
    private HistoryController historyController;

    private final JButton back;
    private final JButton clear;

    public HistoryView(HistoryViewModel historyViewModel) {
        this.historyViewModel = historyViewModel;
        historyViewModel.addPropertyChangeListener(this);

        final JPanel first = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.back = new JButton("Back");
        this.clear = new JButton("Clear History");
        first.add(back);
        first.add(clear);

        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        historyController.switchBackToTranslateView();
                    }
                }
        );

        final JScrollPane scroll = new JScrollPane();
        final HistoryState historyState = historyViewModel.getState();
        final Map<String, String> historyMap = historyState.getHistory();
        final JPanel insidePanel = new JPanel();
        insidePanel.setLayout(new BoxLayout(insidePanel, BoxLayout.Y_AXIS));
        insidePanel.add(first);
        if (historyMap != null) {
            for (String key: historyMap.keySet()) {
                final JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                final JLabel labelKey = new JLabel(key);
                final JLabel labelValue = new JLabel(historyMap.get(key));
                panel.add(labelKey);
                panel.add(labelValue);
                insidePanel.add(panel, 1);
            }
        }

        clear.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        insidePanel.removeAll();
                        insidePanel.add(first);
                    }
                }
        );

        scroll.add(insidePanel);
        this.add(scroll);
        scroll.setViewportView(insidePanel);
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    /**
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("history")) {
            this.back.setVisible(true);
        }
    }

    public String getViewName() {
        return viewname;
    }

    public void setHistoryController(HistoryController historyController) {
        this.historyController = historyController;
    }
}
