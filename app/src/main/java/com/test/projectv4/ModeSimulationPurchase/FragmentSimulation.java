package com.test.projectv4.ModeSimulationPurchase;


import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.test.projectv4.R;

//Instead i use intent i try to use fragment.

public class FragmentSimulation extends Fragment{

    public static String TAG = "SimulationFragment";
    private ModeSimulationPurchaseActivity mSimulation;
    private RecyclerView mRecycleView;
    private FloatingActionButton mFloatingActionButton;
    private LinearLayout mLinearLayoutWithoutGames;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_simulation, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        initialize();
    }

    private void initialize(){
        mSimulation = (ModeSimulationPurchaseActivity)getActivity();
        setRecyclerView();
        setViewEvents();
        getViewComponents();
    }

    private void getViewComponents(){
        mRecycleView = (RecyclerView)getView().findViewById(R.id.recyclerViewLottery);
        mFloatingActionButton = (FloatingActionButton)getView().findViewById(R.id.floatingActionButtonLotteryAdd);
        mLinearLayoutWithoutGames = (LinearLayout)getView().findViewById(R.id.linearLayoutWithoutGames);
    }

    private void setRecyclerView(){

    }

    private void setViewEvents(){
        mFloatingActionButton.setOnClickListener(getFloatingButtonOnClickListener());
    }

    private View.OnClickListener getFloatingButtonOnClickListener(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        };
    }


}
