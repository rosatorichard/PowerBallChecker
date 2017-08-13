package com.batchmates.android.powerballlotterychecker.view.mainactivity;

import com.batchmates.android.powerballlotterychecker.model.LotteryNumbersPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Android on 8/13/2017.
 */

public class RetroFitHelper {

    private static final String BASE_URL = "https://data.ny.gov/";
    private static final String APP_TOKEN = "ilUFDpHpdeqIY27f8NjmFqnkm";

    public Retrofit Create() {
        Retrofit retro = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retro;
    }

    public Call<List<LotteryNumbersPojo>> getLotoNumber() {

        Retrofit retro= Create();
        lotteryNumbers loteryNumbers=retro.create(RetroFitHelper.lotteryNumbers.class);
        return loteryNumbers.call(APP_TOKEN);
    }

    public Call<List<LotteryNumbersPojo>> getWinner(String numbers)
    {
        Retrofit retro=Create();
        lotteryNumbers lotterynumbers=retro.create(RetroFitHelper.lotteryNumbers.class);
        return lotterynumbers.winner(APP_TOKEN,numbers);
    }


    //https://data.ny.gov/resource/8vkr-v8vh.json?winning_numbers=17 22 36 37 52 24
    //https://data.ny.gov/resource/8vkr-v8vh.json?$$app_token=ilUFDpHpdeqIY27f8NjmFqnkm
    public interface lotteryNumbers {
        @GET("resource/8vkr-v8vh.json")
        Call<List<LotteryNumbersPojo>> call(@Query("$$app_token") String token);

        @GET("resource/8vkr-v8vh.json")
        Call<List<LotteryNumbersPojo>> winner(@Query("$$app_token")String token,@Query("winning_numbers")String winningNumbers);
    }
}
