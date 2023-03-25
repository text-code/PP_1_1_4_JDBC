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
        final String createUserTable =
                "CREATE TABLE IF NOT EXISTS users" +
                        " (id INT NOT NULL AUTO_INCREMENT," +
                        " name VARCHAR(45)," +
                        " last_name VARCHAR(45)," +
                        " age INT," +
                        " PRIMARY KEY (id));";

        try (Connection connection = new Util().getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(createUserTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        final String dropUserTable = "DROP TABLE IF EXISTS users";

        try (Connection connection = new Util().getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(dropUserTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        final String insertInUserTable = "INSERT into users(name, last_name, age) VALUES(?, ?, ?)";

        try (Connection connection = new Util().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertInUserTable)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);

            preparedStatement.executeUpdate();
            System.out.printf("User с именем – %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        final String deleteById = "DELETE FROM users WHERE id = ?";

        try (Connection connection = new Util().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteById)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        final String getAllUsers = "SELECT id, name, last_name, age FROM users";
        List<User> users = new ArrayList<>();

        try (Connection connection = new Util().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getAllUsers)) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge(resultSet.getByte("age"));

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        final String deleteAll = "DELETE FROM users";

        try (Connection connection = new Util().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteAll)) {

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
