package com.test.projectv4.ModeSimulation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.test.projectv4.R;

public class ModeSimulationActivity extends AppCompatActivity {

    ModeSimulationActivity m;
    private ListView mListViewViewSimulation;
    private FloatingActionButton mFloatingAddLotterySimulation;
    private LinearLayout mLinearLayoutWithoutGames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_simulation);

        //Initialize
        mListViewViewSimulation = (ListView) findViewById(R.id.simulationListView);
        mFloatingAddLotterySimulation = (FloatingActionButton) findViewById(R.id.fabAddlotterySimulation);
        mLinearLayoutWithoutGames = (LinearLayout) findViewById(R.id.linearLayoutWithoutGames);


        /**
         *
         *  Floating Action Button
         *
         */
        mFloatingAddLotterySimulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addLotterySimulation = new Intent(ModeSimulationActivity.this, AddLotteryToSimulationActivity.class);
                startActivity(addLotterySimulation);
            }
        });


    }

}
