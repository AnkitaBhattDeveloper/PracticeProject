package com.example.practiceproject.activity;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.practiceproject.R;
import com.example.practiceproject.adapter.ExpandableListViewAdapter;
import com.example.practiceproject.databinding.ActivityMainBinding;
import com.example.practiceproject.fragment.HomeFragment;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ExpandableListView expandableListView;
    private ExpandableListViewAdapter expandableListViewAdapter;

    private List<String> listDataGroup;

    private HashMap<String, List<String>> listDataChild;
    // private NavigationManager navigationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        expandableListView = findViewById(R.id.expandableListView);
        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        // initializing the views
        initViews();

        // initializing the listeners
        initListeners();

        // initializing the objects
        initObjects();

        // preparing list data
        initListData();


        //replace Fragment
        replaceFragment(new HomeFragment());

    }

    /*private void setUpDrawer() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("open");
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle("close");
                invalidateOptionsMenu();
            }
        };
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
       drawerLayout.addDrawerListener(actionBarDrawerToggle);


    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // int id = item.getItemId();
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {

        expandableListView = findViewById(R.id.expandableListView);
        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header, null, false);
        expandableListView.addHeaderView(listHeaderView);

    }

    /**
     * method to initialize the listeners
     */
    private void initListeners() {

        // ExpandableListView on child click listener
        expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            Toast.makeText(
                    getApplicationContext(),
                    listDataGroup.get(groupPosition)
                            + " : "
                            + listDataChild.get(
                            listDataGroup.get(groupPosition)).get(
                            childPosition), Toast.LENGTH_SHORT)
                    .show();
            drawerLayout.closeDrawer(GravityCompat.START);


            return false;
        });

        // ExpandableListView Group expanded listener
        expandableListView.setOnGroupExpandListener(groupPosition -> Toast.makeText(getApplicationContext(),
                listDataGroup.get(groupPosition) + " " + getString(R.string.setting),
                Toast.LENGTH_SHORT).show());

        // ExpandableListView Group collapsed listener
        expandableListView.setOnGroupCollapseListener(groupPosition -> Toast.makeText(getApplicationContext(),
                listDataGroup.get(groupPosition) + " " + getString(R.string.account),
                Toast.LENGTH_SHORT).show());

    }

    /**
     * method to initialize the objects
     */
    private void initObjects() {

        // initializing the list of groups
        listDataGroup = new ArrayList<>();

        // initializing the list of child
        listDataChild = new HashMap<>();

        // initializing the adapter object
        expandableListViewAdapter = new ExpandableListViewAdapter(this, listDataGroup, listDataChild);

        // setting list adapter
        expandableListView.setAdapter(expandableListViewAdapter);

    }

    /*
     * Preparing the list data
     *
     * Dummy Items
     */
    private void initListData() {


        // Adding group data
        listDataGroup.add(getString(R.string.laws));
        listDataGroup.add(getString(R.string.setting));
        listDataGroup.add(getString(R.string.account));

        // array of strings
        String[] array;

        // list of alcohol
        List<String> lawList = new ArrayList<>();
        array = getResources().getStringArray(R.array.string_laws);
        lawList.addAll(Arrays.asList(array));

        List<String> l1 = new ArrayList<>();
        /*if (l1.isEmpty()) {
            expandableListView.setGroupIndicator(null);
        }*/


        List<String> l2 = new ArrayList<>();
       /* if (l2.isEmpty()) {
            expandableListView.setGroupIndicator(null);
        }*/


        // Adding child data
        listDataChild.put(listDataGroup.get(0), lawList);
        listDataChild.put(listDataGroup.get(1), l1);
        listDataChild.put(listDataGroup.get(2), l2);

        expandableListViewAdapter.notifyDataSetChanged();
    }


    void replaceFragment(Fragment f) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, f);
        ft.commit();


    }

}
