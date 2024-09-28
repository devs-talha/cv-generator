package com.github.tm_425006.webproject.model;

import com.github.tm_425006.webproject.exception.CVGeneratorException;
import com.github.tm_425006.webproject.bean.ContactInformation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class ContactInformationDAO implements DAO<ContactInformation> {
    private final Properties dbProperties;

    public ContactInformationDAO(Properties dbProperties) {
        this.dbProperties = dbProperties;
    }

    public ContactInformation get(int id) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement userQuery = connection.prepareStatement(
                    "select * from contact_information where id = ( ? )"
            );
            userQuery.setInt(1, id);
            ResultSet resultSet = userQuery.executeQuery();
            if (resultSet.next()) {
                return new ContactInformation(id, resultSet.getInt("cv_id"),
                        resultSet.getString("name"), resultSet.getString("url"));
            }
            return null;
        }
    }

    public ArrayList<ContactInformation> getAll(int cvId) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement userQuery = connection.prepareStatement(
                    "select * from contact_information where cv_id = ( ? )"
            );
            userQuery.setInt(1, cvId);
            ResultSet resultSet = userQuery.executeQuery();
            ArrayList<ContactInformation> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new ContactInformation(resultSet.getInt("id"), cvId,
                        resultSet.getString("name"), resultSet.getString("url")));
            }
            return list;
        }
    }

    public Integer insert(ContactInformation contactInformation) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into contact_information (cv_id, name, url) " +
                            "values (?, ?, ?)"
            );
            preparedStatement.setInt(1, contactInformation.getCvId());
            preparedStatement.setString(2, contactInformation.getName());
            preparedStatement.setString(3, contactInformation.getUrl());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not insert ContactInformation: " + contactInformation);
            preparedStatement.close();
            ResultSet resultSet = connection.createStatement().executeQuery("select last_insert_id()");
            resultSet.next();
            return resultSet.getInt(1);
        }
    }

    public void update(ContactInformation contactInformation) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update contact_information set " +
                            "name = ( ? ), " +
                            "url = ( ? ) " +
                            "where id = ( ? )"
            );
            preparedStatement.setString(1, contactInformation.getName());
            preparedStatement.setString(2, contactInformation.getUrl());
            preparedStatement.setInt(3, contactInformation.getId());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not update ContactInformation: " + contactInformation);
            preparedStatement.close();
        }
    }

    public void delete(ContactInformation contactInformation) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from contact_information where id = ( ? )"
            );
            preparedStatement.setInt(1, contactInformation.getId());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not delete ContactInformation: " + contactInformation);
            preparedStatement.close();
        }
    }

    private Properties getDBProperties() {
        return dbProperties;
    }
}
