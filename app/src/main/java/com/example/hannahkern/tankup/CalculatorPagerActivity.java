package com.example.hannahkern.tankup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;
import java.util.UUID;

/**
 * Created by pauli on 07.03.2018.
 */

public class CalculatorPagerActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {
    public static final String EXTRA_CALCULATOR_ID =
            "com.example.hannahkern.tankup.calculator_id";

    private ViewPager mViewPager;                                   //ViewPager for a ViewHierarchy
    private List<Calculator> mCalculators;

    protected Fragment createFragment() {
        UUID calculatorId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_CALCULATOR_ID);
        return CalculatorFragment.newInstance(calculatorId);
    }

    public static Intent newIntent(Context packageContext, UUID calculatorId) {
        Intent intent = new Intent(packageContext, CalculatorPagerActivity.class);
        intent.putExtra(EXTRA_CALCULATOR_ID, calculatorId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //set view



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout3);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view3);
        navigationView.setNavigationItemSelectedListener(this);


        UUID calculatorId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_CALCULATOR_ID);

        mViewPager = (ViewPager) findViewById(R.id.calculator_view_pager);

        mCalculators = CalculatorLab.get(this).getCalculators();
        FragmentManager fragmentManager = getSupportFragmentManager();          //needs support
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {  //requires adapter

            @Override
            public Fragment getItem(int position) {
                Calculator calculator = mCalculators.get(position);
                return CalculatorFragment.newInstance(calculator.getId());
            }

            @Override
            public int getCount() {
                return mCalculators.size();
            }          //returns number of items
        });

        for (int i = 0; i < mCalculators.size(); i++) {                    //find index to display via looping
            if (mCalculators.get(i).getId().equals(calculatorId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


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

            Intent intent1 = new Intent(CalculatorPagerActivity.this, MapsActivity.class);
            CalculatorPagerActivity.this.startActivity(intent1);

           /*com.example.hannahkern.tankup.MapFragment mMapFragment = new com.example.hannahkern.tankup.MapFragment();
           getSupportFragmentManager().beginTransaction()
                   .replace(R.id.content_frame, mMapFragment)
                   .addToBackStack(null)
                   .commit();*/

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_trips) {
            CalculatorListFragment calculatorFragment = new CalculatorListFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.calculator_view_pager,calculatorFragment)
                    .addToBackStack(null)
                    .commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout3);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
