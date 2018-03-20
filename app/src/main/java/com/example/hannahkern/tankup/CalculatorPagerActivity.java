package com.example.hannahkern.tankup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import java.util.List;
import java.util.UUID;

/**
 * Created by pauli on 07.03.2018.
 */

public class CalculatorPagerActivity extends BaseActivity implements  NavigationView.OnNavigationItemSelectedListener {
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

    private Calculator mCalculator;


    @Override
    public int getLayoutResource() {
        return R.layout.activity_main3;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



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




}