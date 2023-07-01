package com.galkin.PP312.dao;

import com.galkin.PP312.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserDAOImp implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User").getResultList();
    }
    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }
    @Override
    public void addNewUser(User user) {
        entityManager.persist(user);
    }
    @Override
    public void updateUser(User updateUser, long id) {
        entityManager.merge(updateUser);
        entityManager.flush();

    }
    @Override
    public void deleteUserById(long id) {
        entityManager.remove(getUserById(id));
    }


}
