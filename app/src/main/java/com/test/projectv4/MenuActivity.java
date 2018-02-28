package com.test.projectv4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.test.projectv4.ModeSimulationPurchase.ModeSimulationPurchaseActivity;

public class MenuActivity extends AppCompatActivity {

    private Button btnMode1, btnMode2, btnStatic1, btnStatic2, btnCheckLotto1, btnCheckLotto2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnMode1 = (Button) findViewById(R.id.btnMode1);
        btnMode2 = (Button) findViewById(R.id.btnMode2);
        btnStatic1 = (Button) findViewById(R.id.btnStatic1);
        btnStatic2 = (Button) findViewById(R.id.btnStatic2);
        btnCheckLotto1= (Button) findViewById(R.id.btnCheckLotto1);


        btnCheckLotto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c1 = new Intent(MenuActivity.this, SeenPrizeActivity.class);
                startActivity(c1);
            }
        });


        btnMode1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m1 = new Intent(MenuActivity.this, ModeSimulationPurchaseActivity.class);
                startActivity(m1);
            }
        });

    }



}
