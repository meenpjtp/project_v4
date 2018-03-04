package com.test.projectv4.Model;

import android.provider.BaseColumns;

/**
 *
 * งวดวันที่ xx เดือน xxxx ปี xxxx
 * 123456
 * ถูก/ไม่ถูก
 *
 */

public class CheckLotteryHistory {
    private int id;
    private String date;
    private String lotteryNumber;
    private String result;

    public CheckLotteryHistory(int id, String date, String lotteryNumber, String result) {
        this.id = id;
        this.date = date;
        this.lotteryNumber = lotteryNumber;
        this.result = result;
    }

    public class Column{
        public static final String ID = BaseColumns._ID;
        public static final String LOTTERY_DATE = "date";
        public static final String LOTTERY_NUMBER = "lotteryNumber";
        public static final String LOTTER_RESULT = "result";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLotteryNumber() {
        return lotteryNumber;
    }

    public void setLotteryNumber(String lotteryNumber) {
        this.lotteryNumber = lotteryNumber;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
