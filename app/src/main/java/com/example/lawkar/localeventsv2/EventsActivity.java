package com.example.lawkar.localeventsv2;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.lawkar.localeventsv2.adapter.EventRecyclerAdapter;
import com.example.lawkar.localeventsv2.model.EventModel;

import java.util.ArrayList;
import java.util.List;

public class EventsActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener
        , ListView.OnItemClickListener {

    private List<EventModel> eventList;
    private RecyclerView eventRecyclerView;
    private AppBarLayout appBarLayout;
    private TabLayout tabLayout;
    private ImageView topContentImage;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String[] drawerItems;
    private ListView mDrawerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.event_toolbar);
        //  Erase the title
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        setupDrawer();


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

    /**
     * Sets up the navigation drawer
     */
    private void setupDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.event_drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.event_left_drawer_list);

        //  Attach a click listener to the list
        mDrawerList.setOnItemClickListener(this);

        dummyArray();
        //  Set adapter for the list
        mDrawerList.setAdapter(new ArrayAdapter<String>(this
                , android.R.layout.simple_list_item_1
                , drawerItems));

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout
                ,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //  Set title
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //  set title
                invalidateOptionsMenu();

            }
        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

    }

    /**
     * Called whenever we call invalidateOptionsMenu();
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //  If drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);

        mDrawerToggle.syncState();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void dummyArray() {
        drawerItems = new String[]{
                "Events" , "Asdf", "gjsgl", "etc"
        };
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

        if(mDrawerToggle.onOptionsItemSelected(item))
            return true;

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

    /**
     * Handles clicks in the listview
     * @param adapterView
     * @param view  View that the click event happened on
     * @param i     index - Position of the view
     * @param l
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }


}
