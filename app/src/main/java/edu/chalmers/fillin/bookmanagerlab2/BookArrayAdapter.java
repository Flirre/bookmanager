package edu.chalmers.fillin.bookmanagerlab2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BookArrayAdapter extends ArrayAdapter<Book> {

    private Context bContext;
    private List<Book> bookList = new ArrayList<Book>();

    public BookArrayAdapter(@NonNull Context context, @NonNull List<Book> books) {
        super(context, 0, books);
        bContext = context;
        bookList = books;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View listitem = convertView;
        if (listitem == null) {
            listitem = LayoutInflater.from(bContext).inflate(R.layout.listitem_book, parent, false);
        }
        Book currentBook = bookList.get(position);
        TextView bookName = (TextView) listitem.findViewById(R.id.bookName);
        bookName.setText(currentBook.getTitle());
        TextView bookAuthor = (TextView) listitem.findViewById(R.id.bookAuthor);
        bookAuthor.setText(currentBook.getAuthor());
        return listitem;
    }
}

