package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection connection = Util.open();
             Statement statement = connection.createStatement()) {
            boolean execute = statement.execute("CREATE TABLE IF NOT EXISTS people (id SERIAL PRIMARY KEY," +
                                                "name VARCHAR(255)," +
                                                "lastName VARCHAR(255)," +
                                                "age SMALLINT)");

            System.out.println(execute);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {

        try (Connection connection = Util.open();
             Statement statement = connection.createStatement()) {
            boolean execute = statement.execute("DROP TABLE IF EXISTS people");
            System.out.println(execute);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try (Connection connection = Util.open();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO people (name,lastname,age) VALUES (?,?,?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            System.out.println(statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {

        try (Connection connection = Util.open();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM people WHERE id=?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println(statement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = Util.open();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM people")) {
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.open();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE people");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
