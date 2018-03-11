package com.test.projectv4.ModeSimulation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.test.projectv4.CustomListView.CustomListViewSimulation;
import com.test.projectv4.DatabaseHelper.DBHelperSimulation;
import com.test.projectv4.Model.SimulationModel;
import com.test.projectv4.R;

import java.util.ArrayList;

public class ModeSimulationActivity extends AppCompatActivity {

    ModeSimulationActivity m;
    private ListView mListViewViewSimulation;
    private FloatingActionButton mFloatingAddLotterySimulation;
    private LinearLayout mLinearLayoutWithoutGames;
    private TextView mTextViewGame;

    private DBHelperSimulation simulationHelper;
    private ArrayList<SimulationModel> models;
    private CustomListViewSimulation customListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_simulation);

        //Initialize
        mListViewViewSimulation = (ListView) findViewById(R.id.simulationListView);
        mFloatingAddLotterySimulation = (FloatingActionButton) findViewById(R.id.fabAddlotterySimulation);
        mLinearLayoutWithoutGames = (LinearLayout) findViewById(R.id.linearLayoutWithoutGames);
        mTextViewGame = (TextView) findViewById(R.id.gameTextView);

        simulationHelper = new DBHelperSimulation(this);
        querySQL();


        //mListViewViewSimulation.setVisibility(View.INVISIBLE);



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

    private void querySQL(){
        models = simulationHelper.getAllLotterySimulation();
        customListView = new CustomListViewSimulation(this,0,models);
        mListViewViewSimulation.setAdapter(customListView);
    }

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_stat,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_stat:
                Intent c = new Intent(this, SimulationStaticActivity.class);
                startActivity(c);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
