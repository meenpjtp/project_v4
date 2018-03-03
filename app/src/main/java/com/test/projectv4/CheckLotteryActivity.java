package com.test.projectv4;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.test.projectv4.DatabaseHelper.DBHelperSeenPrize;

public class CheckLotteryActivity extends AppCompatActivity  {

    private Spinner spSelectLotteryDate;
    private EditText etInputLottery;
    private Button btnCheckLottery;

    private String[] myString;
    private String Selecteditem;

    private DBHelperSeenPrize databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_lottery);

        initObjects();

        etInputLottery = (EditText) findViewById(R.id.etInputLottery);
        btnCheckLottery = (Button)findViewById(R.id.btnCheckLottery);
        spSelectLotteryDate = (Spinner) findViewById(R.id.spSelectLotteryDate);

        //Spinner
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

        //press on button, database will check lottery
        btnCheckLottery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(databaseHelper.checkLottery(spSelectLotteryDate.getSelectedItem().toString(),
                        etInputLottery.getText().toString().trim())) {
                    etInputLottery.setText("xxxxx");

                }

            }
        });

    }

    private void initObjects(){
        databaseHelper = new DBHelperSeenPrize(this);
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
