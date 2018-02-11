package com.test.projectv4.LotteryActivities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.test.projectv4.Adapter.ListAdapterDec_30_2560;
import com.test.projectv4.CheckLottery2Activity;
import com.test.projectv4.DatabaseHelper.DBHelperDec_30_2560;
import com.test.projectv4.Model.LotteryModel;
import com.test.projectv4.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class LotteryDec_30_2560 extends AppCompatActivity {

    private ListView listView;
    private ListAdapterDec_30_2560 adapter;
    private List<LotteryModel> lotteryModels;
    private DBHelperDec_30_2560 helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery_dec_30_2560);

        listView = (ListView)findViewById(R.id.listView);
        helper = new DBHelperDec_30_2560(this);

        File database = getApplicationContext().getDatabasePath(DBHelperDec_30_2560.DBNAME);
        if(false == database.exists()){
            helper.getReadableDatabase();
            if(copyDatabase(this)) {
                Toast.makeText(this, "Copy database succes", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        lotteryModels = helper.getListLottery();
        adapter = new ListAdapterDec_30_2560(this, lotteryModels);
        listView.setAdapter(adapter);
    }

    private boolean copyDatabase(Context context){
        try {

            InputStream inputStream = context.getAssets().open(helper.DBNAME);
            String outFileName = helper.DBLOCATION + helper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            //Log.w("MainActivity","DB copied");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.check,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btnCheck:
                Intent c = new Intent(this,CheckLottery2Activity.class);
                startActivity(c);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
