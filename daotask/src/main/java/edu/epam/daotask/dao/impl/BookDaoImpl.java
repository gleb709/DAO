package edu.epam.daotask.dao.impl;

import edu.epam.daotask.dao.BaseDao;
import edu.epam.daotask.entity.Book;
import edu.epam.daotask.storage.WareHouse;
import edu.epam.daotask.exception.BaseDaoException;

import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BaseDao  {
    public List<Book> findAll() throws BaseDaoException{
        List<Book> books = new ArrayList<>();
        WareHouse wareHouse = WareHouse.getInstance();
        if(wareHouse.libraryIsEmpty()){
            throw new BaseDaoException("Библиотека пуста");
        }
        for(int i = 0; i < wareHouse.getLibrarySize(); i++) {
            Book newBook = new Book();
            newBook.setBookId(wareHouse.loadBookFromLibrary(i).getBookId());
            newBook.setBookName(wareHouse.loadBookFromLibrary(i).getBookName());
            newBook.setBookAuthor(wareHouse.loadBookFromLibrary(i).getBookAuthor());
            newBook.setBookPublisher(wareHouse.loadBookFromLibrary(i).getBookPublisher());
            newBook.setBookYearPublished(wareHouse.loadBookFromLibrary(i).getBookYearPublished());
            books.add(newBook);
        }
        return books;
    }

    public Book findEntityByName(String name) throws BaseDaoException {
        Book book = null;
        WareHouse wareHouse = WareHouse.getInstance();
        for(int i = 0; i < wareHouse.getLibrarySize(); i++) {
            if(wareHouse.loadBookFromLibrary(i).getBookName().equals(name)) {
                book = wareHouse.loadBookFromLibrary(i);
            }
        }
        if(book == null){
            throw new BaseDaoException("Книга с таким названием не найдена");
        }
        return book;
    }

    @Override
    public Book findEntityById(int id) throws BaseDaoException{
        Book book = null;
        WareHouse wareHouse = WareHouse.getInstance();
        for(int i = 0; i < wareHouse.getLibrarySize(); i++) {
            Integer integer = wareHouse.loadBookFromLibrary(i).getBookId();
            if(integer.equals(id)) {
                book = wareHouse.loadBookFromLibrary(i);
            }
        }
        if(book == null){
            throw new BaseDaoException("Книга с таким id не найдена");
        }
        return book;
    }

    @Override
    public List<Book> findEntitiesByAuthor(String author) throws BaseDaoException{
        List<Book> books = new ArrayList<>();
        WareHouse wareHouse = WareHouse.getInstance();
        for(int i = 0; i < wareHouse.getLibrarySize(); i++) {
            if(wareHouse.loadBookFromLibrary(i).getBookAuthor().equals(author)){
                Book newBook = new Book();
                newBook.setBookId(wareHouse.loadBookFromLibrary(i).getBookId());
                newBook.setBookName(wareHouse.loadBookFromLibrary(i).getBookName());
                newBook.setBookAuthor(wareHouse.loadBookFromLibrary(i).getBookAuthor());
                newBook.setBookPublisher(wareHouse.loadBookFromLibrary(i).getBookPublisher());
                newBook.setBookYearPublished(wareHouse.loadBookFromLibrary(i).getBookYearPublished());
                books.add(newBook);
            }
        }
        if(books.isEmpty()){
            throw new BaseDaoException("Книг данного автора не найдено");
        }
        return books;
    }

    @Override
    public List<Book> findEntitiesByYear(int year) throws BaseDaoException{
        List<Book> books = new ArrayList<>();
        WareHouse wareHouse = WareHouse.getInstance();
        for(int i = 0; i < wareHouse.getLibrarySize(); i++) {
            if(wareHouse.loadBookFromLibrary(i).getBookYearPublished() == year){
                Book newBook = new Book();
                newBook.setBookId(wareHouse.loadBookFromLibrary(i).getBookId());
                newBook.setBookName(wareHouse.loadBookFromLibrary(i).getBookName());
                newBook.setBookAuthor(wareHouse.loadBookFromLibrary(i).getBookAuthor());
                newBook.setBookPublisher(wareHouse.loadBookFromLibrary(i).getBookPublisher());
                newBook.setBookYearPublished(wareHouse.loadBookFromLibrary(i).getBookYearPublished());
                books.add(newBook);
            }
        }
        if(books.isEmpty()){
            throw new BaseDaoException("Книг данного года выпуска не найдено");
        }
        return books;
    }

    public boolean delete(Book book) throws BaseDaoException{
        BookDaoImpl bookDao = new BookDaoImpl();
        try{
            bookDao.findEntityByName(book.getBookName());
        } catch (BaseDaoException e) {
            throw new BaseDaoException(e);
        }
        WareHouse wareHouse = WareHouse.getInstance();
        wareHouse.deleteBook(book);
        return true;
    }

    public boolean add(Book book) throws BaseDaoException{
        BookDaoImpl bookDao = new BookDaoImpl();
        try{
            bookDao.findEntityByName(book.getBookName());
        } catch (BaseDaoException e) {
            throw new BaseDaoException(e);
        }
        WareHouse wareHouse = WareHouse.getInstance();
        wareHouse.addBook(book);
        return true;
    }

    public boolean update(Book book) throws BaseDaoException{
        BookDaoImpl bookDao = new BookDaoImpl();
        try{
            bookDao.findEntityByName(book.getBookName());
        } catch (BaseDaoException e) {
            throw new BaseDaoException(e);
        }
        WareHouse wareHouse = WareHouse.getInstance();
        wareHouse.updateBook(book);
        return true;
    }
}
