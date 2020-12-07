package edu.epam.daotask.storage;

import edu.epam.daotask.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class WareHouse {
    private static WareHouse INSTANCE = new WareHouse();
    private List<Book> library = new ArrayList<Book>();
    private int librarySize = 0;

    public void updateBook(Book book){
        int i = 0;
        while(true){
            if(library.get(i).getBookName().equals(book.getBookName())){
                library.get(i).setBookName(book.getBookName());
                library.get(i).setBookAuthor(book.getBookAuthor());
                library.get(i).setBookPublisher(book.getBookPublisher());
                library.get(i).setBookYearPublished(book.getBookYearPublished());
                break;
            }
            i++;
        }
    }

    public void clearLibrary(){
        library.clear();
        librarySize = 0;
    }

    public Book loadBookFromLibrary(int i){
        return library.get(i);
    }

    private WareHouse(){
    }

    public static WareHouse getInstance(){
        return INSTANCE;
    }

    public void addBook(Book book){
        library.add(book);
        librarySize++;
    }

    public boolean libraryIsEmpty(){
        if(library.isEmpty()){
            return true;
        }else {
            return false;
        }
    }

    public int getLibrarySize() {
        return librarySize;
    }

    public void deleteBook(Book book){
        int i = 0;
        while(true){
            if(library.get(i).getBookName().equals(book.getBookName())){
                library.remove(i);
                break;
            }
            i++;
        }
        librarySize--;
    }
}
