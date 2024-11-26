// Commented out for now - implement if tehre's time
//package view;
//
//import java.util.List;
//
//import entity.Recipe;
//import entity.User;
//import interface_adapter.BookmarkController;
//
//import javax.swing.*;
//// import interface_adapter.BookmarkController;
//
//// implements ActionListener removed for now
//public class FolderView extends RecipeListView {
//    String folderName;
//
//    public FolderView(User user) {
//        super(user);
//        setTitle(folderName);
//    }
//
//    @Override
//    protected List<Recipe> getRecipeList(User user1) {
//        return userDAO.getFolderFromFile(user1.getUsername(), folderName);
//    }
//}
