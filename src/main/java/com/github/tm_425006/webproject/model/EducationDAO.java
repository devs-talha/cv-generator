package com.github.tm_425006.webproject.model;

import com.github.tm_425006.webproject.exception.CVGeneratorException;
import com.github.tm_425006.webproject.bean.Education;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class EducationDAO implements DAO<Education>{
    private final Properties dbProperties;

    public EducationDAO(Properties dbProperties) {
        this.dbProperties = dbProperties;
    }

    public Education get(int id) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement userQuery = connection.prepareStatement(
                    "select * from education where id = ( ? )"
            );
            userQuery.setInt(1, id);
            ResultSet resultSet = userQuery.executeQuery();
            if (resultSet.next()) {
                return new Education(id, resultSet.getInt("cv_id"),
                        resultSet.getString("name"), resultSet.getString("institute"),
                        resultSet.getDate("start_date"), resultSet.getDate("end_date"),
                        resultSet.getString("grade"));
            }
            return null;
        }
    }

    public ArrayList<Education> getAll(int cvId) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement userQuery = connection.prepareStatement(
                    "select * from education where cv_id = ( ? )"
            );
            userQuery.setInt(1, cvId);
            ResultSet resultSet = userQuery.executeQuery();
            ArrayList<Education> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new Education(resultSet.getInt("id"), cvId,
                        resultSet.getString("name"), resultSet.getString("institute"),
                        resultSet.getDate("start_date"), resultSet.getDate("end_date"),
                        resultSet.getString("grade")));
            }
            return list;
        }
    }

    public Integer insert(Education education) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into education (cv_id, name, institute, start_date, end_date, grade) " +
                            "values (?, ?, ?, ?, ?, ?)"
            );
            preparedStatement.setInt(1, education.getCvId());
            preparedStatement.setString(2, education.getName());
            preparedStatement.setString(3, education.getInstitute());
            preparedStatement.setDate(4, education.getStartDate());
            if (education.getEndDate() != null)
                preparedStatement.setDate(5, education.getEndDate());
            else
                preparedStatement.setNull(5, Types.DATE);
            preparedStatement.setString(6, education.getGrade());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not insert Education: " + education);
            preparedStatement.close();
            ResultSet resultSet = connection.createStatement().executeQuery("select last_insert_id()");
            resultSet.next();
            return resultSet.getInt(1);
        }
    }

    public void update(Education education) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update education set " +
                            "name = ( ? ), " +
                            "institute = ( ? ), " +
                            "start_date = ( ? ), " +
                            "end_date = ( ? ), " +
                            "grade = ( ? ) " +
                            "where id = ( ? )"
            );
            preparedStatement.setString(1, education.getName());
            preparedStatement.setString(2, education.getInstitute());
            preparedStatement.setDate(3, education.getStartDate());
            if (education.getEndDate() != null)
                preparedStatement.setDate(4, education.getEndDate());
            else
                preparedStatement.setNull(4, Types.DATE);
            preparedStatement.setString(5, education.getGrade());
            preparedStatement.setInt(6, education.getId());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not update Education: " + education);
            preparedStatement.close();
        }
    }

    public void delete(Education education) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from education where id = ( ? )"
            );
            preparedStatement.setInt(1, education.getId());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not delete Education: " + education);
            preparedStatement.close();
        }
    }

    private Properties getDBProperties() {
        return dbProperties;
    }
}
