package com.github.tm_425006.webproject.model;

import com.github.tm_425006.webproject.exception.CVGeneratorException;
import com.github.tm_425006.webproject.exception.NotImplementedException;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAO<T> {
    default T get(int i) throws SQLException, ClassNotFoundException, CVGeneratorException {
        throw new NotImplementedException();
    }
    default T get(String s) throws SQLException, ClassNotFoundException, CVGeneratorException {
        throw new NotImplementedException();
    }
    default ArrayList<T> getAll() throws SQLException, ClassNotFoundException, CVGeneratorException {
        throw new NotImplementedException();
    }
    default ArrayList<T> getAll(int i) throws SQLException, ClassNotFoundException, CVGeneratorException {
        throw new NotImplementedException();
    }
    default ArrayList<T> getAll(String s) throws SQLException, ClassNotFoundException, CVGeneratorException {
        throw new NotImplementedException();
    }
    Integer insert(T t) throws SQLException, ClassNotFoundException, CVGeneratorException;
    void update(T t) throws SQLException, ClassNotFoundException, CVGeneratorException;
    void delete(T t) throws SQLException, ClassNotFoundException, CVGeneratorException;
}
