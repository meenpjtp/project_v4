package com.test.projectv4;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.test.projectv4.CustomListView.CustomListViewHistory;
import com.test.projectv4.DatabaseHelper.DBHelperHistory;
import com.test.projectv4.DatabaseHelper.DBHelperSeenPrize;
import com.test.projectv4.Model.CheckLotteryHistory;
import com.test.projectv4.Validation.InputValidation;

import java.util.ArrayList;

public class CheckLotteryActivity extends AppCompatActivity  {

    private AppCompatSpinner spSelectLotteryDate;
    private AppCompatButton btnCheckLottery;

    private TextInputEditText etInputLottery;
//    private TextInputLayout textInputLayout;

    private LinearLayoutCompat nestedScrollView;

    private String[] myString;
    private String Selecteditem;

    private DBHelperSeenPrize databaseHelper;
    private InputValidation inputValidation;

    //History
    private SwipeMenuListView mHistoryListView;
    private DBHelperHistory dbHelperHistory;
    private ArrayList<CheckLotteryHistory> checks;
    private CustomListViewHistory customListView;
    private int ID = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_lottery);

        initObjects();
        setTitle("ตรวจล็อตเตอรี่");

        etInputLottery = (TextInputEditText) findViewById(R.id.etInputLottery);
        btnCheckLottery = (AppCompatButton) findViewById(R.id.btnCheckLottery);
        spSelectLotteryDate = (AppCompatSpinner) findViewById(R.id.spSelectLotteryDate);
//        textInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);
        nestedScrollView = (LinearLayoutCompat) findViewById(R.id.nestedScrollView);
        mHistoryListView = (SwipeMenuListView) findViewById(R.id.historyListView);

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
//        Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "Had a snack at Snackbar", Snackbar.LENGTH_LONG);
//        View v = snack.getView();
//        FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)v.getLayoutParams();
//        params.gravity = Gravity.TOP;
//        v.setLayoutParams(params);
//        snack.show();
        btnCheckLottery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inputLottery = etInputLottery.getText().toString();
                if(!inputValidation.isInputEditTextLottery(etInputLottery, getString(R.string.error_message))){

                    //Display snackbar
                    Snackbar snack = Snackbar.make(findViewById(android.R.id.content), R.string.error_message, Snackbar.LENGTH_LONG);
                    View v = snack.getView();
                    FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)v.getLayoutParams();
                    params.gravity=  Gravity.TOP;
                    v.setLayoutParams(params);
                    snack.show();

                    return;
                }
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
//                    Snackbar.make(nestedScrollView, getString(R.string.win_lotto) , Snackbar.LENGTH_LONG).show();

                    //Add check lottery to database and listview
                    checks.add(new CheckLotteryHistory(ID, spSelectLotteryDate.getSelectedItem().toString()
                            , etInputLottery.getText().toString(), getString(R.string.win_lotto)));

                    dbHelperHistory.addLottery(new CheckLotteryHistory(ID, spSelectLotteryDate.getSelectedItem().toString()
                            , etInputLottery.getText().toString(), getString(R.string.win_lotto)));
                    clear();
                    customListView.notifyDataSetChanged();
                    mHistoryListView.setAdapter(customListView);


                } else{
                    //Display snackbar Lose!
                    Snackbar snack = Snackbar.make(findViewById(android.R.id.content), R.string.lose_lotto, Snackbar.LENGTH_LONG);
                    View v = snack.getView();
                    FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)v.getLayoutParams();
                    params.gravity=  Gravity.TOP;
                    v.setLayoutParams(params);
                    v.setBackgroundColor(getResources().getColor(R.color.lose_lotto));
                    snack.show();

                    //Add check lottery to database and listview
                    checks.add(new CheckLotteryHistory(ID, spSelectLotteryDate.getSelectedItem().toString()
                            , etInputLottery.getText().toString(), getString(R.string.lose_lotto)));

                    dbHelperHistory.addLottery(new CheckLotteryHistory(ID, spSelectLotteryDate.getSelectedItem().toString()
                            , etInputLottery.getText().toString(), getString(R.string.lose_lotto)));
                    clear();
                    customListView.notifyDataSetChanged();
                    mHistoryListView.setAdapter(customListView);
                }

            }
        });

        /**
         *
         * ListView
         *
         */
        customListView = new CustomListViewHistory(this, 0, checks);
        mHistoryListView.setAdapter(customListView);

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
        mHistoryListView.setMenuCreator(creator);

        mHistoryListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        int count = 0;
                        for(CheckLotteryHistory check:dbHelperHistory.getAllHistory()){
                            if(count == position) {
                                checks.remove(position);
                                dbHelperHistory.deleteHistory(check.getId());
                            }
                            count++;
                            Toast.makeText(getApplication(), R.string.deleted, Toast.LENGTH_SHORT).show();
                            queryCheckList();
                        }

                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

    }

    private void initObjects(){
        databaseHelper = new DBHelperSeenPrize(this);
        inputValidation = new InputValidation(this);
        dbHelperHistory = new DBHelperHistory(this);
        checks = dbHelperHistory.getAllHistory();
    }

    public void clear(){
        etInputLottery.setText(null);
    }

    private void queryCheckList(){
        checks = dbHelperHistory.getAllHistory();
        customListView = new CustomListViewHistory(this, 0, checks);
        mHistoryListView.setAdapter(customListView);
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

}
