package com.test.projectv4.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.test.projectv4.Model.SimulationModel;

import java.util.ArrayList;
import java.util.List;


/**
 *
 *  - Date : 1/12/2560
 *  - Lottery Number : 123456
 *  - Amount : 10
 *  - Price x Amount : 80x10 = 800
 *  - Status : wait/win/lose
 *
 */

public class DBHelperSimulation extends SQLiteOpenHelper{

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ModeSimulation.db";

    // Table Name
    private static final String TABLE_NAME = "simulation";

    //Column Names
    private static final String COLS_ID = "id";
    private static final String COLS_LOTTERY_DATE = "lottery_date";
    private static final String COLS_LOTTERY_NUMBER = "lottery_number";
    private static final String COLS_AMOUNT = "lottery_amount";
    private static final String COLS_PAID = "lottery_paid";
    private static final String COLS_STATUS = "lottery_status";

    private SQLiteDatabase sqLiteDatabase;

    //Create
    private String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLS_LOTTERY_DATE + " TEXT,"
            + COLS_LOTTERY_NUMBER + " TEXT," + COLS_AMOUNT + " INTEGER,"
            + COLS_PAID + " INTEGER," + COLS_STATUS + " TEXT" + ")";

    private String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DBHelperSimulation(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void addLottery(SimulationModel lottery){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLS_LOTTERY_DATE, lottery.getLottery_date());
        values.put(COLS_LOTTERY_NUMBER, lottery.getLottery_number());
        values.put(COLS_AMOUNT, lottery.getLottery_amount());
        values.put(COLS_PAID, lottery.getLottery_paid());
        values.put(COLS_STATUS, lottery.getLottery_status());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<SimulationModel> getAllLotterySimulation(){

        String QUERY_ALL_LOTTERY = "SELECT * FROM " + TABLE_NAME;
        ArrayList<SimulationModel> lotteries = new ArrayList<>();
        sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery(QUERY_ALL_LOTTERY,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            SimulationModel lottery = new SimulationModel(
                    cursor.getInt(0), cursor.getString(1),
                    cursor.getString(2), cursor.getInt(3),
                    cursor.getInt(4),cursor.getString(5)
            );
            lotteries.add(lottery);
            cursor.moveToNext();
        }
        sqLiteDatabase.close();
        return lotteries;
    }

    public void deleteLottery(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, COLS_ID + " = " + id,null);
        sqLiteDatabase.close();
    }
}
