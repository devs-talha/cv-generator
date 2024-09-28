package com.github.tm_425006.webproject.model;

import com.github.tm_425006.webproject.exception.CVGeneratorException;
import com.github.tm_425006.webproject.bean.WorkExperience;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class WorkExperienceDAO implements DAO<WorkExperience> {
    private final Properties dbProperties;

    public WorkExperienceDAO(Properties dbProperties) {
        this.dbProperties = dbProperties;
    }

    public WorkExperience get(int id) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement userQuery = connection.prepareStatement(
                    "select * from work_experience where id = ( ? )"
            );
            userQuery.setInt(1, id);
            ResultSet resultSet = userQuery.executeQuery();
            if (resultSet.next()) {
                return new WorkExperience(id, resultSet.getInt("cv_id"),
                        resultSet.getString("role"), resultSet.getString("institute"),
                        resultSet.getDate("start_date"), resultSet.getDate("end_date"),
                        resultSet.getString("details"));
            }
            return null;
        }
    }

    public ArrayList<WorkExperience> getAll(int cvId) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement userQuery = connection.prepareStatement(
                    "select * from work_experience where cv_id = ( ? )"
            );
            userQuery.setInt(1, cvId);
            ResultSet resultSet = userQuery.executeQuery();
            ArrayList<WorkExperience> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new WorkExperience(resultSet.getInt("id"), cvId,
                        resultSet.getString("role"), resultSet.getString("institute"),
                        resultSet.getDate("start_date"), resultSet.getDate("end_date"),
                        resultSet.getString("details")));
            }
            return list;
        }
    }

    public Integer insert(WorkExperience workExperience) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into work_experience (cv_id, role, institute, start_date, end_date, details) " +
                            "values (?, ?, ?, ?, ?, ?)"
            );
            preparedStatement.setInt(1, workExperience.getCvId());
            preparedStatement.setString(2, workExperience.getRole());
            preparedStatement.setString(3, workExperience.getInstitute());
            preparedStatement.setDate(4, workExperience.getStartDate());
            if (workExperience.getEndDate() != null)
                preparedStatement.setDate(5, workExperience.getEndDate());
            else
                preparedStatement.setNull(5, Types.DATE);
            preparedStatement.setString(6, workExperience.getDetails());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not insert WorkExperience: " + workExperience);
            ResultSet resultSet = connection.createStatement().executeQuery("select last_insert_id()");
            resultSet.next();
            return resultSet.getInt(1);
        }
    }

    public void update(WorkExperience workExperience) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update work_experience set " +
                            "role = ( ? ), " +
                            "institute = ( ? ), " +
                            "start_date = ( ? ), " +
                            "end_date = ( ? ), " +
                            "details = ( ? ) " +
                            "where id = ( ? )"
            );
            preparedStatement.setString(1, workExperience.getRole());
            preparedStatement.setString(2, workExperience.getInstitute());
            preparedStatement.setDate(3, workExperience.getStartDate());
            if (workExperience.getEndDate() != null)
                preparedStatement.setDate(4, workExperience.getEndDate());
            else
                preparedStatement.setNull(4, Types.DATE);
            preparedStatement.setString(5, workExperience.getDetails());
            preparedStatement.setInt(6, workExperience.getId());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not update WorkExperience: " + workExperience);
            preparedStatement.close();
        }
    }

    public void delete(WorkExperience workExperience) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from work_experience where id = ( ? )"
            );
            preparedStatement.setInt(1, workExperience.getId());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not delete WorkExperience: " + workExperience);
            preparedStatement.close();
        }
    }

    private Properties getDBProperties() {
        return dbProperties;
    }
}
