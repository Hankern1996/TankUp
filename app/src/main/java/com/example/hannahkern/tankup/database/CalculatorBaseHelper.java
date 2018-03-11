package com.example.hannahkern.tankup.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hannahkern.tankup.database.CalculatorDbSchema.CalculatorTable;

/**
 * Created by pauli on 11.03.2018.
 */

public class CalculatorBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "calculatorBase.db";

    public CalculatorBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + CalculatorTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                CalculatorTable.Cols.UUID + ", " +
                CalculatorTable.Cols.DATE + ", " +
                CalculatorTable.Cols.GAS + ", " +
                CalculatorTable.Cols.KM +  ", " +
                CalculatorTable.Cols.PASSENGER + ", " +
                CalculatorTable.Cols.ERGEBNIS + ", " +
                CalculatorTable.Cols.TITLE +
                ")");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
