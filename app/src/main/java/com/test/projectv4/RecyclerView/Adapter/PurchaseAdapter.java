package com.test.projectv4.RecyclerView.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.test.projectv4.Model.PurchaseModel;
import com.test.projectv4.R;
import java.util.List;

public class PurchaseAdapter extends RecyclerView.Adapter <PurchaseAdapter.MyViewHolder>{

    private Context context;
    private List<PurchaseModel> purchaseList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView selectedDateTextViewPurchase, statusTextViewPurchase, lotteryNumberTextViewPurchase
                ,amountTextViewPurchase, paidTextViewPurchase;
        public RelativeLayout pBackground;
        public LinearLayout pForeground;

        public MyViewHolder(View view){
            super(view);
            selectedDateTextViewPurchase = view.findViewById(R.id.selectedDateTextViewPurchase);
            statusTextViewPurchase = view.findViewById(R.id.statusTextViewPurchase);
            lotteryNumberTextViewPurchase = view.findViewById(R.id.lotteryNumberTextViewPurchase);
            amountTextViewPurchase = view.findViewById(R.id.amountTextViewPurchase);
            paidTextViewPurchase = view.findViewById(R.id.paidTextViewPurchase);
            pBackground = view.findViewById(R.id.p_background);
            pForeground = view.findViewById(R.id.p_foreground);
        }

    }

    public PurchaseAdapter(Context context, List<PurchaseModel> purchaseList){
        this.context = context;
        this.purchaseList = purchaseList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_purchase, parent, false);
        return new PurchaseAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final PurchaseModel item = purchaseList.get(position);
        holder.lotteryNumberTextViewPurchase.setText(item.getLottery_number());
        holder.selectedDateTextViewPurchase.setText(item.getLottery_date());
        holder.statusTextViewPurchase.setText(item.getLottery_status());
        holder.amountTextViewPurchase.setText(item.getLottery_amount());
        holder.paidTextViewPurchase.setText(item.getLottery_paid());

    }

    @Override
    public int getItemCount() {
        return purchaseList.size();
    }

    public void removeItem(int position){
        purchaseList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(PurchaseModel item, int position){
        purchaseList.add(position, item);
        notifyItemInserted(position);
    }
}
