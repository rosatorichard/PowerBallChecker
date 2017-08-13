package com.batchmates.android.powerballlotterychecker.view.mainactivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.batchmates.android.powerballlotterychecker.R;
import com.batchmates.android.powerballlotterychecker.model.LotteryNumbersPojo;
import com.batchmates.android.powerballlotterychecker.model.MyLoteryPojo;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * Created by Android on 8/13/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<MyLoteryPojo> response=new ArrayList<>();

    public RecyclerViewAdapter(List<MyLoteryPojo> response) {
        this.response = response;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        MyLoteryPojo lotteryNumbersPojo= response.get(position);
        holder.date.setText("Date: "+lotteryNumbersPojo.getDate());
        holder.numbers.setText("Numbers: "+lotteryNumbersPojo.getNumbers());
        holder.multiplier.setText("Multiplier: "+lotteryNumbersPojo.getMultiplier());

    }

    @Override
    public int getItemCount() {
        return response.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView numbers;
        TextView multiplier;

        public ViewHolder(View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.tvDateOfPull);
            numbers=itemView.findViewById(R.id.tvNumbersPulled);
            multiplier=itemView.findViewById(R.id.tvMultiplier);
        }
    }
}
