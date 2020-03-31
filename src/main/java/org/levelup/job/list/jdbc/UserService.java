package org.levelup.job.list.jdbc;

import org.levelup.job.list.domain.User;

import java.sql.SQLException;
import java.util.Collection;

public interface UserService {

    User createUser(String name, String lastName, String passport) throws SQLException;
    Collection<User> findByPassport(String passport) throws SQLException;
    Collection<User> findByNameAndLastName(String name, String lastName) throws SQLException;
    Collection<User> findByLastName(String lastName) throws SQLException;
    void deleteUserByPassport(String passport) throws SQLException;
    User updateUser(String passport, String name, String lastName) throws SQLException;
}
