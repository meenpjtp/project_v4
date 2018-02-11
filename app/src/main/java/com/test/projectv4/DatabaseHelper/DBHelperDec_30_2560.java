package com.test.projectv4.DatabaseHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.test.projectv4.Model.LotteryModel;

import java.util.ArrayList;
import java.util.List;

public class DBHelperDec_30_2560 extends SQLiteOpenHelper{

    public static final String DBNAME = "30_12_60.sqlite";
    public static final String DBLOCATION = "/data/data/com.test.projectv4/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DBHelperDec_30_2560(Context context){
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void openDatabase(){
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if(mDatabase != null && mDatabase.isOpen()){
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase(){
        if(mDatabase != null){
            mDatabase.close();
        }
    }

    public List<LotteryModel> getListLottery(){
        LotteryModel lotteryModel = null;
        List<LotteryModel> lotteryModelList = new ArrayList<>();
        openDatabase();

        Cursor cursor = mDatabase.rawQuery("SELECT * FROM LOTTERY", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            lotteryModel = new LotteryModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            lotteryModelList.add(lotteryModel);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return lotteryModelList;
    }
}
