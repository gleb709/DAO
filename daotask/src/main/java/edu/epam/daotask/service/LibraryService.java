package edu.epam.daotask.service;

import edu.epam.daotask.entity.Book;

import java.util.List;

public interface LibraryService {
    void showLibrary(List<Book> books);
    Book findEntityByName(String name);
    Book findEntityById(int id);
    List<Book>  findEntitiesByAuthor(String author);
    List<Book> findEntitiesByYear(int year);
    boolean addBook();
    boolean deleteBook();
    boolean updateBook();
    List<Book> librarySortByName();
}
