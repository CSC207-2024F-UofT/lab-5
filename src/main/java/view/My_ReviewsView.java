package view;
import interface_adapter.reviews.My_ReviewsController;
import interface_adapter.reviews.My_ReviewsViewModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import interface_adapter.ViewModel;

public class My_ReviewsView extends JPanel implements PropertyChangeListener {
    private final String viewName = "MyReviewsView";
    private final My_ReviewsViewModel viewModel;
    private final JPanel reviewsPanel;
    private My_ReviewsController controller; // Add a reference to the Controller

    public My_ReviewsView(My_ReviewsViewModel viewModel) {
        this.viewModel = viewModel;

        // Register as an observer
        this.viewModel.addPropertyChangeListener(this);

        // Set layout
        this.setLayout(new BorderLayout());

        // Top panel for navigation
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton backButton = new JButton("Back");
        JLabel titleLabel = new JLabel("My Reviews");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(backButton);
        topPanel.add(titleLabel);

        // Action listener for back button
        backButton.addActionListener(e -> {
            if (controller != null) {
                controller.getReviews(/* Pass appropriate user object */ null);
            } else {
                JOptionPane.showMessageDialog(this, "Controller is not set!");
            }
        });

        // Reviews panel
        reviewsPanel = new JPanel();
        reviewsPanel.setLayout(new BoxLayout(reviewsPanel, BoxLayout.Y_AXIS));
        reviewsPanel.setBackground(new Color(255, 243, 212)); // Light yellow background

        // Scrollable area
        JScrollPane scrollPane = new JScrollPane(reviewsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Smooth scrolling

        // Add components to the main panel
        this.add(topPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);

        // Initial render
        updateView(viewModel);
    }

    public void setMyReviewsController(My_ReviewsController controller) {
        this.controller = controller;
    }

    public void updateView(My_ReviewsViewModel viewModel) {
        reviewsPanel.removeAll();

        List<String> formattedReviews = viewModel.getReviews();
        if (formattedReviews == null || formattedReviews.isEmpty()) {
            reviewsPanel.add(new JLabel("No reviews available."));
        } else {
            for (String review : formattedReviews) {
                JTextArea reviewArea = new JTextArea(review);
                reviewArea.setWrapStyleWord(true);
                reviewArea.setLineWrap(true);
                reviewArea.setEditable(false);
                reviewArea.setOpaque(true);
                reviewArea.setBackground(new Color(254, 243, 169));
                reviewArea.setFont(new Font("Arial", Font.PLAIN, 14));
                reviewArea.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.GRAY, 1),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));
                reviewsPanel.add(reviewArea);
                reviewsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }

        reviewsPanel.revalidate();
        reviewsPanel.repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            My_ReviewsViewModel newViewModel = (My_ReviewsViewModel) evt.getNewValue();
            updateView(newViewModel);
        }
    }

    public String getViewName() {
        return viewName;
    }
}
