package nutrition_information_test;

import data_access.NutritionInformationDAO;
import entity.Nutrition;
import use_case.nutrition_information.*;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NutritionInformationInteractorTest {
    private static class MockNutritionInformationDAO implements NutritionInformationAccessInterface {
        @Override
        public String fetchNutritionInformation(int recipeId) {
            return "{ \"nutrients\": [" +
                    "{ \"name\": \"Protein\", \"amount\": 25.0, \"unit\": \"g\", \"percentOfDailyNeeds\": 50.0 }," +
                    "{ \"name\": \"Carbohydrates\", \"amount\": 50.0, \"unit\": \"g\", \"percentOfDailyNeeds\": 25.0 }" +
                    "] }";
        }
    }

    @Test
    void testNutritionInformationSuccess() {
        NutritionInformationAccessInterface mockAccess = new MockNutritionInformationDAO();

        NutritionInformationOutputBoundary successPresenter = new NutritionInformationOutputBoundary() {
            @Override
            public List<Nutrition> presentNutritionInformation(NutritionInformationOutputData outputData) {
                List<Nutrition> nutritionList = outputData.getNutritionList();

                assertNotNull(nutritionList, "Nutrition list should not be null");
                assertEquals(2, nutritionList.size(), "Expected 2 nutrition items");

                Nutrition protein = nutritionList.get(0);
                assertEquals("Protein", protein.getName());
                assertEquals(25.0, protein.getAmount());
                assertEquals("g", protein.getUnit());
                assertEquals(50.0, protein.getPercentDailyNeeds());

                Nutrition carbs = nutritionList.get(1);
                assertEquals("Carbohydrates", carbs.getName());
                assertEquals(50.0, carbs.getAmount());
                assertEquals("g", carbs.getUnit());
                assertEquals(25.0, carbs.getPercentDailyNeeds());

                return nutritionList;
            }
        };

        NutritionInformationInteractor interactor = new NutritionInformationInteractor(mockAccess, successPresenter);

        NutritionInformationInputData inputData = new NutritionInformationInputData(1);

        interactor.NutritionInformation(inputData);
    }

    @Test
    void testEmptyNutritionData() {
        NutritionInformationAccessInterface mockAccess = new NutritionInformationAccessInterface() {
            @Override
            public String fetchNutritionInformation(int recipeId) {
                return "{ \"nutrients\": [] }";
            }
        };

        NutritionInformationOutputBoundary successPresenter = new NutritionInformationOutputBoundary() {
            @Override
            public List<Nutrition> presentNutritionInformation(NutritionInformationOutputData outputData) {
                List<Nutrition> nutritionList = outputData.getNutritionList();
                assertNotNull(nutritionList, "Nutrition list should not be null");
                assertEquals(0, nutritionList.size(), "Expected 0 nutrition items");
                return nutritionList;
            }
        };

        NutritionInformationInteractor interactor = new NutritionInformationInteractor(mockAccess, successPresenter);

        NutritionInformationInputData inputData = new NutritionInformationInputData(2);

        interactor.NutritionInformation(inputData);
    }

    @Test
    void testInvalidJsonData() {
        NutritionInformationAccessInterface mockAccess = new NutritionInformationAccessInterface() {
            @Override
            public String fetchNutritionInformation(int recipeId) {
                return "{ \"invalid\": \"data\" }";
            }
        };

        NutritionInformationOutputBoundary successPresenter = new NutritionInformationOutputBoundary() {
            @Override
            public List<Nutrition> presentNutritionInformation(NutritionInformationOutputData outputData) {
                List<Nutrition> nutritionList = outputData.getNutritionList();
                assertNotNull(nutritionList, "Nutrition list should not be null");
                assertEquals(0, nutritionList.size(), "Expected 0 nutrition items due to invalid JSON data");
                return nutritionList;
            }
        };

        NutritionInformationInteractor interactor = new NutritionInformationInteractor(mockAccess, successPresenter);

        NutritionInformationInputData inputData = new NutritionInformationInputData(3);

        interactor.NutritionInformation(inputData);
    }
}
