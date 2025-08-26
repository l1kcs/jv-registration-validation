package core.basesyntax.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegistrationServiceImplTest {
    private User user;
    private StorageDao storageDao;
    private RegistrationService registrationService;

    @BeforeEach
    void setUp() {
        storageDao = new StorageDaoImpl();
        registrationService = new RegistrationServiceImpl();
    }

    @Test
    void userInStorage_isOk() {
        user = new User.UserBuilder().setId(312313L)
                .setLogin("test222")
                .setPassword("qwerty")
                .setAge(23)
                .build();
        registrationService.register(user);
        assertNotNull(storageDao.get("test222"));
        assertEquals("test222",storageDao.get("test222").getLogin(),
                "login should match in DB and register");
    }

    @Test
    void userLowerSixDigitsLogin_notOk() {
        user = new User.UserBuilder().setId(312313L)
                .setLogin("test")
                .setPassword("qwerty")
                .setAge(23)
                .build();
        assertThrows(IllegelDataException.class,
                () -> registrationService.register(user));

    }

    @Test
    void userLowerSixDigitsPassword_notOk() {
        user = new User.UserBuilder().setId(312313L)
                .setLogin("test123")
                .setPassword("123")
                .setAge(23)
                .build();
        assertThrows(IllegelDataException.class,
                () -> registrationService.register(user));
    }

    @Test
    void userLowerEighteenAge_notOk() {
        user = new User.UserBuilder().setId(312313L)
                .setLogin("test123")
                .setPassword("123123")
                .setAge(17)
                .build();
        assertThrows(IllegelDataException.class,
                () -> registrationService.register(user));
    }

    @Test
    void userNullIdExpression_notOk() {
        user = new User.UserBuilder().setLogin("test123")
                .setPassword("123333")
                .setAge(18)
                .build();
        assertThrows(IllegelDataException.class,
                () -> registrationService.register(user));
    }

    @Test
    void userNullLoginExpression_notOk() {
        user = new User.UserBuilder().setId(312313L)
                .setPassword("123456")
                .setAge(18)
                .build();
        assertThrows(IllegelDataException.class,
                () -> registrationService.register(user));
    }

    @Test
    void userNullPasswordExpression_notOk() {
        user = new User.UserBuilder().setId(312313L)
                .setLogin("test123")
                .setAge(18)
                .build();
        assertThrows(IllegelDataException.class,
                () -> registrationService.register(user));
    }

    @Test
    void userNullAgeExpression_notOk() {
        user = new User.UserBuilder().setId(312313L)
                .setLogin("test123")
                .setPassword("123123")
                .build();
        assertThrows(IllegelDataException.class,
                () -> registrationService.register(user));
    }

    @Test
    void userNegativeAge_notOk() {
        user = new User.UserBuilder().setId(312313L)
                .setLogin("test123")
                .setPassword("123123")
                .setAge(-1)
                .build();
        assertThrows(IllegelDataException.class,
                () -> registrationService.register(user));
    }
}
