package com.github.OctupusTea.Accounting.Statistics;

import com.github.OctupusTea.Accounting.Data.DatePart;
import com.github.OctupusTea.Accounting.Data.Record;
import com.github.OctupusTea.Accounting.Data.Statistics;

import java.util.ArrayList;
import java.util.List;

public abstract class StatisticsFunction {

    public abstract List<Record> getDataByYear(String account, String year);
    public abstract List<Record> getDataByMonth(String account, String year, String month);
    public abstract List<Record> getDataByDay(String account, String year, String month, String day);

    public List<Statistics> getSumOfEachCategory(String account, String dateType, DatePart datePart) {

        List<Record> recordList = null;

        switch (dateType) {
            case "year":
                recordList = getDataByYear(account, datePart.getYear());
                break;
            case "month":
                recordList = getDataByMonth(account, datePart.getYear(), datePart.getMonth());
                break;
            case "day":
                recordList = getDataByDay(account, datePart.getYear(), datePart.getMonth(), datePart.getDay());
                break;
        }

        List<String> categoryList = new ArrayList<String>();
        for(Record record : recordList) {
            if (!categoryList.contains(record.getCategoryName())) {
                categoryList.add(record.getCategoryName());
            }
        }

        List<Statistics> statisticsList = new ArrayList<Statistics>();
        Statistics statistics = null;
        for(String category : categoryList) {
            statistics = new Statistics(category);
            for(Record record : recordList) {
               if (category.equals(record.getCategoryName())){
                    statistics.setSum(record.getCost());
               }
            }
        }

        return statisticsList;
    }
}
