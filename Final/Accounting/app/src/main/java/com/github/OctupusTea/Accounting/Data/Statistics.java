package com.github.OctupusTea.Accounting.Data;

public class Statistics {

    private String category;
    private Double sum;

    public Statistics(String category) {
        this.category = category;
        sum = 0d;
    }

    public String getCategory() {
        return category;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double cost) {
        sum += cost;
    }
}
