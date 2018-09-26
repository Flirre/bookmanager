package edu.chalmers.fillin.bookmanagerlab2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BookManager bookmanager = new SimpleBookManager();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Book book = bookmanager.getBook(0);

        TextView title = (TextView) findViewById(R.id.title_holder);
        title.setText(book.getTitle());
        TextView author = (TextView) findViewById(R.id.author_holder);
        author.setText(book.getAuthor());
        TextView course = (TextView) findViewById(R.id.course_holder);
        course.setText(book.getCourse());
        TextView isbn = (TextView) findViewById(R.id.isbn_holder);
        isbn.setText(book.getIsbn());
        TextView price = (TextView) findViewById(R.id.price_holder);
        price.setText(Integer.toString(book.getPrice()));
    }

}
