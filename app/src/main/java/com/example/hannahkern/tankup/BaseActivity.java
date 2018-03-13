package com.example.hannahkern.tankup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.facebook.CallbackManager;

/**
 * Created by hannahkern on 10.03.18.
 */

public abstract class BaseActivity extends AppCompatActivity {




    android.support.v7.widget.Toolbar toolbar;
    int drawerLayout = 0;
    int navView = 0;
    int mToolbar= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        configureToolbar();



        if (getLayoutResource()== R.layout.activity_main2){

            drawerLayout = R.id.drawer_layout2;
            navView = R.id.nav_view2;

        }
        else if (getLayoutResource() == R.layout.activity_main3){
            drawerLayout = R.id.drawer_layout3;
            navView = R.id.nav_view3;
        }

        else if (getLayoutResource() == R.layout.activity_main4){
            drawerLayout = R.id.drawer_layout4;
            navView = R.id.nav_view4;
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,

                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(navView);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);



    }

    protected abstract int getLayoutResource();

    private void configureToolbar() {

        if (getLayoutResource() == R.layout.activity_main2){
            mToolbar = R.id.toolbar2;
        }
        else if (getLayoutResource() == R.layout.activity_main3){
            mToolbar = R.id.toolbar3;
        }
        else if (getLayoutResource() == R.layout.activity_main4){
            mToolbar = R.id.toolbar4;
        }

        toolbar = (android.support.v7.widget.Toolbar) findViewById(mToolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }



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

    @SuppressWarnings("StatementWithEmptyBody")
    public boolean onNavigationItemSelected(MenuItem item) {

        //onNavigationItemSelected(item);

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.calculator) {

            Calculator calculator = new Calculator();
            CalculatorLab.get(getApplicationContext()).addCalculator(calculator);

            Intent intent = CalculatorPagerActivity
                    .newIntent(getApplicationContext(), calculator.getId());
            startActivity(intent);

           /*CalculatorFragment calculatorFragment = new CalculatorFragment();
           getSupportFragmentManager().beginTransaction()
                   .replace(R.id.content_frame,calculatorFragment)
                   .addToBackStack(null)
                   .commit();*/

        } else if (id == R.id.nav_map) {

            Intent intent1 = new Intent(BaseActivity.this, MapsActivity.class);
            BaseActivity.this.startActivity(intent1);



           /*com.example.hannahkern.tankup.MapFragment mMapFragment = new com.example.hannahkern.tankup.MapFragment();
           getSupportFragmentManager().beginTransaction()
                   .replace(R.id.content_frame, mMapFragment)
                   .addToBackStack(null)
                   .commit();*/

        } else if (id == R.id.nav_share) {


        } else if (id == R.id.nav_trips) {
            if (getLayoutResource()== R.layout.activity_main2){
                CalculatorListFragment calculatorFragment = new CalculatorListFragment();

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.drawer_layout2,calculatorFragment)
                        .addToBackStack(null)
                        .commit();
            }
            else if (getLayoutResource()==R.layout.activity_main3){
                CalculatorListFragment calculatorFragment = new CalculatorListFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.drawer_layout3,calculatorFragment)
                        .addToBackStack(null)
                        .commit();
            }

            else if (getLayoutResource()==R.layout.activity_main4){
                CalculatorListFragment calculatorFragment = new CalculatorListFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.drawer_layout4,calculatorFragment)
                        .addToBackStack(null)
                        .commit();

            }


        }

        if (getLayoutResource()== R.layout.activity_main2){

            drawerLayout = R.id.drawer_layout2;
            navView = R.id.nav_view2;

        }
        else if (getLayoutResource() == R.layout.activity_main3){
            drawerLayout = R.id.drawer_layout3;
            navView = R.id.nav_view3;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    public void onBackPressed() {

        if (getLayoutResource()== R.layout.activity_main2){

            drawerLayout = R.id.drawer_layout2;

        }
        else if (getLayoutResource() == R.layout.activity_main3){
            drawerLayout = R.id.drawer_layout3;
        }

        else if (getLayoutResource() == R.layout.activity_main4){
            drawerLayout = R.id.drawer_layout4;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



}
