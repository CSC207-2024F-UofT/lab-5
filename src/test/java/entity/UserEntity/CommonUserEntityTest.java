package entity.UserEntity;

import entity.CommonUser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the CommonUser class.
 */
class CommonUserEntityTest {

    @Test
    void testConstructorAndGetName() {
        CommonUser user = new CommonUser("Alice", "password123");
        assertEquals("Alice", user.getName(), "The name should be 'Alice'");
    }

    @Test
    void testConstructorAndGetPassword() {
        CommonUser user = new CommonUser("Alice", "password123");
        assertEquals("password123", user.getPassword(), "The password should be 'password123'");
    }
}
