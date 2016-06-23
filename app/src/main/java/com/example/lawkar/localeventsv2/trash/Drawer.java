package com.example.lawkar.localeventsv2.trash;


import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.ListView;

public class Drawer {

    private static Drawer instance;

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String[] drawerItems;
    private ListView mDrawerList;


    protected Drawer(){

    }

    public static Drawer getInstance(){
        if(instance == null)
            instance = new Drawer();
        return instance;
    }
}
