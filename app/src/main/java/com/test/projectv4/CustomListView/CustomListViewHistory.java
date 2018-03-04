package com.test.projectv4.CustomListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.test.projectv4.Model.CheckLotteryHistory;
import com.test.projectv4.R;

import java.util.List;

public class CustomListViewHistory extends ArrayAdapter<CheckLotteryHistory>{

    public CustomListViewHistory(Context context, int resource, List<CheckLotteryHistory> objects) {
        super(context, R.layout.item_history, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_history, null);

            CheckLotteryHistory check = getItem(position);
            TextView date = convertView.findViewById(R.id.dateTextView);
            date.setText(check.getDate());

            TextView lotteryNumber = convertView.findViewById(R.id.lotteryNumberTextView);
            lotteryNumber.setText(check.getLotteryNumber());

            TextView result = convertView.findViewById(R.id.resultTextView);
            result.setText(check.getResult());

        }
        return convertView;
    }
}
