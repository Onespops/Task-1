package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
//        userService.dropUsersTable();
        userService.createUsersTable();

        userService.saveUser("Pasha", "Romanov", (byte) 23);
        userService.saveUser("Pash", "Roman", (byte) 24);
        userService.saveUser("Pas", "Rom", (byte) 25);
        userService.saveUser("Pa", "Ro", (byte) 26);

//        userService.removeUserById(3);
    }
}
