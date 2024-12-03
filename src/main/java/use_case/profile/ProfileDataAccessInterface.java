package use_case.profile;

import entity.User;

public interface ProfileDataAccessInterface {

    User get(String username);
}
