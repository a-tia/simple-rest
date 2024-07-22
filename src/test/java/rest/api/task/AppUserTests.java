package rest.api.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rest.api.task.model.AppUser;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AppUserTest {

    private AppUser appUser;

    private static final long delta = 1;

    @BeforeEach
    void setUp() {
        appUser = new AppUser("testuser", "female", 38);
    }

    @Test
    void testOnCreate() throws Exception {
        // reflection is used to invoke the onCreate method
        Method onCreate = AppUser.class.getDeclaredMethod("onCreate");
        onCreate.setAccessible(true);

        onCreate.invoke(appUser);

        LocalDateTime actualAccountCreationTimestamp = appUser.getAccountCreationTimestamp();
        assertNotNull(actualAccountCreationTimestamp);
        assertTrue(actualAccountCreationTimestamp.isBefore(LocalDateTime.now().plusSeconds(delta)));
    }

    @Test
    void testUserNotNull() {
        // test whether a newly created user is not null
        assertNotNull(appUser);
    }
}