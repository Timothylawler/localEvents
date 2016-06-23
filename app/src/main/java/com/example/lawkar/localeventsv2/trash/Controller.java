package com.example.lawkar.localeventsv2.trash;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.lawkar.localeventsv2.R;

/**
 * First to be created in the app
 *  Sets up a navigation drawer
 */
public class Controller extends AppCompatActivity implements ListView.OnItemClickListener{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String[] drawerItems;
    private ListView mDrawerList;

    private FrameLayout contentFrame;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.navigation_drawer);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer_list);

        contentFrame = (FrameLayout) findViewById(R.id.content_frame);

        setupDrawer();


    }

    /**
     * Sets an adapter for the list
     */
    private void setupDrawer() {
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
     * Fills the array with dummyitems
     */
    private void dummyArray() {
        drawerItems = new String[]{"Events", "asd", "efg", "bgh"};
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mDrawerToggle.onOptionsItemSelected(item))
            return true;

        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }


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
