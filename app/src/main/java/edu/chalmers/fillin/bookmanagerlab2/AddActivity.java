package edu.chalmers.fillin.bookmanagerlab2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final BookManager bookManager = SimpleBookManager.getBookManager();
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
                bookManager.createBook(author.getText().toString(), title.getText().toString(), Integer.parseInt(price.getText().toString()), isbn.getText().toString(), course.getText().toString());
                Log.d("TESTSTRING", "onClick: "+ bookManager.getAllBooks().get(bookManager.count()-1).getPrice());
            }

        });
    }

}
