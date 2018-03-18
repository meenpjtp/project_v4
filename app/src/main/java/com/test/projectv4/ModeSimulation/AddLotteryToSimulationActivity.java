package com.test.projectv4.ModeSimulation;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.test.projectv4.DatabaseHelper.DBHelperSeenPrize;
import com.test.projectv4.DatabaseHelper.DBHelperSimulation;
import com.test.projectv4.Model.SimulationModel;
import com.test.projectv4.R;
import com.test.projectv4.Validation.InputValidation;
import java.util.ArrayList;


public class AddLotteryToSimulationActivity extends AppCompatActivity {

    private TextView mTextViewLotteryPrice;
    private TextView mTextViewSelectedDateLottery;
    private Spinner mSpinnerSelectDate;
    private Button mButtonSave;

    private TextInputEditText mEditTextAddAmountLottery, mEditTextAddLotteryNumber;
    private TextInputLayout textInputLayoutAddLotteryNumber,textInputLayoutAddAmountLottery;

    private InputValidation inputValidation;


    private final int PRICE_LOTTERY = 80;

    private String[] myString;
    private String Selecteditem;

    private ArrayList<SimulationModel> models;

    private int ID = -1;

    //Database
    private DBHelperSimulation simulationHelper;
    private DBHelperSeenPrize seenPrizeHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lottery_to_simulation);

        /**
         *
         * Initialize
         *
         */
        mTextViewLotteryPrice = (TextView) findViewById (R.id.textViewPriceLottery);
        mTextViewSelectedDateLottery = (TextView) findViewById (R.id.textViewSelectedDateLottery);
        mSpinnerSelectDate = (Spinner) findViewById (R.id.spinnerSelectDate);

        mEditTextAddLotteryNumber = (TextInputEditText) findViewById (R.id.editTextAddLotteryNumber);
        mEditTextAddAmountLottery = (TextInputEditText) findViewById (R.id.editTextAddAmountLottery);
        textInputLayoutAddAmountLottery = (TextInputLayout) findViewById(R.id.textInputLayoutAddAmountLottery);
        textInputLayoutAddLotteryNumber = (TextInputLayout) findViewById(R.id.textInputLayoutAddLotteryNumber);
        mButtonSave = (Button) findViewById (R.id.buttonSave);

        //Initialize Database
        simulationHelper = new DBHelperSimulation(this);
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
        mSpinnerSelectDate.setAdapter(adapter);
        mTextViewLotteryPrice.setText(String.valueOf(PRICE_LOTTERY));

        mSpinnerSelectDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Selecteditem = mSpinnerSelectDate.getSelectedItem().toString();
                mTextViewSelectedDateLottery.setText(Selecteditem);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        /**
         *
         * Button save
         *
         */
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //If fields is empty.
                //Notification Error when empty fields.

                if(!inputValidation.isInputEditTextLottery(mEditTextAddLotteryNumber, textInputLayoutAddLotteryNumber, getString(R.string.input_every_fields))){
                    return;
                }

                if(!inputValidation.isInputEditTextLottery(mEditTextAddAmountLottery, textInputLayoutAddAmountLottery, getString(R.string.input_every_fields))){
                    return;
                }


                /**
                 * Date
                 * Number
                 * Amount
                 * Paid
                 * Status
                 */
                // Complete every fields save to database
                // Check date before and then check date & lottery number.

                // if lottery date is not empty, this condition is working
                // Status win lottery

                if(mEditTextAddLotteryNumber.getText().toString().length() == 6){
                    if(seenPrizeHelper.lotteryIsEmpty(mSpinnerSelectDate.getSelectedItem().toString().trim())){

                        if(seenPrizeHelper.checkLottery(mSpinnerSelectDate.getSelectedItem().toString().trim(),
                                mEditTextAddLotteryNumber.getText().toString().trim())) {
                            Toast.makeText(AddLotteryToSimulationActivity.this, R.string.save_complete, Toast.LENGTH_SHORT).show();

                            //Save to database
                            simulationHelper.addLottery(new SimulationModel(ID,
                                    mSpinnerSelectDate.getSelectedItem().toString(),
                                    mEditTextAddLotteryNumber.getText().toString().trim(),
                                    mEditTextAddAmountLottery.getText().toString(),
                                    String.valueOf(Integer.parseInt(mEditTextAddAmountLottery.getText().toString())*PRICE_LOTTERY),
                                    getString(R.string.win_lotto)));
                            clear();
                            Intent intent = new Intent(AddLotteryToSimulationActivity.this, ModeSimulationActivity.class);
                            startActivity(intent);
                        } else {

                            Toast.makeText(AddLotteryToSimulationActivity.this, R.string.save_complete, Toast.LENGTH_SHORT).show();

                            //Save to database
                            simulationHelper.addLottery(new SimulationModel(ID,
                                    mSpinnerSelectDate.getSelectedItem().toString(),
                                    mEditTextAddLotteryNumber.getText().toString().trim(),
                                    mEditTextAddAmountLottery.getText().toString(),
                                    String.valueOf(Integer.parseInt(mEditTextAddAmountLottery.getText().toString())*PRICE_LOTTERY),
                                    getString(R.string.lose_lotto)));
                            clear();
                            Intent intent = new Intent(AddLotteryToSimulationActivity.this, ModeSimulationActivity.class);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(AddLotteryToSimulationActivity.this, R.string.save_complete, Toast.LENGTH_SHORT).show();

                        //Save to database
                        simulationHelper.addLottery(new SimulationModel(ID,
                                mSpinnerSelectDate.getSelectedItem().toString(),
                                mEditTextAddLotteryNumber.getText().toString().trim(),
                                mEditTextAddAmountLottery.getText().toString(),
                                String.valueOf(Integer.parseInt(mEditTextAddAmountLottery.getText().toString())*PRICE_LOTTERY),
                                getString(R.string.wait_lotto)));
                        clear();
                        Intent intent = new Intent(AddLotteryToSimulationActivity.this, ModeSimulationActivity.class);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(AddLotteryToSimulationActivity.this, R.string.error_message_lenght_lottery, Toast.LENGTH_LONG).show();

                }

            }
        });

    }


    private void clear(){
        mEditTextAddAmountLottery.setText(null);
        mEditTextAddLotteryNumber.setText(null);
    }
}
