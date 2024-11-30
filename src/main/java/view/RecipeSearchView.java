package view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import interface_adapter.recipe_search.RecipeSearchController;
import interface_adapter.recipe_search.RecipeSearchState;
import interface_adapter.recipe_search.RecipeSearchViewModel;
import interface_adapter.signup.SignupState;


/**
 * The View for when the user is searching for a recipe.
 */

public class RecipeSearchView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Recipe Search";
    private final RecipeSearchViewModel recipeSearchViewModel;
    private RecipeSearchController recipeSearchController;

    private final JTextField recipeInputField = new JTextField(15);
    private final JLabel recipeErrorField = new JLabel();

    private final JLabel calories = new JLabel("Calories Range:");
    private final JTextField caloriesMaxInputField = new JTextField(15);
    private final JTextField caloriesMinInputField = new JTextField(15);

    private final JLabel carbs = new JLabel("Carbohydrates Range:");
    private final JTextField carbsMaxInputField = new JTextField(15);
    private final JTextField carbsMinInputField = new JTextField(15);

    private final JLabel protein = new JLabel("Protein Range:");
    private final JTextField proteinMinInputField = new JTextField(15);
    private final JTextField proteinMaxInputField = new JTextField(15);

    private final JLabel fat = new JLabel("Fat Range:");
    private final JTextField fatMaxInputField = new JTextField(15);
    private final JTextField fatMinInputField = new JTextField(15);

    private final JButton search;
    private final JButton back;

    public RecipeSearchView(RecipeSearchViewModel recipeSearchViewModel) {
        this.recipeSearchViewModel = recipeSearchViewModel;
        this.recipeSearchViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel(viewName);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel recipeInfo = new LabelTextPanel(
                new JLabel("Recipe Name:"), recipeInputField);

        // panel for calorie information
        final JPanel calorieInfo = new JPanel();
        calorieInfo.add(calories);
        calorieInfo.add(caloriesMinInputField);
        calorieInfo.add(caloriesMaxInputField);

        // panel for carbohydrate information
        final JPanel carbInfo = new JPanel();
        carbInfo.add(carbs);
        carbInfo.add(carbsMinInputField);
        carbInfo.add(carbsMaxInputField);

        // panel for protein information
        final JPanel proteinInfo = new JPanel();
        proteinInfo.add(protein);
        proteinInfo.add(proteinMinInputField);
        proteinInfo.add(proteinMaxInputField);

        // panel for fat information
        final JPanel fatInfo = new JPanel();
        fatInfo.add(fat);
        fatInfo.add(fatMinInputField);
        fatInfo.add(fatMaxInputField);

        // panel for buttons
        final JPanel buttons = new JPanel();
        search = new JButton("Search");
        buttons.add(search);
        back = new JButton("Back");
        buttons.add(back);

        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        recipeSearchController.switchToProfileView();
                    }
                });

        search.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(search)) {
                            handleSearchAction();
                        }
                    }
                });

        addRecipeNameListener();
        addCalMinListener();
        addCalMaxListener();
        addCarbMinListener();
        addCarbMaxListener();
        addProteinMinListener();
        addProteinMaxListener();
        addFatMinListener();
        addFatMaxListener();

        this.add(title);
        this.add(recipeInfo);
        this.add(calorieInfo);
        this.add(carbInfo);
        this.add(proteinInfo);
        this.add(fatInfo);
        this.add(buttons);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private void handleSearchAction() {
        final RecipeSearchState currentState = recipeSearchViewModel.getState();
        executeSearch(currentState);
    }

    private void executeSearch(RecipeSearchState currentState) {
        recipeSearchController.execute(
                currentState.getRecipeName(),
                currentState.getCalMin(),
                currentState.getCalMax(),
                currentState.getCarbMin(),
                currentState.getCarbMax(),
                currentState.getProteinMin(),
                currentState.getProteinMax(),
                currentState.getFatMin(),
                currentState.getFatMax()
        );
    }

    private void switchToSearchResultsView() {
        recipeSearchController.switchToSearchResultsView();
    }

    private void addRecipeNameListener() {
        recipeInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final RecipeSearchState currentState = recipeSearchViewModel.getState();
                currentState.setRecipeName(recipeInputField.getText());
                recipeSearchViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void addCalMinListener() {
        caloriesMinInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final RecipeSearchState currentState = recipeSearchViewModel.getState();
                currentState.setCalMin(caloriesMinInputField.getText());
                recipeSearchViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void addCalMaxListener() {
        caloriesMaxInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final RecipeSearchState currentState = recipeSearchViewModel.getState();
                currentState.setCalMax(caloriesMaxInputField.getText());
                recipeSearchViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void addCarbMinListener() {
        carbsMinInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final RecipeSearchState currentState = recipeSearchViewModel.getState();
                currentState.setCarbMin(carbsMinInputField.getText());
                recipeSearchViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void addCarbMaxListener() {
        carbsMaxInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final RecipeSearchState currentState = recipeSearchViewModel.getState();
                currentState.setCarbMax(carbsMaxInputField.getText());
                recipeSearchViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void addProteinMinListener() {
        proteinMinInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final RecipeSearchState currentState = recipeSearchViewModel.getState();
                currentState.setProteinMin(proteinMinInputField.getText());
                recipeSearchViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void addProteinMaxListener() {
        proteinMaxInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final RecipeSearchState currentState = recipeSearchViewModel.getState();
                currentState.setProteinMax(proteinMaxInputField.getText());
                recipeSearchViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void addFatMinListener() {
        fatMinInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final RecipeSearchState currentState = recipeSearchViewModel.getState();
                currentState.setFatMin(fatMinInputField.getText());
                recipeSearchViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void addFatMaxListener() {
        fatMaxInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final RecipeSearchState currentState = recipeSearchViewModel.getState();
                currentState.setFatMax(fatMaxInputField.getText());
                recipeSearchViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    public String getViewName() {
        return viewName;
    }

    /**
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /**
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final RecipeSearchState state = (RecipeSearchState) evt.getNewValue();
        if (state.getErrorMessage() != null) {
            JOptionPane.showMessageDialog(this, state.getErrorMessage());
        }
    }

    public void setSearchController(RecipeSearchController searchController) {
        this.recipeSearchController = searchController;
    }

}
