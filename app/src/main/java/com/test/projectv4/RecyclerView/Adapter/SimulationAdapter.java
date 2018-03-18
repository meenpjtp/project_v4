package com.test.projectv4.RecyclerView.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.test.projectv4.Model.SimulationModel;
import com.test.projectv4.R;
import java.util.List;

public class SimulationAdapter extends RecyclerView.Adapter <SimulationAdapter.MyViewHolder>{

    private Context context;
    private List<SimulationModel> simulationList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView selectedDateTextView, statusTextView, lotteryNumberTextView
                ,amountTextView, paidTextView;
        public RelativeLayout sBackground;
        public LinearLayout sForeground;

        public MyViewHolder(View view){
            super(view);
            selectedDateTextView = view.findViewById(R.id.selectedDateTextView);
            statusTextView = view.findViewById(R.id.statusTextView);
            lotteryNumberTextView = view.findViewById(R.id.lotteryNumberTextView);
            amountTextView = view.findViewById(R.id.amountTextView);
            paidTextView = view.findViewById(R.id.paidTextView);
            sBackground = view.findViewById(R.id.s_background);
            sForeground = view.findViewById(R.id.s_foreground);
        }

    }

    public SimulationAdapter(Context context, List<SimulationModel> simulationModelList){
        this.context = context;
        this.simulationList = simulationModelList;
    }

    @Override
    public SimulationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_simulation, parent, false);
        return new SimulationAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SimulationAdapter.MyViewHolder holder, int position) {
        final SimulationModel item = simulationList.get(position);
        holder.lotteryNumberTextView.setText(item.getLottery_number());
        holder.selectedDateTextView.setText(item.getLottery_date());
        holder.statusTextView.setText(item.getLottery_status());
        holder.amountTextView.setText(item.getLottery_amount());
        holder.paidTextView.setText(item.getLottery_paid());

    }

    @Override
    public int getItemCount() {
        return simulationList.size();
    }

    public void removeItem(int position){
        simulationList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(SimulationModel item, int position){
        simulationList.add(position, item);
        notifyItemInserted(position);
    }
}
