package use_case.home_view;

/**
 * The Input Data for the Search Use Case.
 */
public class SearchInputData {

    private final String stockSymbol;

    public SearchInputData(String stockSymbol) {
        this.stockSymbol = stockSymbol.toUpperCase();
    }

    public String getStockSymbol() {
        return stockSymbol;
    }
}
