package com.example.hannahkern.tankup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by hannahkern on 28.02.18.
 */

public class CalculatorFragment extends Fragment{

    private Calculator mCalculator;
    private EditText mGas;
    private EditText mKm;
    private TextView mResultText;
    private Button mCalculateButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_calculator, container, false);

        mGas = (EditText) v.findViewById(R.id.enter_price);
            mGas.setText( mCalculator.getGas());
            mGas.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    // This space intentionally left blank
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mCalculator.setGas(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {
                    // This one too
                }
            });


            mKm = (EditText) v.findViewById(R.id.enter_distance);
            mKm.setText(mCalculator.getKm());
            mKm.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    // This space intentionally left blank
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mCalculator.setKm(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {
                    //This one too
                }
            });

            mCalculateButton = (Button) v.findViewById(R.id.calculateButton);
            mCalculateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mResultText = (TextView) v.findViewById(R.id.result);

                // Gets the two EditText controls' Editable values
                Editable editableGas = mGas.getText(),
                        editableKm = mKm.getText();

                // Initializes the double values and result
                double value1 = 0.0,
                        value2 = 0.0,
                        result;

                // If the Editable values are not null, obtains their double values by parsing
                if (editableGas != null)
                    value1 = Double.parseDouble(editableGas.toString());

                if (editableKm != null)
                    value2 = Double.parseDouble(editableKm.toString());

                // Calculates the result
                result = value1 * value2;

                // Displays the calculated result
                mResultText.setText(String.valueOf(result));
                }
            });

            return v;

        }

}


