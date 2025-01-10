package ru.kata311.SpringBoot.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata311.SpringBoot.model.User;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        logger.debug("Fetching all users");
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        logger.info("Adding new user: {}", user);
        entityManager.persist(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        logger.debug("Finding user by id: {}", id);
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        logger.info("Deleting user with id: {}", id);
        User user = findById(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    @Transactional
    public void editUser(User user) {
        logger.info("Editing user: {}", user);
        entityManager.merge(user);
    }
}