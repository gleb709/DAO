package edu.epam.daotask.service.impl;
import edu.epam.daotask.constant.PrintingCenter;
import edu.epam.daotask.dao.impl.BookDaoImpl;
import edu.epam.daotask.exception.BaseDaoException;
import edu.epam.daotask.exception.LibraryReaderException;
import edu.epam.daotask.reader.LibraryReader;
import edu.epam.daotask.service.LibraryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import edu.epam.daotask.entity.Book;
import edu.epam.daotask.storage.WareHouse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.System.exit;

public class LibraryServiceImpl implements LibraryService {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        LibraryReader libraryLoader = new LibraryReader();
        LibraryServiceImpl libraryServiceImpl = new LibraryServiceImpl();
        List<Book> books = new ArrayList<>();
        BookDaoImpl bookDaoImpl = new BookDaoImpl();
        Book book = new Book();
        try {
            libraryLoader.loadLibraryFromFile();
        } catch (LibraryReaderException e) {
            e.printStackTrace();
            exit(0);
        }
        try {
            libraryServiceImpl.showLibrary(bookDaoImpl.findAll());
            libraryServiceImpl.librarySortByName();
            libraryServiceImpl.showLibrary(bookDaoImpl.findAll());
            libraryServiceImpl.addBook();
            libraryServiceImpl.showLibrary(bookDaoImpl.findAll());
            libraryServiceImpl.deleteBook();
            libraryServiceImpl.showLibrary(bookDaoImpl.findAll());
            libraryServiceImpl.updateBook();
            libraryServiceImpl.showLibrary(bookDaoImpl.findAll());
        }catch (BaseDaoException e){
            e.printStackTrace();
        }
        book = libraryServiceImpl.findEntityByName("IgraPrestolov");
        if(book != null){
            System.out.println(book.toString());
        }else{
            System.out.println("Книга не найдена");
        }
        book = libraryServiceImpl.findEntityById(10);
        if(book != null){
            System.out.println(book.toString());
        }else{
            System.out.println("Книга не найдена");
        }
        books = libraryServiceImpl.findEntitiesByAuthor("Tolstoi");
        System.out.println("Результат поиска: ");
        for (Book eachBook: books) {
            System.out.println(eachBook.toString());
        }
        books = libraryServiceImpl.findEntitiesByYear(1999);
        System.out.println("Результат поиска: ");
        for (Book eachBook: books) {
            System.out.println(eachBook.toString());
        }
    }

    public void showLibrary(List<Book> books){
        System.out.println("Библиотека:\n");
        for (Book book: books) {
        System.out.println(book.toString());
    }
        System.out.println("------------------------------------------------------------------");
}

    public Book findEntityByName(String name){
        BookDaoImpl bookDaoImpl = new BookDaoImpl();
        Book book = null;
        try {
            book = bookDaoImpl.findEntityByName(name);
            logger.info("Запрос на поиск книги выполнен успешно");
        } catch (BaseDaoException e) {
            logger.info("Запрос на поиск книги провален");
            e.printStackTrace();
        }
        return book;
    }

    public Book findEntityById(int id){
        BookDaoImpl bookDaoImpl = new BookDaoImpl();
        Book book = new Book();
        try {
            book = bookDaoImpl.findEntityById(id);
            logger.info("Запрос на поиск книги выполнен успешно");
        }catch(BaseDaoException e){
            logger.info("Запрос на поиск книги провален");
            e.printStackTrace();
        }
        return book;
    }

    public List<Book> findEntitiesByAuthor(String author){
        BookDaoImpl bookDaoImpl = new BookDaoImpl();
        List<Book> books = new ArrayList<>();
        try{
        books = bookDaoImpl.findEntitiesByAuthor(author);
            logger.info("Запрос на поиск книг выполнен успешно");
        }catch(BaseDaoException e){
            logger.info("Запрос на поиск книг провален");
            e.printStackTrace();
        }
        return books;
    }

    public List<Book> findEntitiesByYear(int year){
        BookDaoImpl bookDaoImpl = new BookDaoImpl();
        List<Book> books = new ArrayList<>();
        try{
            books = bookDaoImpl.findEntitiesByYear(year);
            logger.info("Запрос на поиск книги выполнен успешно");
        }catch(BaseDaoException e){
            logger.info("Запрос на поиск книги провален");
            e.printStackTrace();
        }
        return books;
    }

    public boolean addBook(){
        Book newBook = new Book(13, 2000, "Tolstoyi", "SmertIvana", PrintingCenter.BREST);
        boolean success = true;
        List<Book> library = new ArrayList<>();
        BookDaoImpl bookDaoImpl = new BookDaoImpl();
        try {
            library = bookDaoImpl.findAll();
        } catch (BaseDaoException e) {
            e.printStackTrace();
        }
        for (Book book: library) {
            if(book.getBookName().equals(newBook.getBookName())) {
                try {
                    bookDaoImpl.add(newBook);
                    logger.info("Запрос на добавление книги выполнен успешно. \nДобавлена книга" + newBook.toString());
                } catch (BaseDaoException e) {
                    success = false;
                    logger.info("Запрос на добавление книги провален");
                    e.printStackTrace();
                }
            }
        }
        return success;
    }

    public boolean deleteBook(){
        boolean success = false;
        List<Book> library = new ArrayList<>();
        BookDaoImpl bookDaoImpl = new BookDaoImpl();
        Book newBook = new Book(12, 1985, "Gogol", "MertvieDushi", PrintingCenter.MOSKOW);
        try{
            library = bookDaoImpl.findAll();
            logger.info("Библиотека загружена");
        }catch (BaseDaoException e){
            logger.info("Ошибка загрузки");
            e.printStackTrace();
        }
        for (Book book: library) {
            if(book.getBookName().equals(newBook.getBookName())){
                success = true;
            }
        }
        if(success == true) {
            try {
                bookDaoImpl.delete(newBook);
                logger.info("Запрос на уделение книги выполнен успешно.\n Удаленная книга:" + newBook.toString());
            } catch (BaseDaoException e) {
                logger.info("Запрос на удаление книги провален");
                e.printStackTrace();
            }
        }
        return success;
    }

    public boolean updateBook(){
        boolean success = false;
        List<Book> library = new ArrayList<>();
        BookDaoImpl bookDaoImpl = new BookDaoImpl();
        try{
            library = bookDaoImpl.findAll();
            logger.info("Библиотека загружена");
        }catch (BaseDaoException e){
            logger.info("Ошибка загрузки");
            e.printStackTrace();
        }
        Book newBook = new Book(12, 1992, "Martin", "IgraPrestolov", PrintingCenter.GOMEL);
        for (Book book: library) {
            if (book.getBookName().equals(newBook.getBookName())) {
                book.setBookId(newBook.getBookId());
                success = true;
                try {
                    bookDaoImpl.update(newBook);
                    logger.info("Запрос на обновление данных о книге выполнен успешно. \nОбновленная книга: " + newBook.toString());
                } catch (BaseDaoException e) {
                    logger.info("Запрос на обновление данных о книге провален");
                }
            }
        }
        return success;
    }

    public List<Book> librarySortByName(){
        Comparator<Book> comparator = Comparator.comparing(Book :: getBookName);
        WareHouse wareHouse = WareHouse.getInstance();
        BookDaoImpl bookDaoImpl = new BookDaoImpl();
        List<Book> library = new ArrayList<>();
        try{
            library = bookDaoImpl.findAll();
            logger.info("Библиотека загружена");
        }catch (BaseDaoException e){
            logger.info("Ошибка загрузки");
            e.printStackTrace();
        }
        wareHouse.clearLibrary();
        library.sort(comparator);
        try {
            for (int i = 0; i < library.size(); i++) {
                wareHouse.addBook(library.get(i));
            }
            logger.info("Массив library отсортирован");
        }catch (NullPointerException e){
            logger.info("Ошибка сортировки");
        }
        return library;
    }
}
