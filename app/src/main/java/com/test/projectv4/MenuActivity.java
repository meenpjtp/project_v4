package com.test.projectv4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.test.projectv4.ModePurchase.ModePurchaseActivity;
import com.test.projectv4.ModeSimulation.ModeSimulationActivity;

public class MenuActivity extends AppCompatActivity {

    private Button btnModeSimulation, btnModePurchase, btnSeenPrize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnModeSimulation = (Button) findViewById(R.id.btnModeSimulation);
        btnModePurchase = (Button) findViewById(R.id.btnModePurchase);
        btnSeenPrize = (Button) findViewById(R.id.btnSeenPrize);


        btnSeenPrize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c1 = new Intent(MenuActivity.this, SeenPrizeActivity.class);
                startActivity(c1);
            }
        });

        btnModePurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent p1 = new Intent(MenuActivity.this, ModePurchaseActivity.class);
                startActivity(p1);
            }
        });


        btnModeSimulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m1 = new Intent(MenuActivity.this, ModeSimulationActivity.class);
                startActivity(m1);
            }
        });

    }



}
