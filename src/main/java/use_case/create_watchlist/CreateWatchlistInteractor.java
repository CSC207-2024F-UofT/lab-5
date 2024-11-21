package use_case.create_watchlist;

public class CreateWatchlistInteractor {
    private CreateWatchlistDataAccessInterface userDataAccessObject;
    private CreateWatchlistOutputBoundary presenter;

    public CreateWatchlistInteractor(CreateWatchlistDataAccessInterface userDataAccessObject,
                                     CreateWatchlistOutputBoundary presenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.presenter = presenter;
    }

    @Override
    public void execute(CreateWatchlistInputData inputData) {
        userDataAccessObject.saveToWatchlists(inputData.getUsername(), inputData.getWatchlist());
        final CreateWatchlistOutputData outputData = new CreateWatchlistOutputData(inputData.getUsername(), false);
        presenter.prepareSuccessView(outputData);
    }
}
