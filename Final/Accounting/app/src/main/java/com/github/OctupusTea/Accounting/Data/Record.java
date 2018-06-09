package com.github.OctupusTea.Accounting.Record;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Record {
    private int id;
    private String accountName;
    private String categorayName;
    private String date;
    private String currencyType;
    private double cost;

    public Record() {
        setDate();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCategorayName() {
        return categorayName;
    }

    public void setCategorayName(String categorayName) {
        this.categorayName = categorayName;
    }

    private void setDate() {
        Date date = new Date();
        SimpleDateFormat timeFormating = new SimpleDateFormat("yyyy-MM-dd");
        this.date = timeFormating.format(date);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

}
