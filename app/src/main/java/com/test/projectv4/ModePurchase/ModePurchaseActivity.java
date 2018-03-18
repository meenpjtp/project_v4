package com.test.projectv4.ModePurchase;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.test.projectv4.DatabaseHelper.DBHelperPurchase;
import com.test.projectv4.Model.PurchaseModel;
import com.test.projectv4.R;
import com.test.projectv4.RecyclerView.Adapter.PurchaseAdapter;
import com.test.projectv4.RecyclerView.Helper.PurchaseHelper;

import java.util.ArrayList;

public class ModePurchaseActivity extends AppCompatActivity implements PurchaseHelper.PurchaseHelperListener{

    private FloatingActionButton mFloatingAddlotteryPurchase;

    private DBHelperPurchase purchaseHelper;

    private RecyclerView recyclerPurchase;
    private static final String TAG = ModePurchaseActivity.class.getSimpleName();
    private ArrayList<PurchaseModel> purchaseList;
    private PurchaseAdapter mAdapter;

    private RelativeLayout modePurchase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_purchase);

        mFloatingAddlotteryPurchase = (FloatingActionButton) findViewById(R.id.fabAddlotteryPurchase);
        recyclerPurchase = (RecyclerView) findViewById(R.id.recyclerPurchase);
        modePurchase = (RelativeLayout) findViewById(R.id.modePurchase);

        purchaseHelper = new DBHelperPurchase(this);
        purchaseList = new ArrayList<>();
        mAdapter = new PurchaseAdapter(this, purchaseList);
        getDataFromSQLite();


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
         * Recycler view
         *
         */
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerPurchase.setLayoutManager(mLayoutManager);
        recyclerPurchase.setItemAnimator(new DefaultItemAnimator());
        recyclerPurchase.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerPurchase.setAdapter(mAdapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new PurchaseHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerPurchase);
    }

    /**
     *
     * Recycler View
     *
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof PurchaseAdapter.MyViewHolder) {
            // get the removed item name to display it in snack bar
            String date = purchaseList.get(viewHolder.getAdapterPosition()).getLottery_date();

            // backup of removed item for undo purpose
            final PurchaseModel deletedItem = purchaseList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            mAdapter.removeItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make(modePurchase, date + " ถูกลบแล้ว!", Snackbar.LENGTH_LONG);
            snackbar.setAction("ยกเลิก", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    mAdapter.restoreItem(deletedItem, deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }

    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                purchaseList.clear();
                purchaseList.addAll(purchaseHelper.getAllLotteryPurchase());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                mAdapter.notifyDataSetChanged();
            }
        }.execute();
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
