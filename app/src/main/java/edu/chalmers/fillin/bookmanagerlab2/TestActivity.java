package edu.chalmers.fillin.bookmanagerlab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BookManager bookManager = new SimpleBookManager();
        Log.d("TEST12345", Float.toString(bookManager.getMeanPrice()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
}
