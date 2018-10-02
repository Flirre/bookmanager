package edu.chalmers.fillin.bookmanagerlab2;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        final BookManager bookManager = SimpleBookManager.getInstance();
        final Book book = bookManager.getBook(getIntent().getExtras().getInt("item_pos"));
        final TextView title = (TextView) findViewById(R.id.title);
        final TextView author = (TextView) findViewById(R.id.author);
        final TextView course = (TextView) findViewById(R.id.course);
        final TextView publisher = (TextView) findViewById(R.id.publisher);
        final TextView isbn = (TextView) findViewById(R.id.isbn);
        final TextView price = (TextView) findViewById(R.id.price);

        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        course.setText(book.getCourse());
        publisher.setText(book.getPublisher());
        isbn.setText(book.getIsbn());
        price.setText(Integer.toString(book.getPrice()));

        Button addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String titleText = title.getText().toString();
                final String authorText = author.getText().toString();
                final String courseText = course.getText().toString();
                final String publisherText = publisher.getText().toString();
                final String isbnText = isbn.getText().toString();
                final int priceText;
                if (price.getText().toString().equals("")){
                    priceText = 0;
                }
                else {
                    priceText = Integer.parseInt(price.getText().toString());
                }

                if (titleText.equals("")){
                    Snackbar snackbar = Snackbar.make(view, "A title is required.", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                else {
                    book.setAuthor(authorText);
                    book.setTitle(titleText);
                    book.setPrice(priceText);
                    book.setIsbn(isbnText);
                    book.setCourse(courseText);
                    book.setPublisher(publisherText);
                    bookManager.saveChanges();
                    startActivity(new Intent(EditActivity.this, MainActivity.class));
                }
            }

        });
    }
}
