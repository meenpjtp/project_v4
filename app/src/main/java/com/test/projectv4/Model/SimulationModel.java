package com.test.projectv4.Model;

/**
 *
 *  - 1
 *  - Date : 1/12/2560
 *  - Lottery Number : 123456
 *  - Amount : 10
 *  - Price x Amount : 80x10 = 800
 *  - Status : wait/win/lose
 *
 */

public class SimulationModel {

    private int id;
    private String lottery_date;
    private String lottery_number;
    private int lottery_amount;
    private int lottery_paid;
    private String lottery_status;

    public SimulationModel(int id, String lottery_date, String lottery_number, int lottery_amount, int lottery_paid, String lottery_status) {
        this.id = id;
        this.lottery_date = lottery_date;
        this.lottery_number = lottery_number;
        this.lottery_amount = lottery_amount;
        this.lottery_paid = lottery_paid;
        this.lottery_status = lottery_status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLottery_date() {
        return lottery_date;
    }

    public void setLottery_date(String lottery_date) {
        this.lottery_date = lottery_date;
    }

    public String getLottery_number() {
        return lottery_number;
    }

    public void setLottery_number(String lottery_number) {
        this.lottery_number = lottery_number;
    }

    public int getLottery_amount() {
        return lottery_amount;
    }

    public void setLottery_amount(int lottery_amount) {
        this.lottery_amount = lottery_amount;
    }

    public int getLottery_paid() {
        return lottery_paid;
    }

    public void setLottery_paid(int lottery_paid) {
        this.lottery_paid = lottery_paid;
    }

    public String getLottery_status() {
        return lottery_status;
    }

    public void setLottery_status(String lottery_status) {
        this.lottery_status = lottery_status;
    }
}
