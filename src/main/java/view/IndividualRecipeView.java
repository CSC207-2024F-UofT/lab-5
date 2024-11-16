package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import entity.*;
import view.NutritionView;

public class IndividualRecipeView extends JFrame implements ActionListener {
    private JButton nutritionButton;
    private JButton bookmarkButton;
    private JList<Ingredient> ingredientJList;
    private Recipe recipe;

    public IndividualRecipeView(Recipe recipe) {
        this.recipe = recipe;

        // Initialize ingredient list
        final DefaultListModel<Ingredient> listModel = new DefaultListModel<>();
        for (Ingredient ingredient : recipe.getIngredients()) {
            listModel.addElement(ingredient);
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

    @Override
    public void actionPerformed(ActionEvent event) {
//        if (event.getSource() == nutritionButton) {
//            new NutritionView(recipe);
//        } else if (event.getSource() == bookmarkButton) {
//            if (!recipe.isBookmarked()) {
//                recipe.setBookmarked(true);
//                JOptionPane.showMessageDialog(this, "Recipe added to bookmarks!");
//            } else {
//                JOptionPane.showMessageDialog(this, "Recipe is already bookmarked.");
//            }
//        }
    }
}
