package view;

import interface_adapter.chat_expert.ChatExpertController;
import interface_adapter.chat_expert.ChatExpertViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

/**
 * Unified view for selecting an expert and chatting.
 * Combines the expert list, chat area, and message input.
 */
public class ExpertChatView extends JPanel {

    private final ChatExpertController controller;
    private final ChatExpertViewModel viewModel;

    private final JLabel headerNameLabel;
    private final JLabel headerAvatarLabel;
    private final JTextArea chatArea;
    private final JTextField messageInput;

    private JButton selectedButton;

    /**
     * Constructs an ExpertChatView object.
     *
     * @param controller The controller to handle user actions.
     * @param viewModel  The view model containing chat data.
     * @param experts    The list of experts to display.
     */
    public ExpertChatView(ChatExpertController controller,
                          ChatExpertViewModel viewModel,
                          List<String[]> experts) {
        this.controller = controller;
        this.viewModel = viewModel;

        setLayout(new BorderLayout());

        // Header Panel with Hamburger Menu and Logo
        final JPanel headerPanel = new JPanel(new BorderLayout());
        final JButton hamburgerMenu = createHamburgerMenu();
        final JLabel logo = new JLabel("Pitch!t", SwingConstants.CENTER);
        logo.setFont(new Font("Arial", Font.BOLD, 24));
        headerAvatarLabel = new JLabel(); // Placeholder for expert avatar
        headerNameLabel = new JLabel("Select an Expert", SwingConstants.LEFT);
        headerNameLabel.setFont(new Font("Arial", Font.BOLD, 18));

        final JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoPanel.add(hamburgerMenu);
        logoPanel.add(logo);

        headerPanel.add(logoPanel, BorderLayout.WEST);
        headerPanel.add(headerAvatarLabel, BorderLayout.CENTER);
        headerPanel.add(headerNameLabel, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        // Left Panel: Expert List
        final JPanel expertListPanel = new JPanel();
        expertListPanel.setLayout(new BoxLayout(expertListPanel, BoxLayout.Y_AXIS));
        for (String[] expert : experts) {
            final String expertId = expert[0];
            final String name = expert[1];
            final String description = expert[2];

            final JPanel expertPanel = new JPanel(new BorderLayout());
            final JLabel nameLabel = new JLabel(name);
            final JButton infoButton = new JButton("Info");
            infoButton.addActionListener(event -> JOptionPane.showMessageDialog(
                    this, description, name + " Info", JOptionPane.INFORMATION_MESSAGE));

            final JButton selectButton = new JButton("Select");
            selectButton.addActionListener(event -> {
                headerNameLabel.setText(name);
                headerAvatarLabel.setIcon(loadAvatar(expert[3]));
                controller.startChat(expertId, "Hi! I'd like to discuss my idea.");
                updateChatArea();
            });

            expertPanel.add(nameLabel, BorderLayout.WEST);
            expertPanel.add(infoButton, BorderLayout.CENTER);
            expertPanel.add(selectButton, BorderLayout.EAST);
            expertListPanel.add(expertPanel);
        }

        final JScrollPane expertScrollPane = new JScrollPane(expertListPanel);
        add(expertScrollPane, BorderLayout.WEST);

        // Main Chat Area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        // Footer Panel: Chat Bar
        final JPanel footerPanel = new JPanel(new BorderLayout());
        messageInput = new JTextField();
        final JButton sendButton = new JButton("Send");

        sendButton.addActionListener(e -> {
            final String userMessage = messageInput.getText().trim();
            if (!userMessage.isEmpty()) {
                controller.startChat(headerNameLabel.getText(), userMessage);
                updateChatArea();
                messageInput.setText("");
            }
        });

        footerPanel.add(messageInput, BorderLayout.CENTER);
        footerPanel.add(sendButton, BorderLayout.EAST);
        add(footerPanel, BorderLayout.SOUTH);
    }

    /**
     * Loads an avatar image from the resources folder.
     *
     * @param avatarFileName The filename of the avatar image.
     * @return An ImageIcon representing the avatar, or null if not found.
     */
    private ImageIcon loadAvatar(String avatarFileName) {
        final String path = "main/resources/avatar_experts/" + avatarFileName;
        final java.net.URL resource = getClass().getResource(path);

        if (resource != null) {
            return new ImageIcon(resource);
        }
        else {
            System.err.println("Avatar not found: " + path);
            return new ImageIcon("src/main/resources/images/avatars/default.png"); // Use a default placeholder
        }
    }
    /**
     * Creates a hamburger menu button.
     *
     * @return The hamburger menu button.
     */

    private JButton createHamburgerMenu() {
        final JButton hamburgerMenu = new JButton("☰");
        hamburgerMenu.setFont(new Font("Arial", Font.PLAIN, 18));
        hamburgerMenu.addActionListener(event -> {
            // Invoke the pre-implemented hamburger menu functionality
            JOptionPane.showMessageDialog(this, "Hamburger Menu Clicked");
        });
        return hamburgerMenu;
    }

    /**
     * Updates the chat area with the current chat history.
     */
    private void updateChatArea() {
        final StringBuilder chatContent = new StringBuilder();
        for (String message : viewModel.getChatHistory()) {
            chatContent.append(message).append("\n");
        }
        chatArea.setText(chatContent.toString());
    }
}
