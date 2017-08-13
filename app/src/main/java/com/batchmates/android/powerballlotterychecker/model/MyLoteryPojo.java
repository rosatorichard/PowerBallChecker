package com.batchmates.android.powerballlotterychecker.model;

/**
 * Created by Android on 8/13/2017.
 */

public class MyLoteryPojo {

    String date;
    String numbers;
    String multiplier;

    public MyLoteryPojo(String date, String numbers, String multiplier) {
        this.date = date;
        this.numbers = numbers;
        this.multiplier = multiplier;
    }


    public String getDate() {
        return date;
    }

    public String getNumbers() {
        return numbers;
    }

    public String getMultiplier() {
        return multiplier;
    }
}
