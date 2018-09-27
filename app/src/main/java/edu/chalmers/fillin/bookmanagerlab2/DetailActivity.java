package edu.chalmers.fillin.bookmanagerlab2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Book book = SimpleBookManager.getInstance().getBook(getIntent().getExtras().getInt("item_pos"));

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
                if(id == R.id.edit_book){
            Intent i = new Intent(DetailActivity.this, EditActivity.class);
            i.putExtra("item_pos", getIntent().getExtras().getInt("item_pos"));
            startActivity(i);
        }
        if(id == R.id.delete_book) {
   AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Delete book");
            builder.setMessage("Are you sure you want to delete this?");
            builder.setPositiveButton("Confirm",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which == -1) {
                                Book book = SimpleBookManager.getInstance().getBook(getIntent().getExtras().getInt("item_pos"));
                                SimpleBookManager.getInstance().removeBook(book);
                                SimpleBookManager.getInstance().saveChanges();
                                startActivity(new Intent(DetailActivity.this, MainActivity.class));
                            }
                        }
                    });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }
}
