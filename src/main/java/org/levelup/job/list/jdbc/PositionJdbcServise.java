package org.levelup.job.list.jdbc;

import org.levelup.job.list.domain.Position;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class PositionJdbcServise implements PositionService {

    @Override
    public Position createPosition(String name) throws SQLException {
        Connection connection = JdbcUtils.getConnection();

        PreparedStatement statement = connection.prepareStatement("insert into positions (name) values (?)");
        statement.setString(1, name);
        int rowChanged = statement.executeUpdate(); // insert/update/delete

        System.out.println("Колличество добавленных строк: " + rowChanged);

        Statement selectStatement = connection.createStatement();
        ResultSet resultSet = selectStatement.executeQuery("select * from positions where name = '" + name + "'"); //select

        resultSet.next();
        int id = resultSet.getInt(1);
        String positionName = resultSet.getString("name");
        System.out.println("Должность: ID = " + id + ", name = " + positionName);

        return new Position(id, positionName);
    }

    @Override
    public void deletePositionById(int id) throws SQLException {
        try(Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("delete from positions where id = ?");
            statement.setInt(1, id);
            int rowDeleted = statement.executeUpdate();
            System.out.println("Удалено позиций: " + rowDeleted);
        }
    }

    @Override
    public void deletePositionByName(String name) throws SQLException {
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("delete from positions where name = ?");
            statement.setString(1, name);
            int rowDeleted = statement.executeUpdate();
            System.out.println("Удалено позиций " + rowDeleted);
        }
    }

    @Override
    public Collection<Position> findAllPositionWhichNameLike(String name) throws SQLException {
        try (Connection connection = JdbcUtils.getConnection()) {
            Statement selectStatement = connection.createStatement();
            ResultSet resultSet = selectStatement.executeQuery("select * from positions where name like '" + name + "'");
            return extractPositions(resultSet);
        }
    }

    @Override
    public Collection<Position> findPositionById(int id) throws SQLException {
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from positions where id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            return extractPositions(resultSet);
        }
    }

    @Override
    public Collection<Position> findAllPositions() throws SQLException {
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from positions");
            ResultSet resultSet = statement.executeQuery();
            return extractPositions(resultSet);
        }
    }

    @Override
    public Collection<Position> findPositionByName(String name) throws SQLException {
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from positions where name = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            return extractPositions(resultSet);
        }
    }

    private Collection<Position> extractPositions(ResultSet resultSet) throws SQLException {
        Collection<Position> positions = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            positions.add(new Position(id, name));
        }
        return positions;
    }
}
