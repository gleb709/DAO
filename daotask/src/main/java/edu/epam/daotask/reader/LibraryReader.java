package edu.epam.daotask.reader;

import edu.epam.daotask.exception.LibraryReaderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import edu.epam.daotask.constant.PrintingCenter;
import edu.epam.daotask.entity.Book;
import edu.epam.daotask.storage.WareHouse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class LibraryReader {
    private static final Logger logger = LogManager.getLogger();
    private final String FILE_NAME = "inputfile/library.txt";

    public void loadLibraryFromFile() throws LibraryReaderException{
        WareHouse wareHouse = WareHouse.getInstance();
        Scanner scanner;
        String enumCheck = "";
        try {
            scanner = new Scanner(new FileInputStream(FILE_NAME));
            int i = 0;
            while(scanner.hasNext()){
                Book book = new Book();
                book.setBookId(scanner.nextInt());
                book.setBookName(scanner.next());
                book.setBookAuthor(scanner.next());
                enumCheck = scanner.next();
                for(PrintingCenter printingCenter : PrintingCenter.values()){
                    if(enumCheck.equals(printingCenter.toString())){
                        book.setBookPublisher(printingCenter);
                    }
                }
                book.setBookYearPublished(scanner.nextInt());
                wareHouse.addBook(book);
            }
            logger.info("Библиотека загружена.\n");
        } catch (FileNotFoundException e) {
            logger.error("Ошибка загрузки библиотеки: "  + e);
            throw new LibraryReaderException("Файл для чтения не найден");
        }
    }
}
