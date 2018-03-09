package com.test.projectv4.CustomListView;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.test.projectv4.Model.SimulationModel;
import com.test.projectv4.R;

import java.util.List;

public class CustomListViewSimulation extends ArrayAdapter<SimulationModel>{

    public CustomListViewSimulation(Context context, int resource, List<SimulationModel> objects){
        super(context, R.layout.item_simulation, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_simulation, null);

            SimulationModel lottery = getItem(position);
            TextView lottery_date = convertView.findViewById(R.id.selectedDateTextView);
            lottery_date.setText(lottery.getLottery_date());

            TextView lottery_status = convertView.findViewById(R.id.statusTextView);
            lottery_status.setText(lottery.getLottery_status());

            TextView lottery_number = convertView.findViewById(R.id.lotteryNumberTextView);
            lottery_number.setText(lottery.getLottery_number());

            TextView lottery_amount = convertView.findViewById(R.id.amountTextView);
            lottery_amount.setText(String.valueOf(lottery.getLottery_amount()));

            TextView lottery_paid = convertView.findViewById(R.id.paidTextView);
            lottery_paid.setText(String.valueOf(lottery.getLottery_paid()));
        }
        return convertView;
    }
}
