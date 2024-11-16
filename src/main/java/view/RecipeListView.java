package view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;

import entity.Recipe;
import entity.User;

public abstract class RecipeListView extends JFrame {
    // attributes for default view
    private final JList<Recipe> recipeList;
    private final DefaultListModel<Recipe> listModel;

    // TODO attributes for other functionalities...
    private JTextField searchField;
    private JButton searchButton;
    // private BookmarkController controller;

    /*
    Generates the default view of a list of recipes associated with a User.
     */
    public RecipeListView(User user) {
        this.recipeList = new JList<>();
        this.listModel = new DefaultListModel<>();

        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final List<Recipe> recipes = getRecipeList(user);
        for (Recipe recipe : recipes) {
            this.listModel.addElement(recipe);
        }

        this.recipeList.setModel(listModel);

        // Wrap the JList in a JScrollPane
        final JScrollPane scrollPane = new JScrollPane(recipeList);
        scrollPane.setPreferredSize(recipeList.getPreferredScrollableViewportSize());
        add(scrollPane);

        // TODO fix the layout
//        // Main panel with vertical BoxLayout
//        final JPanel mainPanel = new JPanel();
//        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//        setContentPane(mainPanel);
//
//        // Add components to the main panel
//        mainPanel.add(scrollPane);

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
                        new IndividualRecipeView(selectedItem);
                    }
                }
            }
        });

        // TODO Search function - in progress
//        ingredientInput = new JTextField(20);
//        searchButton = new JButton("Find Recipes by Ingredients");
//        recipeListByIngredient = new JList<>();
//
//        nameInput = new JTextField(20);
//        searchButton = new JButton("Find Recipes by Name");
//        recipeListByName = new JList<>();
//
//        searchButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String ingredientsText = ingredientInput.getText();
//                java.util.List<String> ingredients = Arrays.asList(ingredientsText.split(","));
//                List<Recipe> recipes = controller.getRecipes(ingredients);
//
//                String[] recipeNames = recipes.stream()
//                        .map(recipe -> recipe.getName() + " - " + recipe.getUrl())
//                        .toArray(String[]::new);
//                recipeList.setListData(recipeNames);
//            }
//        });

        // TODO Display search bar
        /*
        final JPanel panel = new JPanel();
        panel.add(new JLabel("Enter ingredients (comma-separated):"));
        panel.add(ingredientInput);
        panel.add(searchButton);
        add(panel, BorderLayout.NORTH);

        // Button to view individual recipes
        add(viewRecipeButton, BorderLayout.SOUTH);
        */

        setVisible(true);
    }
    // might not need this or the view recipe button altogether
//    public void actionPerformed(ActionEvent event) {
//        new IndividualRecipeView(recipe, nutrition);
//    }

    protected abstract List<Recipe> getRecipeList(User user1);
}
