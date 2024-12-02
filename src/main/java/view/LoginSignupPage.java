package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import data_access.UserDAO;
import data_access.UserDAOImpl;
import entity.User;

public class LoginSignupPage extends JFrame {
    private final UserDAO userDao;

    // UI components
    private final JTextField usernameField;
    private final JPasswordField passwordField;

    public LoginSignupPage() {
        // Initialize with JSON-backed DAO
        userDao = new UserDAOImpl();
        setTitle("Login or Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        // Username field
        final JPanel usernamePanel = new JPanel();
        usernamePanel.add(new JLabel("Username:"));
        usernameField = new JTextField(15);
        usernamePanel.add(usernameField);
        add(usernamePanel);

        // Password field
        final JPanel passwordPanel = new JPanel();
        passwordPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(15);
        passwordPanel.add(passwordField);
        add(passwordPanel);

        // Login button
        final JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        // Signup button
        final JButton signupButton = new JButton("Sign Up");
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSignup();
            }
        });

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);
        add(buttonPanel);

        // Display the frame
        pack();
        setVisible(true);
    }

    private void handleLogin() {
        final String username = usernameField.getText();
        final String password = new String(passwordField.getPassword());

        if (userDao.validateUser(username, password)) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            openHomePage(userDao.findUserByUsername(username));
            dispose();
        }
        else {
            JOptionPane.showMessageDialog(this, "Invalid credentials. Please try again.");
        }
    }

    private void handleSignup() {
        final String username = usernameField.getText();
        final String password = new String(passwordField.getPassword());

        // Check for empty username or password
        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username and password cannot be empty.");
        }

        if (userDao.findUserByUsername(username) != null) {
            JOptionPane.showMessageDialog(this, "Username already exists. Please choose another.");
        }
        else {
            final boolean success = userDao.addUser(new User(username, password));
            if (success) {
                JOptionPane.showMessageDialog(this, "Signup successful! You can now log in.");
            }
            else {
                JOptionPane.showMessageDialog(this, "Signup failed. Please try again.");
            }
        }
    }

    private void openHomePage(User user) {
        // Open the HomePage
        new HomePage(user);
        // Close the LoginSignupPage
        dispose();
    }
}
