package com.example.hannahkern.tankup;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by hannahkern on 15.03.18.
 */

public class ListActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    public RecyclerView mCalculatorRecyclerView;
    public CalculatorAdapter mAdapter;


    @Override
    public int getLayoutResource() {
        return R.layout.activity_main4;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCalculatorRecyclerView = (RecyclerView) findViewById(R.id.calculator_recycler_view);
        mCalculatorRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateUI();


    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI(){

        CalculatorLab calculatorLab = CalculatorLab.get(this);
        List<Calculator> calculators = calculatorLab.getCalculators();


        if (mAdapter == null) {
            mAdapter = new CalculatorAdapter(calculators);
            mCalculatorRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setCalculators(calculators);
            mAdapter.notifyDataSetChanged();
        }

    }

    public class CalculatorHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Calculator mCalculator;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mImageView;

        public CalculatorHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_calculator, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.calculator_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.calculator_date);

        }

        public void bind(Calculator calculator) {
            mCalculator = calculator;
            Date date = mCalculator.getDate();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            mDateTextView.setText(simpleDateFormat.format(date));
            mTitleTextView.setText( mCalculator.getTitle());
        }


        @Override
        public void onClick(View view) {
            Intent intent = CalculatorPagerActivity.newIntent(ListActivity.this, mCalculator.getId());
            startActivity(intent);
        }

    }

    public class CalculatorAdapter extends RecyclerView.Adapter<CalculatorHolder> {

        private List<Calculator> mCalculators;

        public CalculatorAdapter(List<Calculator> calculators) {
            mCalculators = calculators;
        }

        @Override
        public CalculatorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(ListActivity.this);

            return new CalculatorHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(CalculatorHolder holder, int position) {
            Calculator calculator = mCalculators.get(position);
            holder.bind(calculator);
        }

        @Override
        public int getItemCount() {
            return mCalculators.size();
        }

        public void setCalculators(List<Calculator> calculators) {
            mCalculators = calculators;
        }
    }


}
