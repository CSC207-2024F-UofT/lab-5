package view;

import entity.Recipe;
import entity.User;
import interface_adapter.RecipeController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;

public class RecipeView extends JFrame {
    private JTextField ingredientInput;
    private JButton searchButton;
    // private JList<String> recipeList; - commented out for now to make the double click function work
    private JList <Recipe> recipeList;
    private RecipeController controller;
    private User user;

    public RecipeView(RecipeController controller, User user) {
        this.controller = controller;
        this.user = user;
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

                // I commented this out for now and instantiated the list of recipes differently to make it clickable;
                // feel free to change it as needed
//                String[] recipeNames = recipes.stream()
//                        .map(recipe -> recipe.getName() + " - " + recipe.getUrl())
//                        .toArray(String[]::new);
//                recipeList.setListData(recipeNames);

                final DefaultListModel<Recipe> listModel = new DefaultListModel<>();
                for (Recipe recipe : recipes) {
                    listModel.addElement(recipe);
                }
                recipeList.setModel(listModel);

                // Make the recipe list clickable
                recipeList.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent event) {
                        if (event.getClickCount() == 2) {
                            // Get index of clicked item
                            final int index = recipeList.locationToIndex(event.getPoint());
                            // Ensure valid index
                            if (index >= 0) {
                                // Get selected item
                                final Recipe selectedItem = recipeList.getModel().getElementAt(index);
                                // Open a new window

                                new IndividualRecipeView(selectedItem, user);
                            }
                        }
                    }
                });
            }
        });

        final JPanel panel = new JPanel();
        panel.add(new JLabel("Enter ingredients (comma-separated):"));
        panel.add(ingredientInput);
        panel.add(searchButton);
        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(recipeList), BorderLayout.CENTER);

        setVisible(true);
    }
}
