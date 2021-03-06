package com.example.hannahkern.tankup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements  NavigationView.OnNavigationItemSelectedListener {

    private static final String EXTRA_OPEN_NAVIGATION =
            "com.example.hannahkern.tankup.open_navigation";

    private String mString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mString = getIntent().getStringExtra(EXTRA_OPEN_NAVIGATION);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView mText = (TextView)findViewById(R.id.text4);
        mText.setClickable(true);
        mText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calculator calculator = new Calculator();
                CalculatorLab.get(getApplicationContext()).addCalculator(calculator);

                Intent intent = CalculatorPagerActivity
                        .newIntent(getApplicationContext(), calculator.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")

    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.main){
            Intent intent1 = new Intent(MainActivity.this, MainActivity.class);
            MainActivity.this.startActivity(intent1);
        }

        else if (id == R.id.calculaterr) {
            Context context = getApplicationContext();
            CharSequence text = "Now you have a new item in the >My trips< list!";
            int duration = Toast.LENGTH_LONG;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            Calculator calculator = new Calculator();

            CalculatorLab.get(getApplicationContext()).addCalculator(calculator);

            Intent intent = CalculatorPagerActivity
                    .newIntent(getApplicationContext(), calculator.getId());
            startActivity(intent);

        } else if (id == R.id.nav_map) {
            Intent intent1 = new Intent(MainActivity.this, MapsActivity.class);
            MainActivity.this.startActivity(intent1);

        } else if (id == R.id.nav_share) {
            Intent intent1 = new Intent(MainActivity.this, BlogActivity.class);
            MainActivity.this.startActivity(intent1);

        } else if (id == R.id.nav_trips) {
            Intent intent1 = new Intent(MainActivity.this, ListActivity.class);
            MainActivity.this.startActivity(intent1);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}