package com.example.hannahkern.tankup.database;

/**
 * Created by pauli on 11.03.2018.
 */

public class CalculatorDbSchema {
        public static final class CalculatorTable {
            public static final String NAME = "calculators";


            public static final class Cols {
                public static final String UUID = "uuid";
                public static final String DATE = "date";
                public static final String GAS = "gas";
                public static final String KM = "km";
                public static final String PASSENGER = "passenger";
                public static final String ERGEBNIS = "ergebnis";
                public static final String TITLE = "title";
            }
        }

}
