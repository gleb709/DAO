package edu.epam.daotask.entity;

import edu.epam.daotask.constant.PrintingCenter;

public class Book {
    private int bookId;
    private int bookYearPublished;
    private String bookAuthor;
    private String bookName;
    private PrintingCenter bookPublisher;

    public Book(){}

    public Book(int bookId, int bookYearPublished, String bookAuthor, String bookName, PrintingCenter bookPublisher) {
        this.bookId = bookId;
        this.bookYearPublished = bookYearPublished;
        this.bookAuthor = bookAuthor;
        this.bookName = bookName;
        this.bookPublisher = bookPublisher;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBookYearPublished() {
        return bookYearPublished;
    }

    public void setBookYearPublished(int bookYearPublished) {
        this.bookYearPublished = bookYearPublished;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public PrintingCenter getBookPublisher() { return bookPublisher; }

    public void setBookPublisher(PrintingCenter bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(bookId);
        builder.append(" ");
        builder.append(bookName);
        builder.append(" ");
        builder.append(bookAuthor);
        builder.append(" ");
        builder.append(bookPublisher);
        builder.append(" ");
        builder.append(bookYearPublished);
        builder.append(" ");
        builder.append(super.toString());
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (this.getClass() != o.getClass())) {
            return false;
        }
        Book book = (Book) o;
        if (!bookName.equals(book.getBookName())) {
            return false;
        }
        if (!bookAuthor.equals(book.getBookAuthor())) {
            return false;
        }
        if (!bookPublisher.equals(book.getBookPublisher())) {
            return false;
        }
        if (bookYearPublished != book.bookYearPublished) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode(){
        int hCode = bookId;
        final int prime = 31;
        hCode = prime * hCode + bookYearPublished;
        hCode = prime * hCode + (bookName != null ? bookName.hashCode() : 0);
        hCode = prime * hCode + (bookAuthor != null ? bookAuthor.hashCode() : 0);
        hCode = prime * hCode + (bookPublisher != null ? bookPublisher.hashCode() : 0);

        return hCode;
    }
}
