package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import data_access.GetRecipeId;
import entity.*;

public class IndividualRecipeView extends JFrame implements ActionListener {
    private JButton nutritionButton;
    private JButton bookmarkButton;
    private JList<String> ingredientJList;
    private Recipe recipe;
    private URL imageUrl;
    private ImageIcon imageIcon;

    public IndividualRecipeView(Recipe recipe) {
        this.recipe = recipe;

        // Initialize ingredient list
        final DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Ingredient ingredient : recipe.getIngredients()) {
            listModel.addElement(ingredient.getName());
        }
        ingredientJList = new JList<>(listModel);
        final JScrollPane scrollPane = new JScrollPane(ingredientJList);

        // Initialize buttons
        nutritionButton = new JButton("Nutrition");
        bookmarkButton = new JButton("Bookmark");

        // Set up JFrame properties
        setTitle(recipe.getName());
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel with vertical BoxLayout - you need a Layout for each Panel
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        setContentPane(mainPanel);

        // initializing image
        try {
            // Specify the image URL
            this.imageUrl = new URL(recipe.getImage()); // Replace with your image URL

            // Load the image
            this.imageIcon = new ImageIcon(imageUrl);

            // Scale the image
            final Image scaledImage = imageIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(scaledImage);

            // Add the image to a JLabel
            final JLabel imageLabel = new JLabel(imageIcon);
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setVerticalAlignment(SwingConstants.CENTER);

            // Add the label to the frame
            mainPanel.add(imageLabel, BorderLayout.CENTER);

        } catch (Exception e) {
            // Handle exceptions, e.g., invalid URL or connection error
            final JLabel errorLabel = new JLabel("Failed to load image.");
            errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
            mainPanel.add(errorLabel, BorderLayout.CENTER);
            e.printStackTrace();
        }

        // Add components to main panel
        mainPanel.add(scrollPane);

        // Button panel with horizontal BoxLayout
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(nutritionButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add spacing
        buttonPanel.add(bookmarkButton);

        mainPanel.add(buttonPanel);

        // Add action listeners
        nutritionButton.addActionListener(this);
        bookmarkButton.addActionListener(this);

        // Display the frame
        setVisible(true);
    }

    GetRecipeId getGetRecipeId = new GetRecipeId();

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == nutritionButton) {
            int recipeId = getGetRecipeId.getRecipeIdByName(recipe.getName());
            new NutritionView(recipeId);
        }
    }
}

//        else if (event.getSource() == bookmarkButton) {
//            if (!recipe.isBookmarked()) {
//                recipe.setBookmarked(true);
//                JOptionPane.showMessageDialog(this, "Recipe added to bookmarks!");
//            } else {
//                JOptionPane.showMessageDialog(this, "Recipe is already bookmarked.");
//            }
//        }
//    }
//}
