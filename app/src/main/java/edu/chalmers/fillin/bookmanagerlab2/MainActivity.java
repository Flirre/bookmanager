package edu.chalmers.fillin.bookmanagerlab2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    public static SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getPreferences(MODE_PRIVATE);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
/*        if (id == R.id.action_settings) {
            return true;
        }*/
        if(id == R.id.create_new){
            startActivity(new Intent(MainActivity.this, AddActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class SummaryFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        public SummaryFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static SummaryFragment newInstance() {
            SummaryFragment fragment = new SummaryFragment();
            return fragment;
        }

        public void updateSummary(View view){
            TextView nrbooks = (TextView) view.findViewById(R.id.totalnr_holder);
            Log.d("textbooks", "onCreate: "+nrbooks.getText().toString());
            nrbooks.setText(Integer.toString(SimpleBookManager.getInstance().count()));
            TextView least = (TextView) view.findViewById(R.id.least_holder);
            least.setText(Integer.toString(SimpleBookManager.getInstance().getMinPrice()) + " SEK");
            TextView most = (TextView) view.findViewById(R.id.most_holder);
            most.setText(Integer.toString(SimpleBookManager.getInstance().getMaxPrice()) + " SEK");
            TextView average = (TextView) view.findViewById(R.id.average_holder);
            average.setText(Float.toString(SimpleBookManager.getInstance().getMeanPrice()) + " SEK");
            TextView total= (TextView) view.findViewById(R.id.total_holder);
            total.setText(Integer.toString(SimpleBookManager.getInstance().getTotalCost()) + " SEK");
        }

        @Override
        public void onResume() {
            super.onResume();
            updateSummary(getView());

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_summary, container, false);
            updateSummary(view);
            return view;
        }
    }

    public static class CollectionFragment extends Fragment {

        /* The fragment argument representing the section number for this fragment.
         */
        public CollectionFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static CollectionFragment newInstance() {
            CollectionFragment fragment = new CollectionFragment();
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_collection, container, false);
            ListView bookListView = view.findViewById(R.id.bookList);
            ArrayAdapter<Book> bookArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, SimpleBookManager.getInstance().getAllBooks());
            bookListView.setAdapter(bookArrayAdapter);
            bookListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent(view.getContext(), DetailActivity.class);
                    i.putExtra("item_pos", position);
                    startActivity(i);
                }
            });
            return view;
        }
    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if (position==1){
                return SummaryFragment.newInstance();
            }
            return CollectionFragment.newInstance();
        }


        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
    }
}
