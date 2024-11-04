package view;

import interface_adapter.RecipeController;
import entity.Recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class RecipeView extends JFrame {
    private JTextField ingredientInput;
    private JButton searchButton;
    private JList<String> recipeList;
    private RecipeController controller;

    public RecipeView(RecipeController controller) {
        this.controller = controller;
        setTitle("Recipe Generator");
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ingredientInput = new JTextField(20);
        searchButton = new JButton("Find Recipes");
        recipeList = new JList<>();

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ingredientsText = ingredientInput.getText();
                List<String> ingredients = Arrays.asList(ingredientsText.split(","));
                List<Recipe> recipes = controller.getRecipes(ingredients);

                String[] recipeNames = recipes.stream()
                        .map(recipe -> recipe.getName() + " - " + recipe.getUrl())
                        .toArray(String[]::new);
                recipeList.setListData(recipeNames);
            }
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Enter ingredients (comma-separated):"));
        panel.add(ingredientInput);
        panel.add(searchButton);
        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(recipeList), BorderLayout.CENTER);

        setVisible(true);
    }
}
