package com.test.projectv4.ModeSimulation;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.test.projectv4.R;
import com.test.projectv4.Util.StringUtil;


public class AddLotteryToSimulationActivity extends AppCompatActivity {

    private TextView mTextViewLotteryPrice;
    private TextView mTextViewSelectedDateLottery;
    private Spinner mSpinnerSelectDate;
    private EditText mEditTextAddLotteryNumber;
    private EditText mEditTextAddAmountLottery;
    private Button mButtonSave;

    private final int PRICE_LOTTERY = 80;

    private String[] myString;
    private String Selecteditem;

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
        mEditTextAddLotteryNumber = (EditText) findViewById (R.id.editTextAddLotteryNumber);
        mEditTextAddAmountLottery = (EditText) findViewById (R.id.editTextAddAmountLottery);
        mButtonSave = (Button) findViewById (R.id.buttonSave);

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

                //Notification Error when empty fields.
                if(StringUtil.isEmpty(mEditTextAddLotteryNumber.getText().toString())){
                    mEditTextAddLotteryNumber.setError(getString(R.string.input_every_fields));
                }

                if(StringUtil.isEmpty(mEditTextAddAmountLottery.getText().toString())){
                    mEditTextAddAmountLottery.setError(getString(R.string.input_every_fields));
                }
            }
        });

    }
}
