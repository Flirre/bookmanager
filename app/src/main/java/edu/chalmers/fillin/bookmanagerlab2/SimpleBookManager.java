package edu.chalmers.fillin.bookmanagerlab2;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class SimpleBookManager implements BookManager {
    private ArrayList<Book> bookList;


    public static final Comparator<Book> DESCENDING_PRICE_COMPARATOR = new Comparator<Book>() {
        // Overriding the compare method to sort the age
        public int compare(Book b0, Book b1) {
            return b0.getPrice() - b1.getPrice();
        }
    };

    public static final Comparator<Book> ASCENDING_PRICE_COMPARATOR = new Comparator<Book>() {
        // Overriding the compare method to sort the age
        public int compare(Book b0, Book b1) {
            return b1.getPrice() - b0.getPrice();
        }
    };

    private static SimpleBookManager instance;

    public static SimpleBookManager getInstance() {
        if (instance == null)
            instance = new SimpleBookManager();
        return instance;
    }

    public SimpleBookManager(){
        bookList = new ArrayList<Book>();
        Gson gson = new GsonBuilder().create();
        Book[] bookListArray = gson.fromJson(PreferenceHelper.getBooks(), Book[].class);
        bookList = new ArrayList<Book>(Arrays.asList(bookListArray));
        Log.d("sbm123", "SimpleBookManager: "+ getAllBooks().size());
    };

    @Override
    public int count() {
        try {return getAllBooks().size();}
        catch (Exception e) {
            System.out.print("No List found");
        }
        return 0;
    }

    @Override
    public Book createBook() {
        Book book = new Book("", "", 0, "", "");
        getAllBooks().add(book);
        return book;
    }
    @Override
    public Book createBook(String author, String title, int price, String isbn, String course) {
        Book book = new Book(author, title, price, isbn, course);
        getAllBooks().add(book);
        return book;
    }

    @Override
    public Book getBook(int index) throws IndexOutOfBoundsException {
        try {
            return getAllBooks().get(index);
        }
        catch (Exception e) {
            System.out.print("Book index out of bounds.");
        }
        return null;
    }

    @Override
    public ArrayList<Book> getAllBooks() {
        return bookList;
    }

    @Override
    public void removeBook(Book book) throws NullPointerException {
        try {
            getAllBooks().remove(book);
        }
        catch (Exception e) {
            System.out.print("Book not found in list.");
        }
    }

    @Override
    public void moveBook(int from, int to) throws IndexOutOfBoundsException {
        try {
            Book book = bookList.get(from);
            bookList.remove(from);
            bookList.add(to, book);
        }
        catch (Exception e) {
            System.out.print("Book index not found.");
        }
    }

    @Override
    public int getMinPrice() throws NullPointerException {
        try {
            Collections.sort(getAllBooks(), DESCENDING_PRICE_COMPARATOR);
            return getAllBooks().get(0).getPrice();
        }
        catch (Exception e){
            System.out.print("No list found");
        }
        return 0;

    }

    @Override
    public int getMaxPrice() throws NullPointerException {
        try {
            Collections.sort(getAllBooks(), ASCENDING_PRICE_COMPARATOR);
            return getAllBooks().get(0).getPrice();
        }
        catch (Exception e) {
            System.out.print("No list found");
        }
        return 0;
    }

    @Override
    public float getMeanPrice() throws NullPointerException{
        try {
            return getTotalCost() / getAllBooks().size();
        }
        catch (Exception e) {
            System.out.print("No list found");
        }
        return 0;
    }

    @Override
    public int getTotalCost() throws NullPointerException{
        int totalCost = 0;
        try {
            for (int i = 0; i < getAllBooks().size(); i++) {
                totalCost += getBook(i).getPrice();
            }
            return totalCost;
        }
        catch (Exception e){
            System.out.print("No list found");
        }
        return 0;
    }

    @Override
    public void saveChanges() {
        Gson gson = new GsonBuilder().create();
        String jsonOutput = gson.toJson(getAllBooks().toArray());
        PreferenceHelper.saveBooks(jsonOutput);
        Log.d("gsontag", ("output :" + PreferenceHelper.getBooks()));



    }
}