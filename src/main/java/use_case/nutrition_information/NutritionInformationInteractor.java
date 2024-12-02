package use_case.nutrition_information;

import entity.Nutrition;

import data_access.NutritionInformationDAO;
import interface_adapter.nutrition_information.NutritionInformationPresenter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NutritionInformationInteractor implements NutritionInformationInputBoundary {
    private final NutritionInformationDAO dataFetcher;
    private final NutritionInformationPresenter outputPresenter;

    public NutritionInformationInteractor(NutritionInformationDAO dataFetcher
            ,NutritionInformationPresenter outputPresenter) {
        this.dataFetcher = dataFetcher;
        this.outputPresenter = outputPresenter;
    }

    @Override
    public List<Nutrition> NutritionInformation(NutritionInformationInputData inputData) {
        // Step 1: 从数据访问层获取原始 JSON 数据
        String jsonData = dataFetcher.fetchNutritionInformation(inputData.getRecipeId());

        // Step 2: 解析 JSON 数据
        List<Nutrition> nutritionList = parseNutritionData(jsonData);

        // Step 3: 封装结果为 OutputData
        NutritionInformationOutputData outputData = new NutritionInformationOutputData(nutritionList);

        // Step 4: 调用 OutputBoundary 返回结果
        return outputPresenter.presentNutritionInformation(outputData);
    }

    private List<Nutrition> parseNutritionData(String jsonData) {
        List<Nutrition> nutritionList = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(jsonData);
        if (jsonObject.has("nutrients")) {
            JSONArray nutrients = jsonObject.getJSONArray("nutrients");
            for (int i = 0; i < nutrients.length(); i++) {
                JSONObject nutrientJson = nutrients.getJSONObject(i);

                String name = nutrientJson.getString("name");
                double amount = nutrientJson.getDouble("amount");
                String unit = nutrientJson.getString("unit");
                double percentOfDailyNeeds = nutrientJson.optDouble("percentOfDailyNeeds", 0);

                Nutrition nutrition = new Nutrition(name, amount, unit, percentOfDailyNeeds);
                nutritionList.add(nutrition);
            }
        }
        return nutritionList;
    }
}


