package com.test.projectv4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.test.projectv4.LotteryActivities.LotteryDec_16_2560;
import com.test.projectv4.LotteryActivities.LotteryDec_1_2560;
import com.test.projectv4.LotteryActivities.LotteryDec_30_2560;

public class CheckLottery1Activity extends AppCompatActivity {

    private Spinner spinner;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_lottery1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CheckLottery1Activity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.lotteries));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position){
                    case 0:
                        Intent i0 = new Intent(CheckLottery1Activity.this, LotteryDec_30_2560.class);
                        startActivity(i0);
                        break;

                    case 1:
                        Intent i1 = new Intent(CheckLottery1Activity.this , LotteryDec_16_2560.class);
                        startActivity(i1);
                        break;

                    case 2:
                        Intent i2 = new Intent(CheckLottery1Activity.this , LotteryDec_1_2560.class);
                        startActivity(i2);
                        break;
                }
            }
        });
        listView.setAdapter(adapter);



    }

}
