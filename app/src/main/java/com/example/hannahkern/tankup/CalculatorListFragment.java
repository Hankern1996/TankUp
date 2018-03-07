package com.example.hannahkern.tankup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by pauli on 07.03.2018.
 */

public class CalculatorListFragment extends Fragment {
    private RecyclerView mCalculatorRecyclerView;
    private CalculatorAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator_list, container, false);

        mCalculatorRecyclerView = (RecyclerView) view
                .findViewById(R.id.calculator_recycler_view);
        mCalculatorRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        CalculatorLab calculatorLab = CalculatorLab.get(getActivity());
        List<Calculator> calculators = calculatorLab.getCalculators();

        mAdapter = new CalculatorAdapter(calculators);
        mCalculatorRecyclerView.setAdapter(mAdapter);
    }

    private class CalculatorHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        private Calculator mCalculator;
        private TextView mKmTextView;
        private TextView mDateTextView;

        public CalculatorHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_calculator, parent, false));
            itemView.setOnClickListener(this);

            mDateTextView = (TextView) itemView.findViewById(R.id.calculator_date);
            mKmTextView = (TextView) itemView.findViewById(R.id.calculator_price);
        }

        public void bind(Calculator calculator) {
            mCalculator = calculator;
            mDateTextView.setText(mCalculator.getDate().toString());
            mKmTextView.setText(mCalculator.getKm());
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(),
                     " clicked!", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    private class CalculatorAdapter extends RecyclerView.Adapter<CalculatorHolder> {

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
    }
}
