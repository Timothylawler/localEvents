package com.example.lawkar.localeventsv2;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.lawkar.localeventsv2.adapter.EventRecyclerAdapter;
import com.example.lawkar.localeventsv2.model.EventModel;

import java.util.ArrayList;
import java.util.List;

public class EventsActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private List<EventModel> eventList;
    private RecyclerView eventRecyclerView;
    private AppBarLayout appBarLayout;
    private TabLayout tabLayout;
    private ImageView topContentImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.event_toolbar);
        //  Erase the title
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        appBarLayout = (AppBarLayout) findViewById(R.id.event_app_bar_layout);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            appBarLayout.setElevation(16);

        tabLayout = (TabLayout) findViewById(R.id.event_tab_layout);
        setupTabs();

        topContentImage = (ImageView) findViewById(R.id.event_toolbar_image);
        topContentImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.event_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Coming soon..", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        eventRecyclerView = ( RecyclerView ) findViewById(R.id.event_recycler_view);
        setupRecyclerView();
    }

    private void setupTabs() {
        tabLayout.addTab(tabLayout.newTab().setText("Local"));
        tabLayout.addTab(tabLayout.newTab().setText("Custom"));
    }

    private void setupRecyclerView() {
        eventList = new ArrayList<>();
        eventList = dummyList();

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        EventRecyclerAdapter adapter = new EventRecyclerAdapter(eventList, this);

        eventRecyclerView.setLayoutManager(llm);
        eventRecyclerView.setAdapter(adapter);
    }

    private List<EventModel> dummyList() {
        for(int i = 0; i<15; i++)
            eventList.add(new EventModel("Disco " + i));
        return eventList;
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * TABS!
     */

    @Override
    public void onTabSelected(Tab tab) {

    }

    @Override
    public void onTabUnselected(Tab tab) {

    }

    @Override
    public void onTabReselected(Tab tab) {

    }

    /**
     * END OF TABS
     */
}
