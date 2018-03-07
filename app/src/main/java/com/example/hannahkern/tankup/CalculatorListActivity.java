package com.example.hannahkern.tankup;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by pauli on 07.03.2018.
 */

public class CalculatorListActivity extends AppCompatActivity {


    protected Fragment createFragment() {
        return new CalculatorListFragment();
    }
}
