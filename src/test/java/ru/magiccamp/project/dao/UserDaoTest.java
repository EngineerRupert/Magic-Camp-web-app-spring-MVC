package ru.magiccamp.project.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import ru.magiccamp.project.model.User;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserDaoTest {

    private User user;

    @Autowired
    private UserDao userDao;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        user = userDao.createUser("userLogin", "userPassword");
    }

    @Test
    void createUser() {
        assertNotNull(user);
        assertEquals(user, entityManager.find(User.class, user.getId()));
    }


    @Test
    void findByLoginOrId() {
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