package data_access;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetRecipeId {

    private static final String API_KEY = "5d06ae7be18746aaa4d1f1e5877f4450"; // 替换为您的 API 密钥
    private static final String BASE_URL = "https://api.spoonacular.com/recipes/complexSearch";

    public static int getRecipeIdByName(String recipeName) {
        try {
            // 构建 URL
            String apiUrl = BASE_URL + "?query=" + recipeName.replace(" ", "+") + "&apiKey=" + API_KEY;

            // 创建 HTTP 请求
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // 检查响应码
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                System.out.println("Error: API returned response code " + responseCode);
                return -1;
            }

            // 读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // 解析 JSON 响应
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray results = jsonResponse.getJSONArray("results");

            // 检查是否有结果
            if (results.length() == 0) {
                System.out.println("No recipes found for: " + recipeName);
                return -1;
            }

            // 提取第一个结果的 ID
            JSONObject firstResult = results.getJSONObject(0);
            int recipeId = firstResult.getInt("id");
            System.out.println("Recipe ID for " + recipeName + ": " + recipeId);

            return recipeId;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
