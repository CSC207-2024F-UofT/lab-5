package view;

import entity.Recipe;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RecipeListView extends JFrame {
    // attributes for default view
    private JList<Recipe> recipeList;
//    private DefaultListModel<Recipe> listModel;
    private User user;

    // TODO attributes for other functionalities...
    private JTextField searchField;
    private JButton searchButton;
    // private BookmarkController controller;
    // button to view individual recipes - might remove
    private JButton viewRecipeButton;

    /*
    Generates the default view of a list of recipes associated with a User.
     */
    public RecipeListView(User user) {
        this.user = user;
        this.recipeList = new JList<>();
//        this.listModel = new DefaultListModel<>();
//        this.recipeList.setModel(listModel);

        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new JScrollPane(recipeList), BorderLayout.CENTER);

        // TODO view individual recipes - to be confirmed
//        bookmarkList.add("view recipe", viewRecipeButton);
//        // or do JButton b = new JButton("view recipe")?
//        viewRecipeButton.addActionListener(this);
        recipeList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    // Get index of clicked item
                    int index = recipeList.locationToIndex(event.getPoint());
                    // Ensure valid index
                    if (index >= 0) {
                        // Get selected item
                        Recipe selectedItem = recipeList.getModel().getElementAt(index);
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
}
