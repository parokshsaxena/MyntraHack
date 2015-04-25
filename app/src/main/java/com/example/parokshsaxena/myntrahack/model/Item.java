package com.example.parokshsaxena.myntrahack.model;

/**
 * Created by paroksh.saxena on 25/04/15.
 */
public class Item {

    String name;

    String phoneNum;

    public Item(String name, String phoneNum) {
        this.name = name;
        this.phoneNum = phoneNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
