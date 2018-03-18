package com.test.projectv4.ModeSimulation;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.test.projectv4.DatabaseHelper.DBHelperSimulation;
import com.test.projectv4.Model.SimulationModel;
import com.test.projectv4.R;
import com.test.projectv4.RecyclerView.Adapter.SimulationAdapter;
import com.test.projectv4.RecyclerView.Helper.SimulationHelper;

import java.util.ArrayList;

public class ModeSimulationActivity extends AppCompatActivity implements SimulationHelper.SimulationHelperListener{

    private FloatingActionButton mFloatingAddLotterySimulation;
    private DBHelperSimulation simulationHelper;


    private RecyclerView recyclerSimulation;
    private static final String TAG = ModeSimulationActivity.class.getSimpleName();
    private ArrayList<SimulationModel> simulationList;
    private SimulationAdapter mAdapter;
    private RelativeLayout modeSimulation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_simulation);

        //Initialize
        mFloatingAddLotterySimulation = (FloatingActionButton) findViewById(R.id.fabAddlotterySimulation);
        modeSimulation = (RelativeLayout) findViewById(R.id.modeSimulation);
        recyclerSimulation = (RecyclerView) findViewById(R.id.recyclerSimulation);

        simulationHelper = new DBHelperSimulation(this);

        simulationList = new ArrayList<>();
        mAdapter = new SimulationAdapter(this, simulationList);
        getDataFromSQLite();

        /**
         *
         * Recycler view
         *
         */
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerSimulation.setLayoutManager(mLayoutManager);
        recyclerSimulation.setItemAnimator(new DefaultItemAnimator());
        recyclerSimulation.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerSimulation.setAdapter(mAdapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new SimulationHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerSimulation);



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

    /**
     *
     * Recycler View
     *
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof SimulationAdapter.MyViewHolder) {
            // get the removed item name to display it in snack bar
            String date = simulationList.get(viewHolder.getAdapterPosition()).getLottery_date();

            // backup of removed item for undo purpose
            final SimulationModel deletedItem = simulationList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            mAdapter.removeItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make(modeSimulation, date + " ถูกลบแล้ว!", Snackbar.LENGTH_LONG);
            snackbar.setAction("ยกเลิก", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    mAdapter.restoreItem(deletedItem, deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }

    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                simulationList.clear();
                simulationList.addAll(simulationHelper.getAllLotterySimulation());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mAdapter.notifyDataSetChanged();
            }
        }.execute();
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
