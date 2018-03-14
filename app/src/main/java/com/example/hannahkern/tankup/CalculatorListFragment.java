package com.example.hannahkern.tankup;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPresenter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by pauli on 07.03.2018.
 */

public class CalculatorListFragment extends Fragment {
    public RecyclerView mCalculatorRecyclerView;
    public CalculatorAdapter mAdapter;
    View view;

    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main4, container, false);

        final android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) view.findViewById(R.id.toolbar4);
        // ((AppCompatActivity)getActivity()).getSupportActionBar();
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

       // toolbar.setNavigationIcon(R.drawable.test);
        toolbar.inflateMenu(R.menu.activity_main_drawer);
        setHasOptionsMenu(true);




        toolbar.setOnClickListener(new View.OnClickListener() {

                                                 @Override
                                                 public void onClick(View v) {
               /*DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout4);
               drawer.openDrawer(Gravity.START);*/

                                                     Intent intent1 = new Intent(getActivity(), MainActivity.class);
                                                     String openNavigation = "Open navigation drawer";
                                                     intent1.putExtra(openNavigation, true);
                                                     startActivity(intent1);

                                                     //toolbar.inflateMenu(R.menu.activity_main_drawer);

                                                     //MenuInflater inflater1 = null;
                                                     //inflater1.inflate(R.menu.activity_main_drawer, menu);
                                                     //setHasOptionsMenu(true);
                                                 }
                                             }

        );

        mCalculatorRecyclerView = (RecyclerView) view
                .findViewById(R.id.calculator_recycler_view);
        mCalculatorRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

   /*public void onCreateOptionsMenu( Menu menu, MenuInflater inflater){
       super.onCreateOptionsMenu(menu, inflater);
       inflater.inflate(R.menu.activity_main_drawer, menu);
   }*/

    @SuppressWarnings("StatementWithEmptyBody")

    public boolean onOptionsItemSelected(MenuItem item) {



        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.calculaterr) {

            Calculator calculator = new Calculator();
            Intent intent = new Intent(getActivity(), CalculatorLab.class);

            CalculatorLab.get(getActivity().getApplicationContext()).addCalculator(calculator);

            Intent intent1 = CalculatorPagerActivity
                    .newIntent(getActivity().getApplicationContext(), calculator.getId());
            startActivity(intent1);

          /*CalculatorFragment calculatorFragment = new CalculatorFragment();
          getSupportFragmentManager().beginTransaction()
                  .replace(R.id.content_frame,calculatorFragment)
                  .addToBackStack(null)
                  .commit();*/

        } else if (id == R.id.nav_map) {

            Intent myIntent = new Intent(getActivity(), MapsActivity.class);
            getActivity().startActivity(myIntent);

          /*com.example.hannahkern.tankup.MapFragment mMapFragment = new com.example.hannahkern.tankup.MapFragment();
          getSupportFragmentManager().beginTransaction()
                  .replace(R.id.content_frame, mMapFragment)
                  .addToBackStack(null)
                  .commit();*/

        } else if (id == R.id.nav_share) {
            Intent myIntent = new Intent(getActivity(), CalculatorListActivity.class);
            getActivity().startActivity(myIntent);

        } else if (id == R.id.nav_trips) {
          /* CalculatorListFragment calculatorFragment = new CalculatorListFragment();
           getSupportFragmentManager().beginTransaction()
                   .replace(R.id.content_frame,calculatorFragment)
                   .addToBackStack(null)
                   .commit();*/
        }


        DrawerLayout drawer = (DrawerLayout) view.findViewById(R.id.drawer_layout4);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        CalculatorLab calculatorLab = CalculatorLab.get(getActivity());
        List<Calculator> calculators = calculatorLab.getCalculators();

        if (mAdapter == null) {
            mAdapter = new CalculatorAdapter(calculators);
            mCalculatorRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setCalculators(calculators);
            mAdapter.notifyDataSetChanged();
        }
    }

    public class CalculatorHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        private Calculator mCalculator;
        private TextView mTitleTextView;
        private TextView mDateTextView;

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
            Intent intent = CalculatorPagerActivity.newIntent(getActivity(), mCalculator.getId());
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
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

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