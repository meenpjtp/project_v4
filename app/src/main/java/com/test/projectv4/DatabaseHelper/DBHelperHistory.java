package com.test.projectv4.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.test.projectv4.Model.CheckLotteryHistoryModel;

import java.util.ArrayList;

public class DBHelperHistory extends SQLiteOpenHelper {

    private SQLiteDatabase sqLiteDatabase;
    public static final String DATABASE_NAME = "history.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE = "history";

    public DBHelperHistory(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = String.format("CREATE TABLE %s " + "(%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT)",
                TABLE, CheckLotteryHistoryModel.Column.ID, CheckLotteryHistoryModel.Column.LOTTERY_DATE,
                CheckLotteryHistoryModel.Column.LOTTERY_NUMBER, CheckLotteryHistoryModel.Column.LOTTER_RESULT);
                sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE;
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void addLottery(CheckLotteryHistoryModel check){
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CheckLotteryHistoryModel.Column.LOTTERY_DATE, check.getDate());
        values.put(CheckLotteryHistoryModel.Column.LOTTERY_NUMBER, check.getLotteryNumber());
        values.put(CheckLotteryHistoryModel.Column.LOTTER_RESULT, check.getResult());
        sqLiteDatabase.insert(TABLE, null, values);
        sqLiteDatabase.close();
    }

    public ArrayList<CheckLotteryHistoryModel> getAllHistory(){
        String QUERY_ALL_HISTORY = "SELECT * FROM " + TABLE;
        ArrayList<CheckLotteryHistoryModel> checks = new ArrayList<>();
        sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(QUERY_ALL_HISTORY, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            CheckLotteryHistoryModel check = new CheckLotteryHistoryModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            checks.add(check);
            cursor.moveToNext();
        }
        sqLiteDatabase.close();
        return checks;
    }

    public void deleteHistory(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE, CheckLotteryHistoryModel.Column.ID +" = " + id,null);
        sqLiteDatabase.close();
    }
}
