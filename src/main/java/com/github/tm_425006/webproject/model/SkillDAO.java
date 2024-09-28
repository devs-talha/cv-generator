package com.github.tm_425006.webproject.model;

import com.github.tm_425006.webproject.exception.CVGeneratorException;
import com.github.tm_425006.webproject.bean.Skill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class SkillDAO implements DAO<Skill> {
    private final Properties dbProperties;

    public SkillDAO(Properties dbProperties) {
        this.dbProperties = dbProperties;
    }

    public Skill get(int id) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement userQuery = connection.prepareStatement(
                    "select * from skill where id = ( ? )"
            );
            userQuery.setInt(1, id);
            ResultSet resultSet = userQuery.executeQuery();
            if (resultSet.next()) {
                return new Skill(id, resultSet.getInt("cv_id"),
                        resultSet.getString("name"));
            }
            return null;
        }
    }

    public ArrayList<Skill> getAll(int cvId) throws SQLException, ClassNotFoundException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement userQuery = connection.prepareStatement(
                    "select * from skill where cv_id = ( ? )"
            );
            userQuery.setInt(1, cvId);
            ResultSet resultSet = userQuery.executeQuery();
            ArrayList<Skill> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(new Skill(resultSet.getInt("id"), cvId,
                        resultSet.getString("name")));
            }
            return list;
        }
    }

    public Integer insert(Skill skill) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "insert into skill (cv_id, name) " +
                            "values (?, ?)"
            );
            preparedStatement.setInt(1, skill.getCvId());
            preparedStatement.setString(2, skill.getName());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not insert Skill: " + skill);
            preparedStatement.close();
            ResultSet resultSet = connection.createStatement().executeQuery("select last_insert_id()");
            resultSet.next();
            return resultSet.getInt(1);
        }
    }

    public void update(Skill skill) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "update skill set " +
                            "name = ( ? ) " +
                            "where id = ( ? )"
            );
            preparedStatement.setString(1, skill.getName());
            preparedStatement.setInt(2, skill.getId());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not update Skill: " + skill);
            preparedStatement.close();
        }
    }

    public void delete(Skill skill) throws SQLException, ClassNotFoundException, CVGeneratorException {
        try (Connection connection = ConnectionUtils.getConnection(this.getDBProperties())) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "delete from skill where id = ( ? )"
            );
            preparedStatement.setInt(1, skill.getId());
            if (preparedStatement.executeUpdate() != 1)
                throw new CVGeneratorException("Could not delete Skill: " + skill);
            preparedStatement.close();
        }
    }

    private Properties getDBProperties() {
        return dbProperties;
    }
}
