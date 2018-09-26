package edu.chalmers.fillin.bookmanagerlab2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final BookManager bookManager = SimpleBookManager.getInstance();
        setContentView(R.layout.activity_add);

        final TextView title = (TextView) findViewById(R.id.title);
        final TextView author = (TextView) findViewById(R.id.author);
        final TextView course = (TextView) findViewById(R.id.course);
        final TextView isbn = (TextView) findViewById(R.id.isbn);
        final TextView price = (TextView) findViewById(R.id.price);

        Button addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String titleText = title.getText().toString();
                final String authorText = author.getText().toString();
                final String courseText = course.getText().toString();
                final String isbnText = isbn.getText().toString();
                final int priceText;
                if (price.getText().toString().equals("")){
                    priceText = 0;
                }
                else {
                    priceText = Integer.parseInt(price.getText().toString());
                }
                bookManager.createBook(authorText, titleText, priceText, isbnText, courseText);
                for (int i=0;i < bookManager.count(); i++){
                    Log.d("TESTSTRING", "\nonClick: " + bookManager.getBook(i).getTitle());
                }
                bookManager.saveChanges();

            }

        });
    }

}
