package com.test.projectv4.ModePurchase;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.test.projectv4.CustomListView.CustomListViewPurchase;
import com.test.projectv4.DatabaseHelper.DBHelperPurchase;
import com.test.projectv4.DatabaseHelper.DBHelperSeenPrize;
import com.test.projectv4.Model.PurchaseModel;
import com.test.projectv4.R;
import com.test.projectv4.Validation.InputValidation;

import java.util.ArrayList;

public class AddLotteryToPurchaseActivity extends AppCompatActivity {

    private TextView tvPurchasePrice;
    private TextView tvPurchaseSelectedDate;
    private Spinner spPurchaseSelectDate;
    private TextInputLayout tilPurchaseAddLottery;
    private TextInputLayout tilPurchaseAddAmountLottery;
    private TextInputEditText etPurchaseAddLottery;
    private TextInputEditText etPurchaseAddAmountLottery;
    private Button btPurchaseSave;

    private LinearLayout modepurchase;

    private final int PRICE_LOTTERY = 80;

    private String[] myString;
    private String Selecteditem;

    private ArrayList<PurchaseModel> models;
    private CustomListViewPurchase customListView;

    private int ID = -1;

    private DBHelperPurchase purchaseHelper;
    private DBHelperSeenPrize seenPrizeHelper;

    private InputValidation inputValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lottery_to_purchase);

        /**
         *
         * Initialize
         *
         */

        tvPurchasePrice = (TextView) findViewById(R.id.textViewPriceLotteryPurchase);
        tvPurchaseSelectedDate = (TextView) findViewById(R.id.textViewSelectedDateLotteryPurchase);
        spPurchaseSelectDate = (Spinner) findViewById(R.id.spinnerSelectDatePurchase);
        tilPurchaseAddAmountLottery = (TextInputLayout) findViewById(R.id.textInputLayoutAddAmountLotteryPurchase);
        tilPurchaseAddLottery = (TextInputLayout) findViewById(R.id.textInputLayoutAddLotteryNumberPurchase);
        etPurchaseAddAmountLottery = (TextInputEditText) findViewById(R.id.editTextAddAmountLotteryPurchase);
        etPurchaseAddLottery = (TextInputEditText) findViewById(R.id.editTextAddLotteryNumberPurchase);
        btPurchaseSave = (Button) findViewById(R.id.buttonSavePurchase);

        modepurchase = (LinearLayout) findViewById(R.id.modepurchase);

        purchaseHelper = new DBHelperPurchase(this);
        seenPrizeHelper = new DBHelperSeenPrize(this);

        inputValidation = new InputValidation(this);

        /**
         *
         * Spinner
         *
         */
        ArrayAdapter adapter;
        Resources res = getResources();
        myString = res.getStringArray(R.array.lotteries);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, myString);
        spPurchaseSelectDate.setAdapter(adapter);
        tvPurchasePrice.setText(String.valueOf(PRICE_LOTTERY));

        spPurchaseSelectDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Selecteditem = spPurchaseSelectDate.getSelectedItem().toString();
                tvPurchaseSelectedDate.setText(Selecteditem);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                
            }
        });

        /**
         *
         * Button Save
         *
         */

        btPurchaseSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Fields is empty!
                if (!inputValidation.isInputEditTextLottery(etPurchaseAddAmountLottery,
                        tilPurchaseAddAmountLottery, getString(R.string.input_every_fields))) {
                    return;
                }
                if(!inputValidation.isInputEditTextLottery(etPurchaseAddLottery,
                        tilPurchaseAddLottery, getString(R.string.input_every_fields))){
                    return;
                }


                if(etPurchaseAddLottery.getText().toString().length() == 6){
                    //Input Complete
                    if(seenPrizeHelper.lotteryIsEmpty(spPurchaseSelectDate.getSelectedItem().toString().trim())){

                        if(seenPrizeHelper.checkLottery(spPurchaseSelectDate.getSelectedItem().toString().trim(),
                                etPurchaseAddLottery.getText().toString().trim())) {
                            Toast.makeText(AddLotteryToPurchaseActivity.this, R.string.save_complete, Toast.LENGTH_SHORT).show();


                            //Save to database
                            purchaseHelper.addLottery(new PurchaseModel(ID,
                                    spPurchaseSelectDate.getSelectedItem().toString(),
                                    etPurchaseAddLottery.getText().toString().trim(),
                                    etPurchaseAddAmountLottery.getText().toString(),
                                    String.valueOf(Integer.parseInt(etPurchaseAddAmountLottery.getText().toString())*PRICE_LOTTERY),
                                    getString(R.string.win_lotto)));
                            clear();
                            Intent intent = new Intent(AddLotteryToPurchaseActivity.this, ModePurchaseActivity.class);
                            startActivity(intent);
                        } else {

                            Toast.makeText(AddLotteryToPurchaseActivity.this, R.string.save_complete, Toast.LENGTH_SHORT).show();


                            //Save to database
                            purchaseHelper.addLottery(new PurchaseModel(ID,
                                    spPurchaseSelectDate.getSelectedItem().toString(),
                                    etPurchaseAddLottery.getText().toString().trim(),
                                    etPurchaseAddAmountLottery.getText().toString(),
                                    String.valueOf(Integer.parseInt(etPurchaseAddAmountLottery.getText().toString())*PRICE_LOTTERY),
                                    getString(R.string.lose_lotto)));
                            clear();
                            Intent intent = new Intent(AddLotteryToPurchaseActivity.this, ModePurchaseActivity.class);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(AddLotteryToPurchaseActivity.this, R.string.save_complete, Toast.LENGTH_SHORT).show();

                        //Save to database
                        purchaseHelper.addLottery(new PurchaseModel(ID,
                                spPurchaseSelectDate.getSelectedItem().toString(),
                                etPurchaseAddLottery.getText().toString().trim(),
                                etPurchaseAddAmountLottery.getText().toString(),
                                String.valueOf(Integer.parseInt(etPurchaseAddAmountLottery.getText().toString())*PRICE_LOTTERY),
                                getString(R.string.wait_lotto)));
                        clear();
                        Intent intent = new Intent(AddLotteryToPurchaseActivity.this, ModePurchaseActivity.class);
                        startActivity(intent);
                    }
                } else {
                    //Snackbar.make(modepurchase, getString(R.string.error_message_lenght_lottery), Snackbar.LENGTH_LONG).show();
                    Toast.makeText(AddLotteryToPurchaseActivity.this, R.string.error_message_lenght_lottery, Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    private void clear(){
        etPurchaseAddAmountLottery.setText(null);
        etPurchaseAddLottery.setText(null);
    }
}
