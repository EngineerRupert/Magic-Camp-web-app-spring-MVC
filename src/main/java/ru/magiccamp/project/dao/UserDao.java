package ru.magiccamp.project.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.magiccamp.project.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Repository
public class UserDao {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public User createUser(String login, String password) {
        User user = new User(login, password);
        entityManager.persist(user);
        return user;
    }

    public User findByLoginOrId(String login) {
        return queryForSearchUser(login);
    }

    public User findByLoginOrId(int id) {
        return queryForSearchUser(id);
    }

    // один метод для поиска пользователя по id или login, который мы применяем в перегруженных методах
    private <T> User queryForSearchUser(T searchParameter) {
        String parameterForQuery = "";
        if (searchParameter instanceof String) {
            parameterForQuery = "login";
        } else if (searchParameter instanceof Integer){
            parameterForQuery = "id";
        }
        try {
            return entityManager.createQuery("select u from User u where u." + parameterForQuery + " = :parameter_for_search", User.class)
                    .setParameter("parameter_for_search", searchParameter)
                    .getSingleResult();
        } catch (NoResultException notFound) {
            return null;
        }
    }

    @Transactional
    public void updateUserInfo(User user) {
        entityManager.persist(user);
    }
}
