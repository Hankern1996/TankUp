package com.example.hannahkern.tankup;

import java.util.Date;
import java.util.UUID;

/**
 * Created by hannahkern on 28.02.18.
 */

public class Calculator {

    private UUID mId;
    private Date mDate;
    private String mGas;
    private String mKm;
    private String mErgebnis;

    public Calculator(){
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getGas() {
        return mGas;
    }

    public void setGas(String gas) {
        mGas = gas;
    }

    public String getKm() {
        return mKm;
    }

    public void setKm(String km) {
        mKm = km;
    }

    public String getErgebnis() {
        return mErgebnis;
    }

    public void setErgebnis(String ergebnis) {
        mErgebnis = ergebnis;
    }

}
