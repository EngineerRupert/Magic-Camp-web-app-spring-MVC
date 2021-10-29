package ru.magiccamp.project.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.magiccamp.project.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class UserDaoTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private UserDao userDao;

    @BeforeEach
    void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
        userDao = new UserDao(entityManager);
    }

    @AfterEach
    void tearDown() {
        if (entityManager != null) {
            entityManager.close();
        }
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @Test
    void createUser() {
        User user = userDao.createUser("userLogin", "userPassword");

        Assertions.assertNotNull(user);
        Assertions.assertEquals("userLogin", user.getLogin());
        Assertions.assertEquals("userPassword", user.getPassword());
    }

    @Test
    void findByLoginOrId() {
        User user = userDao.createUser("userLogin", "userPassword");
        User userResult1 = userDao.findByLoginOrId(1);
        User userResult2 = userDao.findByLoginOrId("userLogin");

        Assertions.assertEquals(user, userResult1);
        Assertions.assertEquals(user, userResult2);
        Assertions.assertEquals(userResult1, userResult2);

        Assertions.assertEquals(user.getId(), userResult1.getId());
        Assertions.assertEquals(user.getLogin(), userResult1.getLogin());

        Assertions.assertEquals(user.getId(), userResult2.getId());
        Assertions.assertEquals(user.getLogin(), userResult2.getLogin());

        Assertions.assertEquals(userResult1.getId(), userResult2.getId());
        Assertions.assertEquals(userResult1.getLogin(), userResult2.getLogin());
    }

}