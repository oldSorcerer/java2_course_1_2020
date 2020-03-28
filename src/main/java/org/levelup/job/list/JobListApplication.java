package org.levelup.job.list;

import org.levelup.job.list.domain.Position;
import org.levelup.job.list.jdbc.JdbcJobListServise;

import java.sql.SQLException;
import java.util.Collection;

public class JobListApplication {
    public static void main(String[] args) throws SQLException {
        JdbcJobListServise servise = new JdbcJobListServise();

        for (int i = 0; i < 10; i++) {
            servise.createPosition("Devel" + i);
        }
        System.out.println();

        Collection<Position> allPositions = servise.findAll();
        for (Position position : allPositions) {
            System.out.println(position.getId() + " " + position.getName());
        }
        System.out.println();

        Collection<Position> likePositions = servise.findPositionWithNameLike("Devel%");
        for (Position position : likePositions) {
            System.out.println(position.getId() + " " + position.getName());
        }
    }
}
