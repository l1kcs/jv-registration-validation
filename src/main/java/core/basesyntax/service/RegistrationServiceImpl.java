package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user.getId() == null) {
            throw new IllegelDataException("Id cannot be null");
        }
        if (user.getLogin() == null) {
            throw new IllegelDataException("Login cannot be null");
        }
        if (user.getPassword() == null) {
            throw new IllegelDataException("Password cannot be null");
        }
        if (user.getAge() == null) {
            throw new IllegelDataException("Age cannot be null");
        }
        if (user.getLogin().length() < 6 || user.getPassword().length() < 6) {
            throw new IllegelDataException("Login & Password should be 6 digits and longer");
        }
        if (user.getAge() < 18) {
            throw new IllegelDataException("User's age is at least 18 y.o.");
        }
        return storageDao.add(user);
    }
}
