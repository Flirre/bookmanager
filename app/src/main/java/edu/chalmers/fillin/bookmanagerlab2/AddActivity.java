package edu.chalmers.fillin.bookmanagerlab2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final BookManager bookManager = SimpleBookManager.getInstance();
        setContentView(R.layout.activity_add);

        final TextView title = (TextView) findViewById(R.id.title);
        final TextView author = (TextView) findViewById(R.id.author);
        final TextView course = (TextView) findViewById(R.id.publisher);
        final TextView isbn = (TextView) findViewById(R.id.isbn);
        final TextView price = (TextView) findViewById(R.id.price);
        final TextView publisher = (TextView) findViewById(R.id.publisher);
        isbn.setText("9780465050659");
        final ArrayList<String> url = new ArrayList<>();

        Button fetchButton = (Button) findViewById(R.id.fetchButton);
        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fetchUrl = "http://openlibrary.org/api/books";
                AsyncHttpClient client = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                params.put("bibkeys", "ISBN:"+isbn.getText().toString());
                params.put("jscmd", "data");
                params.put("format", "json");
                client.get(fetchUrl, params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        // Root JSON in response is an dictionary i.e { "data : [ ... ] }
                        // Handle resulting parsed JSON response here
                        Gson gson = new GsonBuilder().create();
                        try {
                            JSONObject bookInfo = response.getJSONObject("ISBN:"+isbn.getText().toString());
                            String bookTitle = bookInfo.getString("title");
                            String bookAuthor = bookInfo.getJSONArray("authors").getJSONObject(0).getString("name");
                            String bookPublisher = bookInfo.getJSONArray("publishers").getJSONObject(0).getString("name");
                            String bookCoverUrl = bookInfo.getJSONObject("cover").getString("small");
                            title.setText(bookTitle);
                            author.setText(bookAuthor);
                            publisher.setText(bookPublisher);
                            url.add(bookCoverUrl);


                            Log.d("getReqTest", "getRequest:t "+ bookTitle);
                            Log.d("getReqTest", "getRequest:a "+ bookAuthor);
                            Log.d("getReqTest", "getRequest:p "+ bookPublisher);
                            Log.d("getReqTest", "getRequest:c "+ bookCoverUrl);
                        } catch (JSONException e) {
                            Log.d("getReqTest", "getRequest: fk");
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)

                        Log.d("getReqTest", res);
                    }
                });

            }
        });

        Button addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String titleText = title.getText().toString();
                final String authorText = author.getText().toString();
                final String courseText = course.getText().toString();
                final String isbnText = isbn.getText().toString();
                final String publisherText = publisher.getText().toString();
                Log.d("saveTest", "onClick: "+url.get(0));
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
                    bookManager.createBook(authorText, titleText, priceText, isbnText, courseText, url.get(0), publisherText);
                    bookManager.saveChanges();
                    startActivity(new Intent(AddActivity.this, MainActivity.class));
                }
            }

        });
    }


}
