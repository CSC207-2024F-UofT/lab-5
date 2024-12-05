package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.*;

import interface_adapter.my_reviews.MyReviewsController;
import interface_adapter.my_reviews.MyReviewsViewModel;

/**
 * The view for the "my reviews" use case.
 */

public class MyReviewsView extends JPanel implements PropertyChangeListener {
    private final String viewName = "My_ReviewsView";
    private final MyReviewsViewModel viewModel;
    private final JPanel reviewsPanel;
    private MyReviewsController controller;

    public MyReviewsView(MyReviewsViewModel viewModel) {
        this.viewModel = viewModel;
        final List<String> reviews = viewModel.getReviews();
        this.viewModel.addPropertyChangeListener(this);

        this.setLayout(new BorderLayout());

        final JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        final JButton backButton = new JButton("Back");
        final JLabel titleLabel = new JLabel("My Reviews");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(backButton);
        topPanel.add(titleLabel);

        // Action listener for back button
        backButton.addActionListener(e -> {
            controller.goBack();
        });

        reviewsPanel = new JPanel();
        reviewsPanel.setLayout(new BoxLayout(reviewsPanel, BoxLayout.Y_AXIS));
        reviewsPanel.setBackground(new Color(255, 243, 212));

        final JScrollPane scrollPane = new JScrollPane(reviewsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);

        updateView(reviews);
    }

    public void setMyReviewsController(MyReviewsController controller1) {
        this.controller = controller1;
    }

    /**
     * Update the view with formated reviews.
     * @param formattedReviews the formated reviews to be passed to the view.
     */
    public void updateView(List<String> formattedReviews) {
        reviewsPanel.removeAll();

        if (formattedReviews == null || formattedReviews.isEmpty()) {
            reviewsPanel.add(new JLabel("No reviews available."));
        }
        else {
            for (String review : formattedReviews) {
                final JTextArea reviewArea = new JTextArea(review);
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
            final List<String> formattedReviews = (List<String>) evt.getNewValue();
            updateView(formattedReviews);
        }
    }

    public String getViewName() {
        return viewName;
    }
}
