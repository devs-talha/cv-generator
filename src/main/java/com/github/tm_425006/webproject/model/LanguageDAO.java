package com.github.tm_425006.webproject.model;

import com.github.tm_425006.webproject.exception.CVGeneratorException;
import com.github.tm_425006.webproject.bean.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class LanguageDAO implements DAO<Language> {
    private final Properties dbProperties;

    public LanguageDAO(Properties dbProperties) {
        this.dbProperties = dbProperties;
    }

    public Language get(int id) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement userQuery = connection.prepareStatement(
                    "select * from language where id = ( ? )"
            );
            userQuery.setInt(1, id);
            ResultSet resultSet = userQuery.executeQuery();
            if (resultSet.next()) {
                return new Language(id, resultSet.getInt("cv_id"),
                        resultSet.getString("name"));
            }
            return null;
        }
    }

    public ArrayList<Language> getAll(int cvId) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement userQuery = connection.prepareStatement(
                    "select * from language where cv_id = ( ? )"
            );
            userQuery.setInt(1, cvId);
            ResultSet resultSet = userQuery.executeQuery();
            ArrayList<Language> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new Language(resultSet.getInt("id"), cvId,
                        resultSet.getString("name")));
            }
            return list;
        }
    }

    public Integer insert(Language language) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into language (cv_id, name) " +
                            "values (?, ?)"
            );
            preparedStatement.setInt(1, language.getCvId());
            preparedStatement.setString(2, language.getName());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not insert Language: " + language);
            preparedStatement.close();
            ResultSet resultSet = connection.createStatement().executeQuery("select last_insert_id()");
            resultSet.next();
            return resultSet.getInt(1);
        }
    }

    public void update(Language language) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update language set " +
                            "name = ( ? ) " +
                            "where id = ( ? )"
            );
            preparedStatement.setString(1, language.getName());
            preparedStatement.setInt(2, language.getId());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not update Language: " + language);
            preparedStatement.close();
        }
    }

    public void delete(Language language) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from language where id = ( ? )"
            );
            preparedStatement.setInt(1, language.getId());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not delete Language: " + language);
            preparedStatement.close();
        }
    }

    private Properties getDBProperties() {
        return dbProperties;
    }
}
