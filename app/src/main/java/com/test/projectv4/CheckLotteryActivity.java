package com.test.projectv4;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.test.projectv4.DatabaseHelper.DBHelperSeenPrize;
import com.test.projectv4.Validation.InputValidation;

public class CheckLotteryActivity extends AppCompatActivity  {

    private AppCompatSpinner spSelectLotteryDate;
    private AppCompatButton btnCheckLottery;

    private TextInputEditText etInputLottery;
    private TextInputLayout textInputLayout;

    private LinearLayoutCompat nestedScrollView;

    private String[] myString;
    private String Selecteditem;

    private DBHelperSeenPrize databaseHelper;
    private InputValidation inputValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_lottery);

        initObjects();

        etInputLottery = (TextInputEditText) findViewById(R.id.etInputLottery);
        btnCheckLottery = (AppCompatButton) findViewById(R.id.btnCheckLottery);
        spSelectLotteryDate = (AppCompatSpinner) findViewById(R.id.spSelectLotteryDate);
        textInputLayout = (TextInputLayout) findViewById(R.id.textInputLayout);
        nestedScrollView = (LinearLayoutCompat) findViewById(R.id.nestedScrollView);

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

                String inputLottery = etInputLottery.getText().toString();
                if(!inputValidation.isInputEditTextLottery(etInputLottery, textInputLayout, getString(R.string.error_message))){
                    return;
                }
                if(databaseHelper.checkLottery(spSelectLotteryDate.getSelectedItem().toString(),
                        etInputLottery.getText().toString().trim())) {
                    Snackbar.make(nestedScrollView, getString(R.string.win_lotto) , Snackbar.LENGTH_LONG).show();
                    clear();


                } else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(CheckLotteryActivity.this);
                    builder.setMessage(Selecteditem + " " + inputLottery + " " + "ไม่ถูกรางวัล")
                            .create()
                            .show();
                }

            }
        });

    }

    //Initialize Database
    private void initObjects(){
        databaseHelper = new DBHelperSeenPrize(this);
        inputValidation = new InputValidation(this);
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

}
