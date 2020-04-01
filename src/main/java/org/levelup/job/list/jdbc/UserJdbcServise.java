package org.levelup.job.list.jdbc;

import org.levelup.job.list.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class UserJdbcServise implements UserService {

    @Override
    public User createUser(String name, String lastName, String passport) throws SQLException {
        try (Connection connection= JdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("insert into users (name, last_name, passport) values (?, ?, ?)");
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setString(3, passport);
            int rowChanged = statement.executeUpdate();
            System.out.println("Колличество добавленных строк: " + rowChanged);

            Statement selectStatement = connection.createStatement();
            ResultSet resultSet = selectStatement.executeQuery("select * from users where passport = '" + passport + "'" ); //select

            resultSet.next();
            int id = resultSet.getInt(1);
            String userName = resultSet.getString("name");
            String userLastName = resultSet.getString("last_name");
            String userPassport = resultSet.getString("passport");

            System.out.println("User: ID = " + id +
                               ", name = " + userName +
                               ", last name = " + userLastName +
                               ", passport = " + passport );

            return new User(id, userName, userLastName, passport);
        }
    }

    @Override
    public Collection<User> findByPassport(String passport) throws SQLException {
        try(Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from users where passport = ?");
            statement.setString(1, passport);
            ResultSet resultSet = statement.executeQuery();
            return extractUsers(resultSet);
        }
    }

    @Override
    public Collection<User> findByNameAndLastName(String name, String lastName) throws SQLException {
        try(Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from users where  name = ? and last_name = ?");
            statement.setString(1, name);
            statement.setString(2, lastName);
            ResultSet resultSet = statement.executeQuery();
            return extractUsers(resultSet);
        }
    }

    @Override
    public Collection<User> findByLastName(String lastName) throws SQLException {
        try(Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from users where  last_name = ?");
            statement.setString(1, lastName);
            ResultSet resultSet = statement.executeQuery();
            return extractUsers(resultSet);
        }
    }

    @Override
    public void deleteUserByPassport(String passport) throws SQLException {
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("delete from userss where passport = ?");
            statement.setString(1, passport);
            int rowDeleted = statement.executeUpdate();
            System.out.println("Удалено позиций: " + rowDeleted);
        }

    }

    @Override
    public User updateUser(String passport, String name, String lastName) throws SQLException {
        try (Connection connection= JdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("update users set passport = ?, name = ?, last_name = ?");
            statement.setString(1, passport);
            statement.setString(2, name);
            statement.setString(3, lastName);

            int rowChanged = statement.executeUpdate();
            System.out.println("Колличество обновленных строк: " + rowChanged);

            Statement selectStatement = connection.createStatement();
            ResultSet resultSet = selectStatement.executeQuery("select * from users where passport = ?" ); //select

            resultSet.next();
            int id = resultSet.getInt(1);
            String userName = resultSet.getString("name");
            String userLastName = resultSet.getString("last_name");
            String userPassport = resultSet.getString("passport");

            System.out.println("User: ID = " + id +
                    ", name = " + userName +
                    ", last name = " + userLastName +
                    ", passport = " + passport );

            return new User(id, userName, userLastName, passport);
        }
    }

    private Collection<User> extractUsers(ResultSet resultSet) throws SQLException {
        Collection<User> users = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String userName = resultSet.getString("name");
            String userLastName = resultSet.getString("last_name");
            String userPassport = resultSet.getString("passport");
            users.add(new User(id, userName, userLastName, userPassport));
        }
        return users;
    }
}
