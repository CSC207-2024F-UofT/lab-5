package view;

import entity.Ingredient;
import entity.Recipe;
import entity.Nutrition;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IndividualRecipeView extends JFrame implements ActionListener {
    private JButton homeButton;
    private JButton nutritionButton;
    private JButton bookmarkButton;
    private JList<Ingredient> ingredientJList;
    private DefaultListModel<Ingredient> listModel;
    private Recipe recipe;
    private Nutrition nutrition;

    public IndividualRecipeView(Recipe recipe) {
        // TODO double check this
        this.recipe = recipe;
        this.listModel = new DefaultListModel<>();
        for (Ingredient ingredient : recipe.getIngredients()) {
            listModel.addElement(ingredient);
        }
        ingredientJList = new JList<>(listModel);

        // this.nutrition = nutrition; - removed for now
        this.nutritionButton = new JButton("Nutrition");
        this.bookmarkButton = new JButton("Bookmark");

        setTitle(recipe.getName());
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // TODO fix the layout so components aren't on top of each other
        add(ingredientJList);
        add(nutritionButton);
        add(bookmarkButton);
        nutritionButton.addActionListener(this);
        bookmarkButton.addActionListener(this);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == nutritionButton) {
            new NutritionView(recipe);
        }
        if (event.getSource() == bookmarkButton) {
            // TODO actually add the recipe to bookmarks
            // logic here should be "if recipe is not in bookmarks, add to bookmarks"
            JOptionPane.showConfirmDialog(bookmarkButton, "Add this recipe to bookmarks?");
        }
    }
}
