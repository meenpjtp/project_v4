package com.test.projectv4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button btnMode1, btnMode2, btnStatic1, btnStatic2, btnCheckLotto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnMode1 = (Button) findViewById(R.id.btnMode1);
        btnMode2 = (Button) findViewById(R.id.btnMode2);
        btnStatic1 = (Button) findViewById(R.id.btnStatic1);
        btnStatic2 = (Button) findViewById(R.id.btnStatic2);
        btnCheckLotto= (Button) findViewById(R.id.btnCheckLotto);

        btnCheckLotto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, CheckLottery1Activity.class);
                startActivity(intent);
            }
        });

    }
}
