package com.example.hannahkern.tankup;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by pauli on 07.03.2018.
 */

public class CalculatorLab {
    private static CalculatorLab sCalculatorLab;

    private List<Calculator> mCalculators;

    public static CalculatorLab get(Context context) {
        if (sCalculatorLab == null) {
            sCalculatorLab = new CalculatorLab(context);
        }
        return sCalculatorLab;
    }

    private CalculatorLab (Context context) {
        mCalculators = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Calculator calculator = new Calculator();
            mCalculators.add(calculator);
        }
    }

    public List<Calculator> getCalculators() {
        return mCalculators;
    }

    public Calculator getCalculator (UUID id) {
        for (Calculator calculator : mCalculators) {
            if (calculator.getId().equals(id)) {
                return calculator;
            }
        }

        return null;
    }
}
