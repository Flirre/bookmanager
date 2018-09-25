package edu.chalmers.fillin.bookmanagerlab2;

import java.util.ArrayList;

public interface BookManager {
    public int count();
    public Book createBook();
    public Book createBook(String title, String author, int price, String isbn, String course);
    public Book getBook(int index);
    public ArrayList<Book> getAllBooks();
    public void removeBook(Book book);
    public void moveBook (int from, int to);
    public int getMinPrice();
    public int getMaxPrice();
    public float getMeanPrice();
    public int getTotalCost();
    public void saveChanges();
}
