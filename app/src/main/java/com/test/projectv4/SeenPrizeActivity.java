package com.test.projectv4;

import android.content.Intent;
import android.content.res.Resources;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.test.projectv4.DatabaseHelper.DBHelperSeenPrize;

import java.io.IOException;

public class SeenPrizeActivity extends AppCompatActivity {

    private String[] myString;
    private Spinner spinner;
    private String[] DataToDB;
    private String[] result_array;
    private String Selecteditem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seen_prize);

        //Back to Menu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ArrayAdapter adapter;
        Resources res = getResources();
        myString = res.getStringArray(R.array.lotteries);
        spinner = (Spinner)findViewById(R.id.spinner);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, myString);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Selecteditem = spinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void RunDatabase(View view){
        DBHelperSeenPrize myDbHelper = new DBHelperSeenPrize(this);
        try{
            myDbHelper.createDataBase();
            DataToDB = myDbHelper.ReadFromDB(Selecteditem.trim());
            TableLayout tableLayout = (TableLayout)findViewById(R.id.tableLayout);
            tableLayout.removeAllViews();



            for(int i=0; i < DataToDB.length; i++){
                TableRow tableRow = new TableRow(this);
                result_array = DataToDB[i].split(",");

                tableRow.setPadding(5,5,5,5);
                TextView txt1 = new TextView(this);
                TextView txt2 = new TextView(this);
                TextView txt3 = new TextView(this);

                txt1.setText(result_array[0]);
                txt2.setText(result_array[1]);
                txt3.setText(result_array[2]);

                tableRow.addView(txt1);
                tableRow.addView(txt2);
                tableRow.addView(txt3);

                tableLayout.addView(tableRow);
            }
        } catch (IOException ioe){
            throw new Error("Unable to create database");
        }try {
            try {
                myDbHelper.openDataBase();
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException sqle){
            throw sqle;
        }

    }

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_check,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnCheck:
                Intent c = new Intent(this, CheckLotteryActivity.class);
                startActivity(c);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
