package com.test.projectv4.ModeSimulation;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.test.projectv4.CustomListView.CustomListViewSimulation;
import com.test.projectv4.DatabaseHelper.DBHelperSimulation;
import com.test.projectv4.Model.SimulationModel;
import com.test.projectv4.R;

import java.util.ArrayList;

public class ModeSimulationActivity extends AppCompatActivity {

    ModeSimulationActivity m;
    //private ListView mListViewSimulation;
    private FloatingActionButton mFloatingAddLotterySimulation;
    private LinearLayout mLinearLayoutWithoutGames;
    private TextView mTextViewGame;

    private DBHelperSimulation simulationHelper;
    private ArrayList<SimulationModel> models;
    private CustomListViewSimulation customListView;

    private SwipeMenuListView mListViewSimulation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_simulation);

        //Initialize
        mListViewSimulation = (SwipeMenuListView) findViewById(R.id.simulationListView);
        mFloatingAddLotterySimulation = (FloatingActionButton) findViewById(R.id.fabAddlotterySimulation);
        mLinearLayoutWithoutGames = (LinearLayout) findViewById(R.id.linearLayoutWithoutGames);
        mTextViewGame = (TextView) findViewById(R.id.gameTextView);

        simulationHelper = new DBHelperSimulation(this);
        querySQL();



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

        /**
         *
         * ListView
         *
         */
        customListView = new CustomListViewSimulation(this,0,models);
        mListViewSimulation.setAdapter(customListView);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                /*SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(170);
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);*/

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(150);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };


        // set creator
        mListViewSimulation.setMenuCreator(creator);

        mListViewSimulation.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        int count = 0;
                        for(SimulationModel model:simulationHelper.getAllLotterySimulation()){
                            if(count == position) {
                                models.remove(position);
                                simulationHelper.deleteLottery(model.getId());
                            }
                            count++;
                            Toast.makeText(getApplication(), R.string.deleted, Toast.LENGTH_SHORT).show();
                            querySQL();
                        }

                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });


    }

    private void querySQL(){
        models = simulationHelper.getAllLotterySimulation();
        customListView = new CustomListViewSimulation(this,0,models);
        mListViewSimulation.setAdapter(customListView);
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
                Intent a = new Intent(this, SimulationStaticActivity.class);
                startActivity(a);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
