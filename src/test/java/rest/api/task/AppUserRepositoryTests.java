package rest.api.task;

import rest.api.task.repository.AppUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import rest.api.task.model.AppUser;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // 1) focus only on testing JPA components,
             // 2) the tests below run in a transaction because @Transactional is included

@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.datasource.driverClassName=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.jpa.show-sql=true"
})

class UserRepositoryTests {

    // In this class I only test the methods I have defined in myself in UserRepository: updateUserGender() and updateUserAge().

    @Autowired
    private AppUserRepository appUserRepository;

    @PersistenceContext
    private EntityManager entityManager;

    private AppUser appUser;

    @BeforeEach
    void setUp() {
        appUser = new AppUser();
        appUser.setUsername("TestUser");
        appUser.setGender("male");
        appUser.setAge(25);
        appUser = appUserRepository.save(appUser);
    }

    @Test
    void testUpdateUserGender() {
        appUserRepository.updateUserGender(appUser.getId(), "female");
        appUserRepository.flush();
        entityManager.clear();

        Optional<AppUser> updatedUser = appUserRepository.findById(appUser.getId());
        assertTrue(updatedUser.isPresent());
        assertEquals("female", updatedUser.get().getGender());
    }

    @Test
    void testUpdateUserAge() {
        appUserRepository.updateUserAge(appUser.getId(), 30);
        appUserRepository.flush();
        entityManager.clear();

        Optional<AppUser> updatedUser = appUserRepository.findById(appUser.getId());
        assertTrue(updatedUser.isPresent());
        assertEquals(30, updatedUser.get().getAge());
    }
}