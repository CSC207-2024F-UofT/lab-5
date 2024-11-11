package view;

import entity.Recipe;
import entity.User;
// import interface_adapter.BookmarkController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class BookmarkView extends JFrame {
    // should the search attributes be defined here?
    private JTextField searchField;
    private JButton searchButton;

    // attributes for default view
    private JList<String> bookmarkList;
    // private BookmarkController controller;
    private User user;

    // button to view individual recipes
    private JButton viewRecipeButton;
    // TODO modify as needed after User entity is added

    /*
    Generates the default view of a user's bookmarked recipes.
     */
    public BookmarkView(User user) {
        // TODO getBookmarks() method
        this.bookmarkList = new JList<>();

        // TODO getname() method
        setTitle(user.getUsername() + "'s Bookmarks");
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new JScrollPane(bookmarkList), BorderLayout.CENTER);

        // TODO view recipe button for each item in the list - in progress
        /*
        bookmarkList.add("view recipe", viewRecipeButton);
        // or do JButton b = new JButton("view recipe")?
        viewRecipeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new IndividualRecipeView;
                // close the current frame - TODO add a back button?
                //  Or just don't dispose so you can access another recipe at the same time?
                dispose();
            }
        });
         */

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
}
