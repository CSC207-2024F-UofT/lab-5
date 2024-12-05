package interface_adapter.search;

import interface_adapter.ViewModel;

/**
 * View Model for the searched view.
 */
public class SearchedViewModel extends ViewModel<SearchedState> {

    public SearchedViewModel() {
        super("searched");
        setState(new SearchedState(new BookViewModel(), "", ""));
    }

}

