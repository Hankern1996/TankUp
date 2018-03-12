package com.example.hannahkern.tankup;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import java.util.List;
import java.util.UUID;

/**
 * Created by pauli on 07.03.2018.
 */

public class CalculatorListActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {






    public int getLayoutResource() {

        return R.layout.activity_main4;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Fragment fragment = new CalculatorListFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();



        if (getLayoutResource()== R.layout.activity_main2){
            transaction.replace(R.id.drawer_layout2,fragment);
        }

        else if (getLayoutResource()== R.layout.activity_main3){
            transaction.replace(R.id.drawer_layout3,fragment);
        }


        transaction.addToBackStack(null);
        transaction.commit(); //requires adapter





    }



    protected Fragment createFragment() {
        return new CalculatorListFragment();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
