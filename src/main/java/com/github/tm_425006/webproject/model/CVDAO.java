package com.github.tm_425006.webproject.model;

import com.github.tm_425006.webproject.exception.CVGeneratorException;
import com.github.tm_425006.webproject.bean.CV;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class CVDAO implements DAO<CV> {
    private final Properties dbProperties;

    public CVDAO(Properties dbProperties) {
        this.dbProperties = dbProperties;
    }

    public CV get(int id) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement userQuery = connection.prepareStatement(
                    "select * from cv where id = ( ? )"
            );
            userQuery.setInt(1, id);
            ResultSet resultSet = userQuery.executeQuery();
            if (resultSet.next()) {
                CV cv = new CV();
                cv.setId(id);
                cv.setUserId(resultSet.getInt("user_id"));
                cv.setCreatedDate(resultSet.getDate("created_date"));
                cv.setModifiedDate(resultSet.getDate("modified_date"));
                return cv;
            }
            return null;
        }
    }

    public ArrayList<CV> getAll(int userId) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement userQuery = connection.prepareStatement(
                    "select * from cv where user_id = ( ? )"
            );
            userQuery.setInt(1, userId);
            ResultSet resultSet = userQuery.executeQuery();
            ArrayList<CV> list = new ArrayList<>();
            while (resultSet.next()) {
                CV cv = new CV();
                cv.setId(resultSet.getInt("id"));
                cv.setUserId(userId);
                cv.setCreatedDate(resultSet.getDate("created_date"));
                cv.setModifiedDate(resultSet.getDate("modified_date"));
                list.add(cv);
            }
            return list;
        }
    }

    public Integer insert(CV cv) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into cv (user_id, created_date, modified_date) " +
                            "values (?, ?, ?)"
            );
            preparedStatement.setInt(1, cv.getUserId());
            preparedStatement.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
            preparedStatement.setDate(3, cv.getModifiedDate());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not insert CV: " + cv);
            preparedStatement.close();
            ResultSet resultSet = connection.createStatement().executeQuery("select last_insert_id()");
            resultSet.next();
            return resultSet.getInt(1);
        }
    }

    public void update(CV cv) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update cv set " +
                            "created_date = ( ? ), " +
                            "modified_date = ( ? ) " +
                            "where id = ( ? )"
            );
            preparedStatement.setDate(1, cv.getCreatedDate());
            preparedStatement.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
            preparedStatement.setInt(3, cv.getId());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not update CV: " + cv);
            preparedStatement.close();
        }
    }

    public void delete(CV cv) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from cv where id = ( ? )"
            );
            preparedStatement.setInt(1, cv.getId());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not delete CV: " + cv);
            preparedStatement.close();
        }
    }

    private Properties getDBProperties() {
        return dbProperties;
    }
}
