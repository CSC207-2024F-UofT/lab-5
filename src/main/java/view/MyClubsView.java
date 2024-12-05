package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import interface_adapter.ViewManagerModel;
import interface_adapter.exit_bookclub.ExitClubController;
import interface_adapter.my_clubs.MyClubsController;
import interface_adapter.my_clubs.MyClubsState;
import interface_adapter.my_clubs.MyClubsViewModel;
import interface_adapter.show_books.ShowBooksController;

/**
 * The view for my clubs use case.
 */
public class MyClubsView extends JPanel implements PropertyChangeListener {
    private static final int TITLE_SIZE = 24;
    private static final int TABLE_SIZE = 18;

    private final String viewName = "my clubs";
    private final MyClubsViewModel myClubsViewModel;
    private MyClubsController myClubsController;
    private ExitClubController exitClubController;

    private ShowBooksController showBooksController;

    private final JLabel title;
    private final JButton notes;
    private final JButton books;
    private final JButton exit;

    private final JButton back;

    private final JTable myClubs;
    private final String[] columnNames = {"Club Name", "Description"};
    private final DefaultTableModel tableModel;

    private final ViewManagerModel viewManagerModel;

    public MyClubsView(MyClubsViewModel myClubsViewModel, ViewManagerModel viewManagerModel) {
        this.myClubsViewModel = myClubsViewModel;
        this.viewManagerModel = viewManagerModel;
        myClubsViewModel.addPropertyChangeListener(this);

        title = new JLabel(myClubsViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, TITLE_SIZE));
        tableModel = new DefaultTableModel(columnNames, 0);

        myClubs = new JTable(tableModel);
        myClubs.setCellSelectionEnabled(true);
        myClubs.setRowSelectionAllowed(false);
        myClubs.setColumnSelectionAllowed(false);
        notes = new JButton(MyClubsViewModel.NOTES_LABEL);
        books = new JButton(MyClubsViewModel.BOOKS_LABEL);
        exit = new JButton(MyClubsViewModel.EXIT_LABEL);
        back = new JButton("Back");

        final JTableHeader tableHeader = myClubs.getTableHeader();
        tableHeader.setFont(new Font("Arial", Font.BOLD, TABLE_SIZE));
        tableHeader.setBackground(Color.WHITE);
        myClubs.setBackground(Color.WHITE);

        myClubs.getColumnModel().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent evt) {

                if (!evt.getValueIsAdjusting()) {
                    final int selectedColumn = myClubs.getSelectedColumn();
                    if (selectedColumn == 1) {
                        final int selectedRow = myClubs.getSelectedRow();
                        final String selectedClub = (String) myClubs.getValueAt(selectedRow, 0);
                        final MyClubsState myClubsState = myClubsViewModel.getState();
                        myClubsState.setCurrentClub(selectedClub);
                        myClubsViewModel.setState(myClubsState);
                    }
                    else {
                        final int selectedRow = myClubs.getSelectedRow();
                        final String selectedClub = (String) myClubs.getValueAt(selectedRow, selectedColumn);
                        final MyClubsState myClubsState = myClubsViewModel.getState();
                        myClubsState.setCurrentClub(selectedClub);
                        myClubsViewModel.setState(myClubsState);
                    }
                }

            }
        });

        notes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                final String currentClub = myClubsViewModel.getState().getCurrentClub();
                myClubsController.switchToShowMessageView(currentClub);
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String currentClub = myClubsViewModel.getState().getCurrentClub();
                final String currentUsername = myClubsViewModel.getState().getCurrentUsername();
                exitClubController.execute(currentUsername, currentClub);
                JOptionPane.showMessageDialog(null, "You have left " + currentClub);
            }
        });

        books.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String currentClub = myClubsViewModel.getState().getCurrentClub();
                showBooksController.execute(currentClub);
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == back) {
                    viewManagerModel.setState("logged in");
                    viewManagerModel.firePropertyChanged();

                }
            }
        });


        final JScrollPane scrollPane = new JScrollPane(myClubs);
        final JPanel buttons = new JPanel();
        buttons.add(notes);
        buttons.add(books);
        buttons.add(exit);
        buttons.add(back);

        title.setBackground(Color.WHITE);
        scrollPane.setBackground(Color.WHITE);
        buttons.setBackground(Color.WHITE);
        this.setBackground(Color.WHITE);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(scrollPane);
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final MyClubsState state = (MyClubsState) evt.getNewValue();
        final Map<String, String> newClubs = state.getMyClubs();
        // clears table
        tableModel.setRowCount(0);
        for (Map.Entry<String, String> entry : newClubs.entrySet()) {
            // adds entry of name and description in the table
            tableModel.addRow(new String[]{entry.getKey(), entry.getValue()});
        }
    }

    public void setMyClubsController(MyClubsController myClubsController) {
        this.myClubsController = myClubsController;
    }

    public String getViewName() {
        return viewName;
    }

    public void setExitClubController(ExitClubController exitClubController) {
        this.exitClubController = exitClubController;
    }

    public void setShowBooksController(ShowBooksController showBooksController) {
        this.showBooksController = showBooksController;
    }
}

