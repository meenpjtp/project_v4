package com.test.projectv4.RecyclerView.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.test.projectv4.Model.CheckLotteryHistoryModel;
import com.test.projectv4.R;
import java.util.List;

public class CheckLotteryAdapter extends RecyclerView.Adapter <CheckLotteryAdapter.MyViewHolder>{
    private Context context;
    private List<CheckLotteryHistoryModel> checkList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView dateTextView, lotteryNumberTextView, resultTextView;
        public RelativeLayout viewBackground;
        public LinearLayout viewForeground;

        public MyViewHolder(View view){
            super(view);
            dateTextView = view.findViewById(R.id.dateTextView);
            lotteryNumberTextView = view.findViewById(R.id.lotteryNumberTextView);
            resultTextView = view.findViewById(R.id.resultTextView);
            viewBackground = view.findViewById(R.id.view_background);
            viewForeground = view.findViewById(R.id.view_foreground);
        }
    }

    public CheckLotteryAdapter(Context context, List<CheckLotteryHistoryModel> checkList){
        this.context = context;
        this.checkList = checkList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final CheckLotteryHistoryModel item = checkList.get(position);
        holder.dateTextView.setText(item.getDate());
        holder.lotteryNumberTextView.setText(item.getLotteryNumber());
        holder.resultTextView.setText(item.getResult());
    }

    @Override
    public int getItemCount() {
        return checkList.size();
    }

    public void removeItem(int position){
        checkList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(CheckLotteryHistoryModel item, int position){
        checkList.add(position, item);
        notifyItemInserted(position);
    }
}
