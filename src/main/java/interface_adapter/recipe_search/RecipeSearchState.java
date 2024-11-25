package interface_adapter.recipe_search;

public class RecipeSearchState {

    private String recipeName = "";
    private String calMin = "";
    private String calMax = "";
    private String carbMin = "";
    private String carbMax = "";
    private String proteinMin = "";
    private String proteinMax = "";
    private String fatMin = "";
    private String fatMax = "";

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getCalMin() {
        return calMin;
    }

    public void setCalMin(String calMin) {
        this.calMin = calMin;
    }

    public String getCalMax() {
        return calMax;
    }

    public void setCalMax(String calMax) {
        this.calMax = calMax;
    }

    public String getCarbMin() {
        return carbMin;
    }

    public void setCarbMin(String carbMin) {
        this.carbMin = carbMin;
    }

    public String getCarbMax() {
        return carbMax;
    }

    public void setCarbMax(String carbMax) {
        this.carbMax = carbMax;
    }

    public String getProteinMin() {
        return proteinMin;
    }

    public void setProteinMin(String proteinMin) {
        this.proteinMin = proteinMin;
    }

    public String getProteinMax() {
        return proteinMax;
    }

    public void setProteinMax(String proteinMax) {
        this.proteinMax = proteinMax;
    }

    public String getfatMin() {
        return this.fatMin;
    }

    public void setFatMin(String fatMin) {
        this.fatMin = fatMin;
    }

    public String getfatMax() {
        return this.fatMax;
    }

    public void setFatMax(String fatMax) {
        this.fatMax = fatMax;
    }
}
