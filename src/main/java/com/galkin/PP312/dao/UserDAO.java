package com.galkin.PP312.dao;

import com.galkin.PP312.models.User;

import java.util.List;

public interface UserDAO {

    List<User> getAllUsers();

    User getUserById(long id);

    void addNewUser(User user);

    void updateUser(User user, long id);

    void deleteUserById(long id);

}
