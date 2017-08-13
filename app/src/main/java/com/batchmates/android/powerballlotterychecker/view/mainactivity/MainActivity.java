package com.batchmates.android.powerballlotterychecker.view.mainactivity;

import android.nfc.Tag;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.batchmates.android.powerballlotterychecker.R;
import com.batchmates.android.powerballlotterychecker.injection.mainactivity.DaggerMainActivityComponent;
import com.batchmates.android.powerballlotterychecker.model.LotteryNumbersPojo;
import com.batchmates.android.powerballlotterychecker.model.MyLoteryPojo;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {


    private static final String TAG = "MainActivity";
    private RecyclerView.LayoutManager layoutManager;
    private DefaultItemAnimator defaultItemAnimator;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Inject
    MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpDagger();
        presenter.addView(this,this);
        ButterKnife.bind(this);
        presenter.getNumbers();
        defaultItemAnimator = new DefaultItemAnimator();

    }

    private void setUpDagger() {
        DaggerMainActivityComponent.create().inject(this);
    }

    @BindView(R.id.rvListOfWinners)
    RecyclerView recyclerView;

    @BindView(R.id.tietMyNumbers)
    TextInputEditText myNumbers;

    @BindView(R.id.tvDateOfWin)
    TextView dateofWin;

    @BindView(R.id.tvWinningNumbers)
    TextView winningNumbers;


    @BindView(R.id.tvMultiplier)
    TextView multiplier;

    @Override
    public void showError() {

    }

    @Override
    public void returnedNumbers(List<MyLoteryPojo> response) {

        layoutManager = new LinearLayoutManager(this);
        recyclerViewAdapter = new RecyclerViewAdapter(response);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(defaultItemAnimator);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void winning(List<MyLoteryPojo> response) {
        dateofWin.setText("Date: "+response.get(0).getDate());
        winningNumbers.setText("Numbers: "+response.get(0).getNumbers());
        multiplier.setText("Multiplier: "+response.get(0).getMultiplier());
    }

    public void didIWin(View view) {
//        presenter.getWinner("09 40 63 64 66 17");
        Log.d(TAG, "didIWin: I Won");
        String pass = myNumbers.getText().toString();

        if (pass.length() != 17) {
            Toast.makeText(this, "Please Enter a Valid Entry", Toast.LENGTH_SHORT).show();
        } else {
            String numberToPass = "";
            numberToPass += pass.substring(0, 2) + " ";
            numberToPass += pass.substring(3, 5) + " ";
            numberToPass += pass.substring(6, 8) + " ";
            numberToPass += pass.substring(9, 11) + " ";
            numberToPass += pass.substring(12, 14) + " ";
            numberToPass += pass.substring(15, 17);
            presenter.getWinner(numberToPass);
            Log.d(TAG, "didIWin: " + numberToPass);
            Log.d(TAG, "didIWin: " + pass.length());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getNumbers();
    }
}
