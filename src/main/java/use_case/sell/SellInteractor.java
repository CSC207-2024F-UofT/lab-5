package use_case.sell;

import entity.Book;
import entity.BookFactory;
import entity.Listing;

/**
 * The Sell Interactor.
 */
public class SellInteractor implements SellInputBoundary {
    private final SellUserDataAccessInterface userDataAccessObject;
    private final SellListingDataAccessInterface listingDataAccessObject;
    private final SellBookDataFetcher sellBookDataFetcher;
    private final SellOutputBoundary userPresenter;
    private final BookFactory bookFactory;

    public SellInteractor(SellUserDataAccessInterface sellUserDataAccessInterface, SellListingDataAccessInterface listingDataAccessObject,
                          SellOutputBoundary sellOutputBoundary,
                          SellBookDataFetcher sellBookDataFetcher,
                          BookFactory bookFactory) {
        this.userDataAccessObject = sellUserDataAccessInterface;
        this.listingDataAccessObject = listingDataAccessObject;
        this.userPresenter = sellOutputBoundary;
        this.sellBookDataFetcher = sellBookDataFetcher;
        this.bookFactory = bookFactory;
    }

    @Override
    public void execute(SellInputData sellInputData) {
        final String bookID = sellInputData.getBookID();
        final Book book = bookFactory.create(bookID);
        final String price = sellInputData.getPrice();
        final String seller = sellInputData.getUsername();

        // check to see if this seller already listed this book
        if (listingDataAccessObject.exists(bookID, seller)) {
            userPresenter.prepareFailView(seller + " has already listed " + book.getTitle() + " for sale!");
        }
        else {
            final Listing listing = new Listing(bookID, book, price, seller, true);
            listingDataAccessObject.save(listing);

            final SellOutputData sellOutputData = new SellOutputData(sellInputData.getUsername(), listing, false);
            userPresenter.prepareSuccessView(sellOutputData);
        }
    }

    @Override
    public String getBookPrice(String BookID) {
        return sellBookDataFetcher.getBookPrice(BookID);
    }
}
