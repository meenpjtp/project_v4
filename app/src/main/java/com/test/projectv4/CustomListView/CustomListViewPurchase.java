package com.test.projectv4.CustomListView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.test.projectv4.Model.PurchaseModel;
import com.test.projectv4.R;

import java.util.List;

public class CustomListViewPurchase extends ArrayAdapter<PurchaseModel>{

    public CustomListViewPurchase(Context context, int resource, List<PurchaseModel> objects){
        super(context, R.layout.item_purchase, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_purchase, null);

            PurchaseModel lottery = getItem(position);
            TextView lottery_date = convertView.findViewById(R.id.selectedDateTextViewPurchase);
            lottery_date.setText(lottery.getLottery_date());

            TextView lottery_status = convertView.findViewById(R.id.statusTextViewPurchase);
            lottery_status.setText(lottery.getLottery_status());

            TextView lottery_number = convertView.findViewById(R.id.lotteryNumberTextViewPurchase);
            lottery_number.setText(lottery.getLottery_number());

            TextView lottery_amount = convertView.findViewById(R.id.amountTextViewPurchase);
            lottery_amount.setText(String.valueOf(lottery.getLottery_amount()));

            TextView lottery_paid = convertView.findViewById(R.id.paidTextViewPurchase);
            lottery_paid.setText(String.valueOf(lottery.getLottery_paid()));
        }
        return convertView;
    }
}
