package com.github.tm_425006.webproject.model;

import com.github.tm_425006.webproject.exception.CVGeneratorException;
import com.github.tm_425006.webproject.bean.Certificate;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class CertificateDAO implements DAO<Certificate> {
    private final Properties dbProperties;

    public CertificateDAO(Properties dbProperties) {
        this.dbProperties = dbProperties;
    }

    public Certificate get(int id) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement userQuery = connection.prepareStatement(
                    "select * from certificate where id = ( ? )"
            );
            userQuery.setInt(1, id);
            ResultSet resultSet = userQuery.executeQuery();
            if (resultSet.next()) {
                return new Certificate(id, resultSet.getInt("cv_id"),
                        resultSet.getString("name"), resultSet.getString("institute"),
                        resultSet.getDate("completion_date"));
            }
            return null;
        }
    }

    public ArrayList<Certificate> getAll(int cvId) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement userQuery = connection.prepareStatement(
                    "select * from certificate where cv_id = ( ? )"
            );
            userQuery.setInt(1, cvId);
            ResultSet resultSet = userQuery.executeQuery();
            ArrayList<Certificate> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new Certificate(resultSet.getInt("id"), cvId,
                        resultSet.getString("name"), resultSet.getString("institute"),
                        resultSet.getDate("completion_date")));
            }
            return list;
        }
    }

    public Integer insert(Certificate certificate) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into certificate (cv_id, name, institute, completion_date) " +
                            "values (?, ?, ?, ?)"
            );
            preparedStatement.setInt(1, certificate.getCvId());
            preparedStatement.setString(2, certificate.getName());
            preparedStatement.setString(3, certificate.getInstitute());
            preparedStatement.setDate(4, certificate.getCompletionDate());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not insert Certificate: " + certificate);
            preparedStatement.close();
            ResultSet resultSet = connection.createStatement().executeQuery("select last_insert_id()");
            resultSet.next();
            return resultSet.getInt(1);
        }
    }

    public void update(Certificate certificate) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update certificate set " +
                            "name = ( ? ), " +
                            "institute = ( ? ), " +
                            "completion_date = ( ? ) " +
                            "where id = ( ? )"
            );
            preparedStatement.setString(1, certificate.getName());
            preparedStatement.setString(2, certificate.getInstitute());
            preparedStatement.setDate(3, certificate.getCompletionDate());
            preparedStatement.setInt(4, certificate.getId());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not update Certificate: " + certificate);
            preparedStatement.close();
        }
    }

    public void delete(Certificate certificate) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from certificate where id = ( ? )"
            );
            preparedStatement.setInt(1, certificate.getId());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not delete Certificate: " + certificate);
            preparedStatement.close();
        }
    }

    private Properties getDBProperties() {
        return dbProperties;
    }
}
