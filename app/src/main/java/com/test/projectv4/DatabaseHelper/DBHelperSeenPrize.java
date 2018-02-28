package com.test.projectv4.DatabaseHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DBHelperSeenPrize extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/com.test.projectv4/databases/";
    private static String DB_NAME = "lotteries.sqlite";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public DBHelperSeenPrize(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    /*Create new empty database in your system and rewrite it with your database from SqlLite.*/
    public void createDataBase() throws IOException{

        boolean dbExist = checkDataBase();

        if(dbExist)
        {
            //do nothing - database already exist
        }
        else{
            //After calling this method we can use system's empty database path to overwrite our on database into it.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }


    public String[] ReadFromDB(String Selecteditem) {

        // Retrieve a string array of all our Data
        ArrayList temp_array = new ArrayList();
        String[] notes_array = new String[0];

        String sqlQuery = "SELECT * FROM Lottery where lottery_date = '"+ Selecteditem +"'"  ;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(sqlQuery, null);

        if (c.moveToFirst()){
            do{
                temp_array.add(  c.getInt(c.getColumnIndex("id")) +
                        "," + c.getString(c.getColumnIndex("lottery_num")) +
                        "," + c.getString(c.getColumnIndex("description"))
                );
            }while(c.moveToNext());
        }
        c.close();
        notes_array = (String[]) temp_array.toArray(notes_array);
        return notes_array;
    }


    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;
        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){

        }

        if(checkDB != null){

            checkDB.close();
        }

        return checkDB != null ? true : false;
    }



    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transferring byte-stream.
     * */

    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void openDataBase() throws java.sql.SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {

        if(myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}