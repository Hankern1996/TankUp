package com.example.hannahkern.tankup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by hannahkern on 28.02.18.
 */

public class MapFragment extends Fragment{

    private Roadtrip mRoadtrip;
    private EditText mGas;
    private Button mNextButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View v = inflater.inflate(R.layout.fragment_test, container, false);
            mGas = (EditText) v.findViewById(R.id.enter_price);
            mGas.setText( mRoadtrip.getGas());
            mGas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mRoadtrip.setGas(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });


        mNextButton = (Button) v.findViewById(R.id.calculate);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MapFragment.class);
                startActivity(i);
            }
        });


        Log.i("Hier",mGas.toString());


            return v;


        }

}
