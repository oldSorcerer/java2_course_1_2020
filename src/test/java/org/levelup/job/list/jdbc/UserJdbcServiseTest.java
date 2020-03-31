package org.levelup.job.list.jdbc;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserJdbcServiseTest {

    @Test
    void createUser() throws SQLException {
        UserJdbcServise servise = new UserJdbcServise();

        for (int i = 0; i < 10; i++) {
            servise.createUser("Ivatn" + i, "Ivanotv " + i + 25, "00 000 458 " + i + 50);
        }
        System.out.println();

    }

    @Test
    void findByPassport() {
    }

    @Test
    void findByNameAndLastName() {
    }

    @Test
    void findByLastName() {
    }

    @Test
    void deleteUserByPassport() {
    }

    @Test
    void updateUser() {
    }
}