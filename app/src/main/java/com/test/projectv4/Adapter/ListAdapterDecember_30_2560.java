package com.test.projectv4.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.test.projectv4.Model.LotteryModel;
import com.test.projectv4.R;

import java.util.List;

/**
 * Created by Administrator1 on 1/2/2561.
 */

public class ListAdapterDecember_30_2560 extends BaseAdapter{
    private Context mContext;
    private List<LotteryModel> lotteryModelList;

    public ListAdapterDecember_30_2560(Context mContext, List<LotteryModel> lotteryModelList) {
        this.mContext = mContext;
        this.lotteryModelList = lotteryModelList;
    }

    @Override
    public int getCount() {
        return lotteryModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return lotteryModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lotteryModelList.get(position).getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext, R.layout.item_lottery, null);
        TextView idTextView = (TextView) v.findViewById(R.id.idTextView);
        TextView numTextView = (TextView) v.findViewById(R.id.numTextView);
        TextView descriptionTextView = (TextView) v.findViewById(R.id.descriptionTextView);

        idTextView.setText(String.valueOf(lotteryModelList.get(position).getID()));
        numTextView.setText(lotteryModelList.get(position).getLottery_num());
        descriptionTextView.setText(lotteryModelList.get(position).getDescription());

        return v;
    }
}
