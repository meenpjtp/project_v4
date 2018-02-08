package com.test.projectv4.Model;

/**
 * Created by Administrator1 on 26/1/2561.
 */

public class LotteryModel {

    private int ID;
    private String lottery_num;
    private String description;

    public LotteryModel(int ID, String lotto, String description) {
        this.ID = ID;
        this.lottery_num = lotto;
        this.description = description;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLottery_num() {
        return lottery_num;
    }

    public void setLottery_num(String lottery_num) {
        this.lottery_num = lottery_num;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
