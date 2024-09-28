package com.github.tm_425006.webproject.model;

import com.github.tm_425006.webproject.exception.CVGeneratorException;
import com.github.tm_425006.webproject.bean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;

public class UserDAO implements DAO<User> {
    private final Properties dbProperties;

    public UserDAO(Properties dbProperties) {
        this.dbProperties = dbProperties;
    }

    public User get(String email) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement userQuery = connection.prepareStatement(
                    "select * from user where lower(email) = ( ? )"
            );
            userQuery.setString(1, email.toLowerCase(Locale.ROOT));
            ResultSet resultSet = userQuery.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getInt("id"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getDate("birth_date"),
                        resultSet.getString("email"), resultSet.getString("password"),
                        resultSet.getString("address"));
            }

            return null;
        }
    }

    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");
            ArrayList<User> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new User(resultSet.getInt("id"), resultSet.getString("first_name"),
                        resultSet.getString("last_name"), resultSet.getDate("birth_date"),
                        resultSet.getString("email"), resultSet.getString("password"),
                        resultSet.getString("address")));
            }
            return list;
        }
    }

    public Integer insert(User user) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into user (first_name, last_name, birth_date, email, password, address) " +
                            "values (?, ?, ?, ?, ?, ?)"
            );
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setDate(3, user.getBirthDate());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getAddress());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not insert User");
            preparedStatement.close();
            ResultSet resultSet = connection.createStatement().executeQuery("select last_insert_id()");
            resultSet.next();
            return resultSet.getInt(1);
        }
    }

    public void update(User user) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update user set " +
                            "first_name = ( ? ), " +
                            "last_name = ( ? ), " +
                            "email = ( ? ), " +
                            "birth_date = ( ? ), " +
                            "password = ( ? ), " +
                            "address = ( ? ) " +
                            "where id = ( ? )"
            );
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setDate(4, user.getBirthDate());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getAddress());
            preparedStatement.setInt(7, user.getId());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not update User");
            preparedStatement.close();
        }
    }

    public void delete(User user) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from user where id = ( ? )"
            );
            preparedStatement.setInt(1, user.getId());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not delete User");
            preparedStatement.close();
        }
    }

    private Properties getDBProperties() {
        return dbProperties;
    }
}
