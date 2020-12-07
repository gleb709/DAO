package task.epam.service;

import edu.epam.daotask.exception.BaseDaoException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import edu.epam.daotask.constant.PrintingCenter;
import edu.epam.daotask.dao.impl.BookDaoImpl;
import edu.epam.daotask.entity.Book;
import edu.epam.daotask.storage.WareHouse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class LibraryServiceImplTest {
    private List<Book> libraryExpected;
    private WareHouse wareHouse;

    @BeforeMethod
    public void setInfo(){
        WareHouse wareHouse = WareHouse.getInstance();
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
    public void testShowLibrary() {
        BookDaoImpl bookDaoImpl = new BookDaoImpl();
        List<Book> libraryActual = null;
        try {
            libraryActual = bookDaoImpl.findAll();
        } catch (BaseDaoException e) {
            e.printStackTrace();
        }
        assertEquals(libraryActual,libraryExpected);
    }

    @Test
    public void testFindEntityByName() {
        BookDaoImpl bookDaoImpl = new BookDaoImpl();
        Book actualEntity = null;
        try {
            actualEntity = bookDaoImpl.findEntityByName("451*F");
        } catch (BaseDaoException e) {
            e.printStackTrace();
        }
        Book expectedEntity = new Book(1, 2000, "Bredberi", "451*F", PrintingCenter.LONDON);
        assertEquals(actualEntity,expectedEntity);
    }

    @Test
    public void testFindEntityById() {
        BookDaoImpl bookDaoImpl = new BookDaoImpl();
        Book actualEntity = null;
        try {
            actualEntity = bookDaoImpl.findEntityById(1);
        } catch (BaseDaoException e) {
            e.printStackTrace();
        }
        Book expectedEntity = new Book(1, 2000, "Bredberi", "451*F", PrintingCenter.LONDON);
        assertEquals(actualEntity,expectedEntity);
    }

    @Test
    public void testFindEntitiesByAuthor() {
        BookDaoImpl bookDaoImpl = new BookDaoImpl();
        List<Book> actualEntity = null;
        try {
            actualEntity = bookDaoImpl.findEntitiesByAuthor("Bredberi");
        } catch (BaseDaoException e) {
            e.printStackTrace();
        }
        List<Book> expectedEntity = new ArrayList<>();
        expectedEntity.add(new Book(1, 2000, "Bredberi", "451*F", PrintingCenter.LONDON));
        assertEquals(actualEntity,expectedEntity);
    }

    @Test
    public void testFindEntitiesByYear() {
        BookDaoImpl bookDaoImpl = new BookDaoImpl();
        List<Book> actualEntity = null;
        try {
            actualEntity = bookDaoImpl.findEntitiesByYear(2000);
        } catch (BaseDaoException e) {
            e.printStackTrace();
        }
        List<Book> expectedEntity = new ArrayList<>();
        expectedEntity.add(new Book(1, 2000, "Bredberi", "451*F", PrintingCenter.LONDON));
        assertEquals(actualEntity,expectedEntity);
    }

    @Test
    public void testAddBook() {
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
            if(book.getBookName().equals(newBook.getBookName())){
                success = false;
            }
        }
        if(success == true){
            try {
                bookDaoImpl.add(newBook);
            } catch (BaseDaoException e) {
                e.printStackTrace();
            }
        }
        Book actualBook = wareHouse.loadBookFromLibrary(wareHouse.getLibrarySize()-1);
        Book expectedBook = newBook;
        assertEquals(actualBook, expectedBook);
    }

    @Test
    public void testDeleteBook() {
        Book newBook = new Book(2,1987, "Tolstoi", "Voina&Mir", PrintingCenter.BREST);
        boolean success = false;
        List<Book> library = new ArrayList<>();
        BookDaoImpl bookDaoImpl = new BookDaoImpl();
        try {
            library = bookDaoImpl.findAll();
        } catch (BaseDaoException e) {
            e.printStackTrace();
        }
        for (Book book: library) {
            if(book.getBookName().equals(newBook.getBookName())){
                success = true;
            }
        }
        if(success == true){
            try {
                bookDaoImpl.delete(newBook);
            } catch (BaseDaoException e) {
                e.printStackTrace();
            }
        }
        List<Book> libraryActual = null;
        try {
            libraryActual = bookDaoImpl.findAll();
        } catch (BaseDaoException e) {
            e.printStackTrace();
        }
        libraryExpected.remove(library.size() - 1);
        assertEquals(libraryActual, libraryExpected);
    }

    @Test
    public void testUpdateBook() {
        Book newBook = new Book(1, 2000, "Tolstoyi", "SmertIvana", PrintingCenter.BREST);
        boolean success = false;
        int bookId = 0;
        List<Book> library = new ArrayList<>();
        BookDaoImpl bookDaoImpl = new BookDaoImpl();
        try {
            library = bookDaoImpl.findAll();
        } catch (BaseDaoException e) {
            e.printStackTrace();
        }
        for (Book book: library) {
            if(book.getBookName().equals(newBook.getBookName())){
                book.setBookId(newBook.getBookId());
                success = true;
            }
        }
        if(success == true) {
            try {
                bookDaoImpl.update(newBook);
            } catch (BaseDaoException e) {
                e.printStackTrace();
            }
            Book actualBook = wareHouse.loadBookFromLibrary(bookId);
            Book expectedBook = new Book(1, 2000, "Tolstoyi", "SmertIvana", PrintingCenter.BREST);
            assertEquals(actualBook, expectedBook);
        }
    }

    @Test
    public void testLibrarySortByName() {
        Comparator<Book> comparator = Comparator.comparing(Book :: getBookName);
        libraryExpected.remove(libraryExpected.size()-1);
        libraryExpected.remove(libraryExpected.size()-1);
        Book book = new Book(3, 1999, "Bredberi", "VinoIzOdyvanchikov", PrintingCenter.LONDON);
        libraryExpected.add(book);
        book = new Book(2,1987, "Tolstoi", "Voina&Mir", PrintingCenter.BREST);
        libraryExpected.add(book);

        WareHouse wareHouse = WareHouse.getInstance();
        BookDaoImpl bookDaoImpl = new BookDaoImpl();
        List<Book> libraryActual = null;
        try {
            libraryActual = bookDaoImpl.findAll();
        } catch (BaseDaoException e) {
            e.printStackTrace();
        }
        wareHouse.clearLibrary();
        libraryActual.sort(comparator);
        for(int i = 0; i < libraryActual.size(); i++){
            wareHouse.addBook(libraryActual.get(i));
        }
        assertEquals(libraryActual,libraryExpected);
    }

    @AfterMethod
    public void deleteInfo(){
        libraryExpected = null;
        wareHouse = null;
    }
}