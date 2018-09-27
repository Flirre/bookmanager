package edu.chalmers.fillin.bookmanagerlab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BookManager bookmanager = SimpleBookManager.getInstance();
        setContentView(R.layout.activity_summary);

        TextView nrbooks = (TextView) findViewById(R.id.totalnr_holder);
        nrbooks.setText(Integer.toString(bookmanager.count()));
        TextView least = (TextView) findViewById(R.id.least_holder);
        least.setText(Integer.toString(bookmanager.getMinPrice()) + " SEK");
        TextView most = (TextView) findViewById(R.id.most_holder);
        most.setText(Integer.toString(bookmanager.getMaxPrice()) + " SEK");
        TextView average = (TextView) findViewById(R.id.average_holder);
        average.setText(Float.toString(bookmanager.getMeanPrice()) + " SEK");
        TextView total= (TextView) findViewById(R.id.total_holder);
        total.setText(Integer.toString(bookmanager.getTotalCost()) + " SEK");
    }
}
