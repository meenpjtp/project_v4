package com.test.projectv4.ModePurchase;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.test.projectv4.CustomListView.CustomListViewPurchase;
import com.test.projectv4.DatabaseHelper.DBHelperPurchase;
import com.test.projectv4.Model.PurchaseModel;
import com.test.projectv4.R;

import java.util.ArrayList;

public class ModePurchaseActivity extends AppCompatActivity {

    private FloatingActionButton mFloatingAddlotteryPurchase;

    private DBHelperPurchase purchaseHelper;
    private ArrayList<PurchaseModel> models;
    private CustomListViewPurchase customListView;

    private SwipeMenuListView mSwipPurchase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_purchase);

        mSwipPurchase = (SwipeMenuListView) findViewById(R.id.purchaseListView);
        mFloatingAddlotteryPurchase = (FloatingActionButton) findViewById(R.id.fabAddlotteryPurchase);

        purchaseHelper = new DBHelperPurchase(this);
        querySQL();

        /**
         *
         *  Floating Action Button
         *
         */
        mFloatingAddlotteryPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addLotteryPurchase = new Intent(ModePurchaseActivity.this, AddLotteryToPurchaseActivity.class);
                startActivity(addLotteryPurchase);
            }
        });

        /**
         *
         *  ListView
         *
         */
        customListView = new CustomListViewPurchase(this, 0, models);
        mSwipPurchase.setAdapter(customListView);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                /*SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(170);
                // set item title
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);*/

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(150);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

        mSwipPurchase.setMenuCreator(creator);

        mSwipPurchase.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index){
                    case 0:
                        int count = 0;
                        for(PurchaseModel model:purchaseHelper.getAllLotteryPurchase()){
                            if(count == position){
                                models.remove(position);
                                purchaseHelper.deleteLottery(model.getId());
                            }
                            count++;
                            Toast.makeText(getApplication(), R.string.deleted, Toast.LENGTH_SHORT).show();
                            querySQL();
                        }
                        break;
                }
                return false;
            }
        });
    }

    private void querySQL(){
        models = purchaseHelper.getAllLotteryPurchase();
        customListView = new CustomListViewPurchase(this, 0, models);
        mSwipPurchase.setAdapter(customListView);
    }

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_stat,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_stat:
                Intent a = new Intent(this, PurchaseStaticActivity.class);
                startActivity(a);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
