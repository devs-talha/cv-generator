package com.github.tm_425006.webproject.model;

import com.github.tm_425006.webproject.exception.CVGeneratorException;
import com.github.tm_425006.webproject.bean.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class ProjectDAO implements DAO<Project> {
    private final Properties dbProperties;

    public ProjectDAO(Properties dbProperties) {
        this.dbProperties = dbProperties;
    }

    public Project get(int id) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement userQuery = connection.prepareStatement(
                    "select * from project where id = ( ? )"
            );
            userQuery.setInt(1, id);
            ResultSet resultSet = userQuery.executeQuery();
            if (resultSet.next()) {
                return new Project(id, resultSet.getInt("cv_id"),
                        resultSet.getString("name"), resultSet.getString("details"));
            }
            return null;
        }
    }

    public ArrayList<Project> getAll(int cvId) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement userQuery = connection.prepareStatement(
                    "select * from project where cv_id = ( ? )"
            );
            userQuery.setInt(1, cvId);
            ResultSet resultSet = userQuery.executeQuery();
            ArrayList<Project> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new Project(resultSet.getInt("id"), cvId,
                        resultSet.getString("name"), resultSet.getString("details")));
            }
            return list;
        }
    }

    public Integer insert(Project project) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into project (cv_id, name, details) " +
                            "values (?, ?, ?)"
            );
            preparedStatement.setInt(1, project.getCvId());
            preparedStatement.setString(2, project.getName());
            preparedStatement.setString(3, project.getDetails());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not insert Project: " + project);
            preparedStatement.close();
            ResultSet resultSet = connection.createStatement().executeQuery("select last_insert_id()");
            resultSet.next();
            return resultSet.getInt(1);
        }
    }

    public void update(Project project) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update project set " +
                            "name = ( ? ), " +
                            "details = ( ? ) " +
                            "where id = ( ? )"
            );
            preparedStatement.setString(1, project.getName());
            preparedStatement.setString(2, project.getDetails());
            preparedStatement.setInt(3, project.getId());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not update Project: " + project);
            preparedStatement.close();
        }
    }

    public void delete(Project project) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from project where id = ( ? )"
            );
            preparedStatement.setInt(1, project.getId());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not delete Project: " + project);
            preparedStatement.close();
        }
    }

    private Properties getDBProperties() {
        return dbProperties;
    }
}
