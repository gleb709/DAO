package task.epam.dao;

import edu.epam.daotask.exception.BaseDaoException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import edu.epam.daotask.constant.PrintingCenter;
import edu.epam.daotask.entity.Book;
import edu.epam.daotask.dao.impl.BookDaoImpl;
import edu.epam.daotask.storage.WareHouse;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class BookDaoImplTest {
    private List<Book> libraryExpected;
    private WareHouse wareHouse;
    private BookDaoImpl bookDaoImpl;

    @BeforeMethod
    public void setInfo(){
        wareHouse = WareHouse.getInstance();
        bookDaoImpl = new BookDaoImpl();
        libraryExpected = new ArrayList<>();
        Book book = new Book(1, 2000, "Bredberi", "451*F", PrintingCenter.LONDON);
        wareHouse.addBook(book);
        libraryExpected.add(book);
        book = new Book(2,1987, "Tolstoi", "Voina&Mir", PrintingCenter.BREST);
        wareHouse.addBook(book);
        libraryExpected.add(book);
        book = new Book(3, 1999, "Bredberi", "VinoIzOdyvanchikov", PrintingCenter.LONDON);
        libraryExpected.add(book);
        wareHouse.addBook(book);
    }

    @Test
    public void testFindAll() {
        List<Book> libraryActual = null;
        try {
            libraryActual = bookDaoImpl.findAll();
        } catch (BaseDaoException e) {
            e.printStackTrace();
        }
        assertEquals(libraryActual, libraryExpected);
    }

    @Test
    public void testFindEntityByName() {
        String name = "451*F";
        Book expectedBook = new Book(1, 2000, "Bredberi", "451*F", PrintingCenter.LONDON);
        Book actualBook = null;
        try {
            actualBook = bookDaoImpl.findEntityByName(name);
        } catch (BaseDaoException e) {
            e.printStackTrace();
        }
        assertEquals(actualBook,expectedBook);
    }

    @Test
    public void testFindEntityById() {
        int id = 1;
        Book expectedBook = new Book(1, 2000, "Bredberi", "451*F", PrintingCenter.LONDON);
        Book actualBook = null;
        try {
            actualBook = bookDaoImpl.findEntityById(id);
        } catch (BaseDaoException e) {
            e.printStackTrace();
        }
        assertEquals(actualBook,expectedBook);
    }

    @Test
    public void testFindEntitiesByAuthor() {
        String author = "Tolstoi";
        libraryExpected.remove(0);
        libraryExpected.remove(1);
        List<Book> libraryActual = null;
        try {
            libraryActual = bookDaoImpl.findEntitiesByAuthor(author);
        } catch (BaseDaoException e) {
            e.printStackTrace();
        }
        assertEquals(libraryActual, libraryExpected);
    }

    @Test
    public void testFindEntitiesByYear() {
        int year = 2000;
        libraryExpected.remove(libraryExpected.size()-1);
        libraryExpected.remove(libraryExpected.size()-1);
        List<Book> libraryActual = null;
        try {
            libraryActual = bookDaoImpl.findEntitiesByYear(year);
        } catch (BaseDaoException e) {
            e.printStackTrace();
        }
        assertEquals(libraryActual, libraryExpected);
    }

    @Test
    public void testDelete() {
        libraryExpected.remove(libraryExpected.size() - 1);
        Book book = new Book(3, 1999, "Bredberi", "VinoIzOdyvanchikov", PrintingCenter.LONDON);
        try {
            bookDaoImpl.delete(book);
        } catch (BaseDaoException e) {
            e.printStackTrace();
        }
        List<Book> libraryActual = null;
        try {
            libraryActual = bookDaoImpl.findAll();
        } catch (BaseDaoException e) {
            e.printStackTrace();
        }
        assertEquals(libraryActual, libraryExpected);
    }

    @Test
    public void testAdd() {
        Book newBook = new Book(13, 2000, "Tolstoyi", "SmertIvana", PrintingCenter.BREST);
        libraryExpected.add(newBook);
        try {
            bookDaoImpl.add(newBook);
        } catch (BaseDaoException e) {
            e.printStackTrace();
        }
        List<Book> libraryActual = null;
        try {
            libraryActual = bookDaoImpl.findAll();
        } catch (BaseDaoException e) {
            e.printStackTrace();
        }
        assertEquals(libraryActual, libraryExpected);
    }

    @AfterMethod
    public void deleteInfo(){
        libraryExpected = null;
        wareHouse = null;
        bookDaoImpl = null;
    }
}