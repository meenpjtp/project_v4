package com.test.projectv4;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.test.projectv4.DatabaseHelper.DBHelperHistory;
import com.test.projectv4.DatabaseHelper.DBHelperSeenPrize;
import com.test.projectv4.Model.CheckLotteryHistoryModel;
import com.test.projectv4.RecyclerView.Adapter.CheckLotteryAdapter;
import com.test.projectv4.RecyclerView.Helper.CheckLotteryHelper;
import com.test.projectv4.Util.StringUtil;
import com.test.projectv4.Validation.InputValidation;

import java.util.ArrayList;

public class CheckLotteryActivity extends AppCompatActivity implements CheckLotteryHelper.CheckLotteryHelperListener {

    //Recycler View
    private static final String TAG = CheckLotteryActivity.class.getSimpleName();
    private ArrayList<CheckLotteryHistoryModel> checkList;
    private RecyclerView rvCheckLottery;
    private CheckLotteryAdapter mAdapter;

    private AppCompatSpinner spSelectLotteryDate;
    private AppCompatButton btnCheckLottery;

    private TextInputEditText etInputLottery;

    private LinearLayoutCompat checkLotteryActivity;

    private String[] myString;
    private String Selecteditem;

    private DBHelperSeenPrize databaseHelper;
    private InputValidation inputValidation;


    //History
    private DBHelperHistory dbHelperHistory;
    private int ID = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_lottery);

        initObjects();
        getDataFromSQLite();
        setTitle("ตรวจล็อตเตอรี่");

        etInputLottery = (TextInputEditText) findViewById(R.id.etInputLottery);
        btnCheckLottery = (AppCompatButton) findViewById(R.id.btnCheckLottery);
        spSelectLotteryDate = (AppCompatSpinner) findViewById(R.id.spSelectLotteryDate);
        checkLotteryActivity = (LinearLayoutCompat) findViewById(R.id.checkLotteryActivity);
        rvCheckLottery = (RecyclerView) findViewById(R.id.recyclerCheckLottery);

        /**
         *
         * Recycler view
         *
         */
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvCheckLottery.setLayoutManager(mLayoutManager);
        rvCheckLottery.setItemAnimator(new DefaultItemAnimator());
        rvCheckLottery.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvCheckLottery.setAdapter(mAdapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new CheckLotteryHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rvCheckLottery);


        /**
         *
         * Spinner
         *
         */
        ArrayAdapter adapter;
        Resources res = getResources();
        myString = res.getStringArray(R.array.lotteries);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, myString);
        spSelectLotteryDate.setAdapter(adapter);

        spSelectLotteryDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Selecteditem = spSelectLotteryDate.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        /**
         *
         *         press on button, database will check lottery
         *
         */

        btnCheckLottery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Display Error
                if(StringUtil.isEmpty(etInputLottery.getText().toString())){
                    etInputLottery.setError(getString(R.string.error_message));
                    return;
                }

                if(etInputLottery.getText().toString().length() == 6){
                    if(databaseHelper.checkLottery(spSelectLotteryDate.getSelectedItem().toString(),
                            etInputLottery.getText().toString().trim())) {

                        //Display snackbar Win!
                        Snackbar snack = Snackbar.make(findViewById(android.R.id.content), R.string.win_lotto, Snackbar.LENGTH_LONG);
                        View v = snack.getView();
                        FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)v.getLayoutParams();
                        params.gravity=  Gravity.TOP;
                        v.setLayoutParams(params);
                        v.setBackgroundColor(getResources().getColor(R.color.win_lotto));
                        snack.show();

                        //Add check lottery to database
                        dbHelperHistory.addLottery(new CheckLotteryHistoryModel(ID, spSelectLotteryDate.getSelectedItem().toString()
                                , etInputLottery.getText().toString(), getString(R.string.win_lotto)));
                        clear();
                        getDataFromSQLite();


                    } else{
                        //Display snackbar Lose!
                        Snackbar snack = Snackbar.make(findViewById(android.R.id.content), R.string.lose_lotto, Snackbar.LENGTH_LONG);
                        View v = snack.getView();
                        FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)v.getLayoutParams();
                        params.gravity=  Gravity.TOP;
                        v.setLayoutParams(params);
                        v.setBackgroundColor(getResources().getColor(R.color.lose_lotto));
                        snack.show();

                        //Add check lottery to database
                        dbHelperHistory.addLottery(new CheckLotteryHistoryModel(ID, spSelectLotteryDate.getSelectedItem().toString()
                                , etInputLottery.getText().toString(), getString(R.string.lose_lotto)));
                        clear();
                        getDataFromSQLite();
                    }
                } else {
                    Toast.makeText(CheckLotteryActivity.this, R.string.error_message_lenght_lottery, Toast.LENGTH_LONG).show();

                }


            }
        });

    }

    private void initObjects(){
        databaseHelper = new DBHelperSeenPrize(this);
        inputValidation = new InputValidation(this);
        dbHelperHistory = new DBHelperHistory(this);
        checkList = new ArrayList<>();
        checkList = dbHelperHistory.getAllHistory();
        mAdapter = new CheckLotteryAdapter(this, checkList);

    }

    public void clear(){
        etInputLottery.setText(null);
    }


    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_home,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home:
                Intent c = new Intent(this, MenuActivity.class);
                startActivity(c);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     *
     * Recycler View
     *
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof CheckLotteryAdapter.MyViewHolder) {
            // get the removed item name to display it in snack bar
            String date = checkList.get(viewHolder.getAdapterPosition()).getDate();

            // backup of removed item for undo purpose
            final CheckLotteryHistoryModel deletedItem = checkList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            mAdapter.removeItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make(checkLotteryActivity, date + " ลบออกจากประวัติแล้ว!", Snackbar.LENGTH_LONG);
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
                checkList.clear();
                checkList.addAll(dbHelperHistory.getAllHistory());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mAdapter.notifyDataSetChanged();
            }
        }.execute();
    }

}
