package com.github.tm_425006.webproject.model;

import com.github.tm_425006.webproject.exception.CVGeneratorException;
import com.github.tm_425006.webproject.bean.Interest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class InterestDAO implements DAO<Interest> {
    private final Properties dbProperties;

    public InterestDAO(Properties dbProperties) {
        this.dbProperties = dbProperties;
    }

    public Interest get(int id) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement userQuery = connection.prepareStatement(
                    "select * from interest where id = ( ? )"
            );
            userQuery.setInt(1, id);
            ResultSet resultSet = userQuery.executeQuery();
            if (resultSet.next()) {
                return new Interest(id, resultSet.getInt("cv_id"),
                        resultSet.getString("name"));
            }
            return null;
        }
    }

    public ArrayList<Interest> getAll(int cvId) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement userQuery = connection.prepareStatement(
                    "select * from interest where cv_id = ( ? )"
            );
            userQuery.setInt(1, cvId);
            ResultSet resultSet = userQuery.executeQuery();
            ArrayList<Interest> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new Interest(resultSet.getInt("id"), cvId,
                        resultSet.getString("name")));
            }
            return list;
        }
    }

    public Integer insert(Interest interest) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into interest (cv_id, name) " +
                            "values (?, ?)"
            );
            preparedStatement.setInt(1, interest.getCvId());
            preparedStatement.setString(2, interest.getName());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not insert Interest: " + interest);
            preparedStatement.close();
            ResultSet resultSet = connection.createStatement().executeQuery("select last_insert_id()");
            resultSet.next();
            return resultSet.getInt(1);
        }
    }

    public void update(Interest interest) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update interest set " +
                            "name = ( ? ) " +
                            "where id = ( ? )"
            );
            preparedStatement.setString(1, interest.getName());
            preparedStatement.setInt(2, interest.getId());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not update Interest: " + interest);
            preparedStatement.close();
        }
    }

    public void delete(Interest interest) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from interest where id = ( ? )"
            );
            preparedStatement.setInt(1, interest.getId());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not delete Interest: " + interest);
            preparedStatement.close();
        }
    }

    private Properties getDBProperties() {
        return dbProperties;
    }
}
