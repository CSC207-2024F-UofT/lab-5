package view;

import interface_adapter.recipe_details.RecipeDetailsViewModel;
import interface_adapter.signup.SignupViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URI;

public class RecipeDetailsView extends JPanel implements ActionListener, PropertyChangeListener {

    private final RecipeDetailsViewModel recipeDetailsViewModel;
    private JLabel recipeNameLabel;
    private JLabel caloriesLabel;
    private JLabel servingsLabel;
    private JLabel urlLabel;
    private JPanel nutrientsPanel;

    private final JLabel rating = new JLabel("Rating:");
    private final JTextField ratingInputField = new JTextField(15);

    private final JLabel comment = new JLabel("Comment:");
    private final JTextField commentInputField = new JTextField(15);

    private final JButton saveButton = new JButton("Save");

    public RecipeDetailsView(RecipeDetailsViewModel recipeDetailsViewModel) {
        this.recipeDetailsViewModel = recipeDetailsViewModel;
        recipeDetailsViewModel.addPropertyChangeListener(this);

        //final JLabel title = new JLabel(RecipeDetailsViewModel.TITLE_LABEL);
        //title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Title
        final JLabel title = new JLabel("Recipe Details");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        // Recipe Name Panel
        final JPanel recipeNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        recipeNameLabel = new JLabel("Recipe: ");
        recipeNamePanel.add(recipeNameLabel);

        // Calories and Servings Panel
        final JPanel detailsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        caloriesLabel = new JLabel("Calories: ");
        servingsLabel = new JLabel("Servings: ");
        detailsPanel.add(caloriesLabel);
        detailsPanel.add(servingsLabel);

        // link panel
        urlLabel = new JLabel("<html><a href=''>View Full Recipe</a></html>");
        urlLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        urlLabel.setForeground(Color.BLUE);
        urlLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        urlLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("http://")); // Replace with recipe URL
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Nutrients Panel
        nutrientsPanel = new JPanel();
        nutrientsPanel.setLayout(new BoxLayout(nutrientsPanel, BoxLayout.Y_AXIS));
        nutrientsPanel.setBorder(BorderFactory.createTitledBorder("Nutrients"));


        // panel for rating
        final JPanel ratingInfo = new JPanel();
        ratingInfo.add(rating);
        ratingInfo.add(ratingInputField);

        // panel for comment
        final JPanel commentInfo = new JPanel();
        commentInfo.add(comment);
        commentInfo.add(commentInputField);

        // Set Layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add Components
        this.add(title);
        this.add(recipeNamePanel);
        this.add(detailsPanel);
        this.add(urlLabel);
        this.add(nutrientsPanel);
        this.add(ratingInfo);
        this.add(commentInfo);
        this.add(saveButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public static void main(String[] args) {
        // Create a dummy RecipeDetailsViewModel
        RecipeDetailsViewModel viewModel = new RecipeDetailsViewModel();

        // Create an instance of RecipeDetailsView
        RecipeDetailsView recipeDetailsView = new RecipeDetailsView(viewModel);

        // Create a JFrame to display the view
        JFrame frame = new JFrame("Recipe Details View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // Add RecipeDetailsView to the frame
        frame.add(recipeDetailsView);

        // Make the frame visible
        frame.setVisible(true);
    }

}
