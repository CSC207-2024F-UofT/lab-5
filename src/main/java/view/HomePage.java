package view;

import data_access.SpoonacularRecipeDAO;
import interface_adapter.RecipeController;
import use_case.SearchRecipeUseCase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {
    public HomePage() {
        setTitle("Welcome to the Recipe Finder");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to the Recipe Finder!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(welcomeLabel, BorderLayout.CENTER);

        JButton startButton = new JButton("Start Searching Recipes");
        startButton.setFont(new Font("Arial", Font.PLAIN, 16));
        startButton.addActionListener(e -> {
            // Open RecipeView and close HomePage
            RecipeController controller = new RecipeController(new SearchRecipeUseCase(new SpoonacularRecipeDAO()));
            new RecipeView(controller);
            dispose();
        });

        add(startButton, BorderLayout.SOUTH);
        setVisible(true);
    }
}

