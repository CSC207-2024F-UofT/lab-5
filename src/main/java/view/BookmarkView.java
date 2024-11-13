package view;

import entity.Recipe;
import entity.User;
// import interface_adapter.BookmarkController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// implements ActionListener removed for now
public class BookmarkView extends JFrame {
    // should the search attributes be defined here?
    private JTextField searchField;
    private JButton searchButton;

    // attributes for default view
    private JList<Recipe> bookmarkList;
    // private BookmarkController controller;
    private User user;

    // button to view individual recipes
    private JButton viewRecipeButton;
    // TODO modify as needed after User entity is added
    private DefaultListModel<Recipe> listModel;

    /*
    Generates the default view of a user's bookmarked recipes.
     */
    public BookmarkView(User user) {
        // TODO getBookmarks() method
        this.bookmarkList = new JList<Recipe>();
        this.listModel = new DefaultListModel<Recipe>();
        for (int i = 0; i < user.getBookmarks().size(); i++) {
            listModel.addElement(user.getBookmarks().get(i));
        }
        this.bookmarkList.setModel(listModel);

        setTitle(user.getUsername() + "'s Bookmarks");
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new JScrollPane(bookmarkList), BorderLayout.CENTER);

        // TODO view individual recipes function - in progress
//        bookmarkList.add("view recipe", viewRecipeButton);
//        // or do JButton b = new JButton("view recipe")?
//        viewRecipeButton.addActionListener(this);

        bookmarkList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    int index = bookmarkList.locationToIndex(event.getPoint()); // Get index of clicked item
                    if (index >= 0) { // Ensure valid index
                        Recipe selectedItem = bookmarkList.getModel().getElementAt(index); // Get selected item
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
