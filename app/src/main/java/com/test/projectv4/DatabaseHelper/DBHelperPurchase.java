package com.test.projectv4.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.test.projectv4.Model.PurchaseModel;

import java.util.ArrayList;

public class DBHelperPurchase extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ModePurchase.db";
    private static final String TABLE_NAME = "purchase";
    private SQLiteDatabase sqLiteDatabase;

    private String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DBHelperPurchase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TABLE = String.format("CREATE TABLE %s " + "(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                TABLE_NAME, PurchaseModel.Column.ID,
                PurchaseModel.Column.LOTTERY_DATE, PurchaseModel.Column.LOTTERY_NUMBER,
                PurchaseModel.Column.LOTTERY_AMOUNT, PurchaseModel.Column.LOTTERY_PAID,
                PurchaseModel.Column.LOTTERY_STATUS);

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void addLottery(PurchaseModel lottery){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PurchaseModel.Column.LOTTERY_DATE, lottery.getLottery_date());
        values.put(PurchaseModel.Column.LOTTERY_NUMBER, lottery.getLottery_number());
        values.put(PurchaseModel.Column.LOTTERY_AMOUNT, lottery.getLottery_amount());
        values.put(PurchaseModel.Column.LOTTERY_PAID, lottery.getLottery_paid());
        values.put(PurchaseModel.Column.LOTTERY_STATUS, lottery.getLottery_status());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<PurchaseModel> getAllLotteryPurchase(){

        String QUERY_ALL_LOTTERY = "SELECT * FROM " + TABLE_NAME;
        ArrayList<PurchaseModel> lotteries = new ArrayList<>();
        sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery(QUERY_ALL_LOTTERY,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            PurchaseModel lottery = new PurchaseModel(
                    cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3),
                    cursor.getString(4),cursor.getString(5)
            );
            lotteries.add(lottery);
            cursor.moveToNext();
        }
        sqLiteDatabase.close();
        return lotteries;
    }

    public void deleteLottery(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, PurchaseModel.Column.ID + " = " + id,null);
        sqLiteDatabase.close();
    }
}
