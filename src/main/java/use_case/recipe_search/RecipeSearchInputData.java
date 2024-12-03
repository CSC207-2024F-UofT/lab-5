package use_case.recipe_search;

/**
 * The Input Data for the Search Use Case.
 */
public class RecipeSearchInputData {

    private final String recipeName;
    private final String calMin;
    private final String calMax;
    private final String carbMin;
    private final String carbMax;
    private final String proteinMin;
    private final String proteinMax;
    private final String fatMin;
    private final String fatMax;

    public RecipeSearchInputData(String recipeName, String calMin, String calMax, String carbMin, String carbMax, String proteinMin, String proteinMax, String fatMin, String fatMax) {
        this.recipeName = recipeName;
        this.calMin = calMin;
        this.calMax = calMax;
        this.carbMin = carbMin;
        this.carbMax = carbMax;
        this.proteinMin = proteinMin;
        this.proteinMax = proteinMax;
        this.fatMin = fatMin;
        this.fatMax = fatMax;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getCalMin() {
        return calMin;
    }

    public String getCalMax() {
        return calMax;
    }

    public String getCarbMin() {
        return carbMin;
    }

    public String getCarbMax() {
        return carbMax;
    }

    public String getProteinMin() {
        return proteinMin;
    }

    public String getProteinMax() {
        return proteinMax;
    }

    public String getFatMin() {
        return fatMin;
    }

    public String getFatMax() {
        return fatMax;
    }
}

