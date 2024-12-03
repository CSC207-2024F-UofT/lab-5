package view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import interface_adapter.leave_review.LeaveReviewController;
import interface_adapter.leave_review.LeaveReviewViewModel;

public class LeaveReviewView extends JPanel implements PropertyChangeListener {
    private String viewName;
    private final LeaveReviewViewModel viewModel;
    private final JPanel leaveReviewPanel;
    private final JButton submitButton;
    private final JLabel wordCountLabel;
    private final JPanel starRatingPanel;
    private final JLabel[] stars = new JLabel[5];
    private double selectedRating = 0;
    private final int maxWords = 250;
    private LeaveReviewController controller;

    public LeaveReviewView(LeaveReviewViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);
        this.viewName = viewModel.getViewName();

        this.setLayout(new BorderLayout());

        // Top panel with title and navigation buttons
        final JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        final JButton backButton = new JButton("Back");
        final JLabel titleLabel = new JLabel("Leave A Review");
        final JButton homeButton = new JButton("Home");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(backButton);
        topPanel.add(titleLabel);
        topPanel.add(homeButton);

        homeButton.addActionListener(e -> controller.goHome());
        backButton.addActionListener(e -> controller.goBack());

        leaveReviewPanel = new JPanel();
        final JTextArea reviewTextArea = new JTextArea(10, 30);
        reviewTextArea.setLineWrap(true);
        reviewTextArea.setWrapStyleWord(true);
        reviewTextArea.setFont(new Font("Arial", Font.PLAIN, 14));

        final JScrollPane jScrollPane = new JScrollPane(reviewTextArea);
        wordCountLabel = new JLabel("0/" + maxWords + " words");
        wordCountLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        submitButton = new JButton("Submit");
        submitButton.setEnabled(false);

        // Text area listener for word count
        reviewTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateWordCount(reviewTextArea.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateWordCount(reviewTextArea.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateWordCount(reviewTextArea.getText());
            }
        });

        // Submit button listener
        submitButton.addActionListener(e -> {
            String review = reviewTextArea.getText().trim();
            if (selectedRating != 0) {
                if (wordCount(review) <= maxWords) {
                    if (review.isEmpty()) {
                        controller.leaveReview(viewModel.getUsername(), selectedRating, viewModel.getMovieName());
                    } else {
                        controller.leaveReview(viewModel.getUsername(), selectedRating, review, viewModel.getMovieName());
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Review exceeds 250 words!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a star rating!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Star rating panel
        starRatingPanel = new JPanel();
        for (int i = 0; i < stars.length; i++) {
            final int starIndex = i + 1;
            stars[i] = new JLabel();
            stars[i].setIcon(new ImageIcon("images/icons8-star-50.png"));
            stars[i].setPreferredSize(new Dimension(32, 32));

            stars[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    highlightStars(starIndex);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    updateStars();
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    selectedRating = starIndex;
                    updateStars();
                }
            });

            starRatingPanel.add(stars[i]);
        }
        starRatingPanel.setPreferredSize(new Dimension(200, 50));

        // Assemble components
        this.add(topPanel, BorderLayout.NORTH);
        leaveReviewPanel.add(jScrollPane);
        leaveReviewPanel.add(wordCountLabel);
        leaveReviewPanel.add(starRatingPanel);
        leaveReviewPanel.add(submitButton);
        this.add(leaveReviewPanel, BorderLayout.CENTER);
    }

    private void highlightStars(int index) {
        for (int i = 0; i < stars.length; i++) {
            if (i < index) {
                stars[i].setIcon(new ImageIcon("images/icons8-star-filled-48.png"));
            } else {
                stars[i].setIcon(new ImageIcon("images/icons8-star-50.png"));
            }
        }
    }

    private void updateStars() {
        for (int i = 0; i < stars.length; i++) {
            if (i < selectedRating) {
                stars[i].setIcon(new ImageIcon("images/icons8-star-filled-48.png"));
            } else {
                stars[i].setIcon(new ImageIcon("images/icons8-star-50.png"));
            }
        }
    }

    private void updateWordCount(String text) {
        int words = wordCount(text);
        wordCountLabel.setText(words + "/" + maxWords + " words");
        submitButton.setEnabled(words <= maxWords && !text.isEmpty());
    }

    private int wordCount(String text) {
        if (text.isEmpty()) return 0;
        return text.split("\\s+").length;
    }
    public String getViewName() {
        return this.viewName;
    }

    public double getSelectedRating() {
        return (Double) selectedRating;
    }

    @Override
    public void propertyChange(java.beans.PropertyChangeEvent evt) {
    }

    public void setLeaveReviewController(LeaveReviewController leaveReviewController) {
        this.controller = leaveReviewController;
    }
}