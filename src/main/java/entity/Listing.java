package entity;

/**
 * A listing of a book.
 */
public class Listing {

    private String listingID;
    private Book book;
    private String sellingPrice;
    private String seller;
    private boolean isAvailable;

    // Constructor, getters, and setters
    public Listing(String listingID, Book book, String sellingPrice, String seller, boolean isAvailable) {
        this.listingID = listingID;
        this.book = book;
        this.sellingPrice = sellingPrice;
        this.seller = seller;
        this.isAvailable = isAvailable;
    }

    // Getters and Setters
    public String getListingID() {
        return listingID;
    }

    public void setListingID(String listingID) {
        this.listingID = listingID;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setPrice(String price) {
        this.sellingPrice = price;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}