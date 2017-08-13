package com.batchmates.android.powerballlotterychecker.view.mainactivity;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.batchmates.android.powerballlotterychecker.model.LotteryNumbersPojo;
import com.batchmates.android.powerballlotterychecker.model.MyLoteryPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Android on 8/13/2017.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter{

    private List<MyLoteryPojo> loteryList=new ArrayList<>();
    private List<MyLoteryPojo> winnerList=new ArrayList<>();

    private static final String TAG = "MainPresenter";
    MainActivityContract.View view;
    Context context;

    @Override
    public void addView(MainActivityContract.View view,Context context) {
        this.view=view;
        this.context=context;
    }

    @Override
    public void removeView() {
        this.view=null;
    }

    @Override
    public void getNumbers() {

        loteryList.clear();
        retrofit2.Call<List<LotteryNumbersPojo>> call=new RetroFitHelper().getLotoNumber();
        call.enqueue(new Callback<List<LotteryNumbersPojo>>() {
            @Override
            public void onResponse(Call<List<LotteryNumbersPojo>> call, Response<List<LotteryNumbersPojo>> response) {
                Log.d(TAG, "onResponse: "+response.body().get(0).getDrawDate());
                Log.d(TAG, "onResponse: "+response.body().get(0).getWinningNumbers());
                Log.d(TAG, "onResponse: "+response.body().get(0).getMultiplier());
                for (int i = 0; i <20 ; i++) {

                    loteryList.add(new MyLoteryPojo(response.body()
                            .get(i).getDrawDate(),response.body()
                            .get(i).getWinningNumbers(),response.body()
                            .get(i).getMultiplier()));

                }
                view.returnedNumbers(loteryList);
            }

            @Override
            public void onFailure(Call<List<LotteryNumbersPojo>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());

            }
        });
    }

    @Override
    public void getWinner(String numbers) {
        winnerList.clear();
        retrofit2.Call<List<LotteryNumbersPojo>> call=new RetroFitHelper().getWinner(numbers);
        call.enqueue(new Callback<List<LotteryNumbersPojo>>() {
            @Override
            public void onResponse(Call<List<LotteryNumbersPojo>> call, Response<List<LotteryNumbersPojo>> response) {

                if(response.body().size()==0)
                {
                    Toast.makeText(context,"No Winners for this number combination",Toast.LENGTH_SHORT).show();
                }
                else
                {

                    Toast.makeText(context,"There is a Winner",Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < response.body().size() ; i++) {
                        winnerList.add(new MyLoteryPojo(response.body()
                                .get(i).getDrawDate(),response.body()
                                .get(i).getWinningNumbers(),response.body()
                                .get(i).getMultiplier()));
                        Log.d(TAG, "onResponse: "+response.body().get(i).getDrawDate());
                        Log.d(TAG, "onResponse: "+response.body().get(i).getWinningNumbers());
                    }
                    view.winning(winnerList);

                }
            }

            @Override
            public void onFailure(Call<List<LotteryNumbersPojo>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.toString());

            }
        });
    }
}
