package com.batchmates.android.powerballlotterychecker.view.mainactivity;

import com.batchmates.android.powerballlotterychecker.BasePresenter;
import com.batchmates.android.powerballlotterychecker.BaseView;
import com.batchmates.android.powerballlotterychecker.model.LotteryNumbersPojo;
import com.batchmates.android.powerballlotterychecker.model.MyLoteryPojo;

import java.util.List;

import okhttp3.Response;

/**
 * Created by Android on 8/13/2017.
 */

public interface MainActivityContract {

    interface View extends BaseView
    {
        void returnedNumbers(List<MyLoteryPojo> response);
        void winning(List<MyLoteryPojo> response);
    }

    interface  Presenter extends BasePresenter<View>
    {
        void getNumbers();
        void getWinner(String numbers);
    }
}
