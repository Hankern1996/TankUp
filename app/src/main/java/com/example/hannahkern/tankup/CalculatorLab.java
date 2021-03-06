package com.example.hannahkern.tankup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hannahkern.tankup.database.CalculatorBaseHelper;
import com.example.hannahkern.tankup.database.CalculatorCursorWrapper;
import com.example.hannahkern.tankup.database.CalculatorDbSchema;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by pauli on 07.03.2018.
 */

public class CalculatorLab {
    private static CalculatorLab sCalculatorLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    //private List<Calculator> preSafe = new ArrayList<>();

    public static CalculatorLab get(Context context) {
        if (sCalculatorLab == null) {
            sCalculatorLab = new CalculatorLab(context);
        }
        return sCalculatorLab;
    }

    private CalculatorLab (Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CalculatorBaseHelper(mContext)
                .getWritableDatabase();
    }

    public void addCalculator(Calculator c) {
        ContentValues values = getContentValues(c);
        mDatabase.insert(CalculatorDbSchema.CalculatorTable.NAME, null, values);
    }

    public void deleteCalculator (Calculator c){
        String uuidString = c.getId().toString();
        ContentValues values = getContentValues(c);
        mDatabase.delete(CalculatorDbSchema.CalculatorTable.NAME, CalculatorDbSchema.CalculatorTable.Cols.UUID+ " = ?",
                new String[] { String.valueOf(uuidString) });
    }

    public void deleteLastCalculator (Calculator c){
        String uuidString = String.valueOf(LastInsert());
        ContentValues values = getContentValues(c);
        mDatabase.delete(CalculatorDbSchema.CalculatorTable.NAME, CalculatorDbSchema.CalculatorTable.Cols.UUID + " = ?",
                new String[] {String.valueOf(uuidString)});
    }

    public List<Calculator> getCalculators() {
        List<Calculator> calculators = new ArrayList<>();
        CalculatorCursorWrapper cursor = queryCalculators(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                calculators.add(cursor.getCalculator());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return calculators;
    }

    public Calculator getCalculator (UUID id) {
        CalculatorCursorWrapper cursor = queryCalculators(
                CalculatorDbSchema.CalculatorTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCalculator();
        } finally {
            cursor.close();
        }
    }

    public void updateCalculator(Calculator calculator) {
        String uuidString = calculator.getId().toString();
        ContentValues values = getContentValues(calculator);

        mDatabase.update(CalculatorDbSchema.CalculatorTable.NAME, values,
                CalculatorDbSchema.CalculatorTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    private CalculatorCursorWrapper queryCalculators(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CalculatorDbSchema.CalculatorTable.NAME,
                null, // columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );

        return new CalculatorCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Calculator calculator) {
        ContentValues values = new ContentValues();
        values.put(CalculatorDbSchema.CalculatorTable.Cols.UUID, calculator.getId().toString());
        values.put(CalculatorDbSchema.CalculatorTable.Cols.DATE, calculator.getDate().getTime());
        values.put(CalculatorDbSchema.CalculatorTable.Cols.GAS, calculator.getGas());
        values.put(CalculatorDbSchema.CalculatorTable.Cols.CONSUMPTION, calculator.getConsumption());
        values.put(CalculatorDbSchema.CalculatorTable.Cols.KM, calculator.getKm());
        values.put(CalculatorDbSchema.CalculatorTable.Cols.PASSENGER, calculator.getPassenger());
        values.put(CalculatorDbSchema.CalculatorTable.Cols.ERGEBNIS, calculator.getErgebnis());
        values.put(CalculatorDbSchema.CalculatorTable.Cols.TITLE, calculator.getTitle());
        values.put(CalculatorDbSchema.CalculatorTable.Cols.IMAGE,calculator.getPhotoFilename());

        return values;
    }

    public int LastInsert() {

        final String MY_QUERY = "SELECT MAX(" + CalculatorDbSchema.CalculatorTable.Cols.UUID
                + ") FROM " + CalculatorDbSchema.CalculatorTable.NAME;
        Cursor cur = mDatabase.rawQuery(MY_QUERY, null);
        cur.moveToFirst();
        int ID = cur.getInt(0);
        cur.close();
        return ID;
    }

    public File getPhotoFile(Calculator calculator) {
    File filesDir = mContext.getFilesDir();
    return new File(filesDir, calculator.getPhotoFilename());
}}
