
package com.batchmates.android.powerballlotterychecker.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LotteryNumbersPojo implements Serializable
{

    @SerializedName("draw_date")
    @Expose
    private String drawDate;
    @SerializedName("multiplier")
    @Expose
    private String multiplier;
    @SerializedName("winning_numbers")
    @Expose
    private String winningNumbers;
    private final static long serialVersionUID = 566929013803503003L;

    public String getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(String drawDate) {
        this.drawDate = drawDate;
    }

    public String getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(String multiplier) {
        this.multiplier = multiplier;
    }

    public String getWinningNumbers() {
        return winningNumbers;
    }

    public void setWinningNumbers(String winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

}
