package com.example.hannahkern.tankup.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.hannahkern.tankup.Calculator;

import java.util.Date;
import java.util.UUID;

/**
 * Created by pauli on 11.03.2018.
 */

public class CalculatorCursorWrapper extends CursorWrapper {
    public CalculatorCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Calculator getCalculator() {
        String uuidString = getString(getColumnIndex(CalculatorDbSchema.CalculatorTable.Cols.UUID));
        long date = getLong(getColumnIndex(CalculatorDbSchema.CalculatorTable.Cols.DATE));
        String gas = getString(getColumnIndex(CalculatorDbSchema.CalculatorTable.Cols.GAS));
        String consumption = getString(getColumnIndex(CalculatorDbSchema.CalculatorTable.Cols.CONSUMPTION));
        String km = getString(getColumnIndex(CalculatorDbSchema.CalculatorTable.Cols.KM));
        String passenger = getString(getColumnIndex(CalculatorDbSchema.CalculatorTable.Cols.PASSENGER));
        String ergebnis = getString(getColumnIndex(CalculatorDbSchema.CalculatorTable.Cols.ERGEBNIS));
        String title = getString(getColumnIndex(CalculatorDbSchema.CalculatorTable.Cols.TITLE));

        Calculator calculator = new Calculator(UUID.fromString(uuidString));
        calculator.setDate(new Date(date));
        calculator.setGas(gas);
        calculator.setConsumption(consumption);
        calculator.setKm(km);
        calculator.setPassenger(passenger);
        calculator.setErgebnis(ergebnis);
        calculator.setTitle(title);

        return calculator;
    }


}
