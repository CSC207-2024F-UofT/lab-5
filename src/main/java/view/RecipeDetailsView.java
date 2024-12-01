package view;

import entity.CommonUser;
import entity.Recipe;
import interface_adapter.recipe_details.RecipeDetailsState;
import interface_adapter.recipe_details.RecipeDetailsViewModel;
import interface_adapter.recipe_review.RecipeReviewController;
import use_case.review_recipe.RecipeReviewInputBoundary;
import use_case.review_recipe.ReviewRecipeInputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URI;
import java.util.Map;

public class RecipeDetailsView extends JPanel implements ActionListener, PropertyChangeListener {

    private final RecipeDetailsViewModel recipeDetailsViewModel;
    private final RecipeReviewController recipeReviewController;
    private final CommonUser user;

    private JLabel recipeNameLabel;
    private JLabel caloriesLabel;
    private JLabel servingsLabel;
    private JLabel urlLabel;
    private JPanel nutrientsPanel;

    private final JLabel rating = new JLabel("Rating:");
    private final JTextField ratingInputField = new JTextField(15);
    private final JButton saveButton = new JButton("Save");

    public RecipeDetailsView(RecipeDetailsViewModel recipeDetailsViewModel, RecipeReviewController recipeReviewController, CommonUser user) {
        this.recipeDetailsViewModel = recipeDetailsViewModel;
        this.recipeReviewController = recipeReviewController;
        this.user = user;

        recipeDetailsViewModel.addPropertyChangeListener(this);

        // final JLabel title = new JLabel(RecipeDetailsViewModel.TITLE_LABEL);
        // title.setAlignmentX(Component.CENTER_ALIGNMENT);

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
        this.add(saveButton);

        saveButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            handleSaveAction();
        }
    }

    private void handleSaveAction() {
        try {
            final RecipeDetailsState currentState = recipeDetailsViewModel.getState();
            if (currentState == null) {
                JOptionPane.showMessageDialog(this, "No recipe selected to save.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            final Recipe recipe = currentState.getRecipe();
            final int userRating = Integer.parseInt(ratingInputField.getText());
            user.addRecipe(recipe, userRating);
            recipeReviewController.switchToSavedrecipesView();
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for the rating.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("recipeDetails")) {
            updateView(recipeDetailsViewModel.getState());
        }
    }

    private void updateView(RecipeDetailsState state) {
        if (state == null) {
            final Recipe recipe = state.getRecipe();
            recipeNameLabel.setText("Recipe: " + recipe.getName());
            caloriesLabel.setText("Calories: " + recipe.getCalories());
            servingsLabel.setText("Servings :" + recipe.getServings());
            urlLabel.setText("<html><a href=''>" + recipe.getUrl() + "</a></html>");

            nutrientsPanel.removeAll();
            final Map<String, Integer> nutrients = recipe.getNutrients();
            if (nutrients != null) {
                for (Map.Entry<String, Integer> entry : nutrients.entrySet()) {
                    nutrientsPanel.add(new JLabel(entry.getKey()));
                }
            }
        }
    }

    public static void main(String[] args) {
        // Create a dummy RecipeDetailsViewModel
        final ReviewRecipeInputBoundary recipeReviewInputBoundary = null;
        // recipeReviewInputBoundary = new RecipeReviewInputBoundary();
        final RecipeDetailsViewModel viewModel = new RecipeDetailsViewModel();
        final RecipeReviewController recipeReviewController = new RecipeReviewController(recipeReviewInputBoundary);
        final CommonUser user = new CommonUser("Adya", "adya");

        // Create an instance of RecipeDetailsView
        final RecipeDetailsView recipeDetailsView = new RecipeDetailsView(viewModel, recipeReviewController, user);

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
