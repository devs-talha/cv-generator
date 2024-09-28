package com.github.tm_425006.webproject.model;

import com.github.tm_425006.webproject.exception.CVGeneratorException;
import com.github.tm_425006.webproject.bean.BasicInformation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class BasicInformationDAO implements DAO<BasicInformation> {
    private final Properties dbProperties;

    public BasicInformationDAO(Properties dbProperties) {
        this.dbProperties = dbProperties;
    }

    public BasicInformation get(int cvId) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement userQuery = connection.prepareStatement(
                    "select * from basic_information where cv_id = ( ? )"
            );
            userQuery.setInt(1, cvId);
            ResultSet resultSet = userQuery.executeQuery();
            if (resultSet.next()) {
                return new BasicInformation(resultSet.getInt("id"), cvId,
                        resultSet.getString("name"), resultSet.getString("address"),
                        resultSet.getDate("birth_date"));
            }
            return null;
        }
    }

    public Integer insert(BasicInformation basicInformation) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into basic_information (cv_id, name, address, birth_date) " +
                            "values (?, ?, ?, ?)"
            );
            preparedStatement.setInt(1, basicInformation.getCvId());
            preparedStatement.setString(2, basicInformation.getName());
            preparedStatement.setString(3, basicInformation.getAddress());
            preparedStatement.setDate(4, basicInformation.getBirthDate());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not insert BasicInformation: " + basicInformation);
            preparedStatement.close();
            ResultSet resultSet = connection.createStatement().executeQuery("select last_insert_id()");
            resultSet.next();
            return resultSet.getInt(1);
        }
    }

    public void update(BasicInformation basicInformation) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update basic_information set " +
                            "name = ( ? ), " +
                            "address = ( ? ), " +
                            "birth_date = ( ? ) " +
                            "where id = ( ? )"
            );
            preparedStatement.setString(1, basicInformation.getName());
            preparedStatement.setString(2, basicInformation.getAddress());
            preparedStatement.setDate(3, basicInformation.getBirthDate());
            preparedStatement.setInt(4, basicInformation.getId());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not update BasicInformation: " + basicInformation);
            preparedStatement.close();
        }
    }

    public void delete(BasicInformation basicInformation) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from basic_information where id = ( ? )"
            );
            preparedStatement.setInt(1, basicInformation.getId());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not delete BasicInformation: " + basicInformation);
            preparedStatement.close();
        }
    }

    private Properties getDBProperties() {
        return dbProperties;
    }
}
