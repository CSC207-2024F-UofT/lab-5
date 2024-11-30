package osiris.use_case.plaid;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import osiris.data_access.PlaidDataAccessObject;
import osiris.data_access.PlaidDataAccessObject.ExchangeTokenResponse;
import osiris.data_access.PlaidDataAccessObject.LinkTokenResponse;
import osiris.entity.User;
import osiris.entity.UserFactory;
import osiris.utility.exceptions.PlaidException;
import osiris.utility.exceptions.PlaidUseCaseException;

/**
 * The Plaid Interactor implementing the PlaidInputBoundary.
 */
@Service
public class PlaidInteractor implements PlaidInputBoundary {

    private final UserPlaidDataAccessInterface plaidDao;
    private final PlaidDataBaseUserAccessObjectInterface userDataAccessObject;
    private final UserFactory userFactory;

    @Autowired
    public PlaidInteractor(UserPlaidDataAccessInterface plaidDao, PlaidDataBaseUserAccessObjectInterface userDataAccessObject, UserFactory userFactory) {
        this.plaidDao = plaidDao;
        this.userDataAccessObject = userDataAccessObject;
        this.userFactory = userFactory;
    }

    /**
     * Creates a Plaid Link Token.
     *
     * @param inputData The input data containing necessary parameters.
     * @return CreateLinkTokenOutputData containing the link token and request ID.
     * @throws PlaidUseCaseException If an error occurs while creating the link token.
     */
    @Override
    public CreateLinkTokenOutputData createLinkToken(CreateLinkTokenInputData inputData)
            throws PlaidUseCaseException {

        try {
            final LinkTokenResponse response = plaidDao.createLinkToken(
                    inputData.getClientName(),
                    inputData.getCountryCodes(),
                    inputData.getLanguage(),
                    inputData.getUserClientId(),
                    inputData.getProducts()
            );
            return new CreateLinkTokenOutputData(response.link_token, response.request_id);
        }
        catch (PlaidException ex) {
            throw new PlaidUseCaseException("Failed to create Link Token: " + ex.getMessage(), ex);
        }
        catch (IOException ex) {
            throw new PlaidUseCaseException("IO Error while creating Link Token.", ex);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Exchanges a Public Token for an Access Token.
     *
     * @param inputData The input data containing the public token.
     * @return ExchangePublicTokenOutputData containing the access token, item ID, and request ID.
     * @throws PlaidUseCaseException If an error occurs while exchanging the public token.
     */
    @Override
    public ExchangePublicTokenOutputData exchangePublicToken(ExchangePublicTokenInputData inputData)
            throws PlaidUseCaseException {

        try {
            final ExchangeTokenResponse response = plaidDao.exchangePublicToken(
                    inputData.getPublicToken()
            );
            User user = userFactory.create(inputData.getUsername(), inputData.getPassword(), response.access_token);
            userDataAccessObject.save(user);
            return new ExchangePublicTokenOutputData(
                    response.access_token,
                    response.item_id,
                    response.request_id
            );
        }
        catch (PlaidException ex) {
            throw new PlaidUseCaseException("Failed to exchange Public Token: " + ex.getMessage(), ex);
        }
        catch (IOException ex) {
            throw new PlaidUseCaseException("IO Error while exchanging Public Token.", ex);
        }
    }
}