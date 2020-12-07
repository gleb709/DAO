package edu.epam.daotask.dao;

import edu.epam.daotask.entity.Book;
import edu.epam.daotask.exception.BaseDaoException;

import java.util.List;

public interface BaseDao{
    public abstract List<Book> findAll() throws BaseDaoException;
    public abstract Book findEntityByName(String name) throws BaseDaoException;
    public abstract Book findEntityById(int id) throws BaseDaoException;
    public abstract List<Book> findEntitiesByAuthor(String author) throws BaseDaoException;
    public abstract List<Book> findEntitiesByYear(int year) throws BaseDaoException;
    public abstract boolean delete(Book book) throws BaseDaoException;
    public abstract boolean add(Book book) throws BaseDaoException;
    public abstract boolean update(Book book) throws BaseDaoException;
}
