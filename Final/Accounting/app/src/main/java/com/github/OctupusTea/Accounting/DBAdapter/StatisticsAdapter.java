package com.github.OctupusTea.Accounting.DBAdapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.github.OctupusTea.Accounting.Data.Record;
import com.github.OctupusTea.Accounting.SQLite.AccountingDBHelper;
import com.github.OctupusTea.Accounting.SQLite.DBFunction;
import com.github.OctupusTea.Accounting.Statistics.StatisticsFunction;

import java.util.ArrayList;
import java.util.List;

public class StatisticsAdapter extends StatisticsFunction {

    private DBFunction dbFunction;
    private SQLiteDatabase db;

    public StatisticsAdapter(Context context) {
        dbFunction = new DBFunction(context);
        db = dbFunction.getDatabase();
    }


    @Override
    public List<Record> getDataByYear(String account, String year) {
        List<Record> records = new ArrayList<Record>();
        String where = String.format(AccountingDBHelper.FIELD_ACCOUNT + "=\'%s\' AND " + AccountingDBHelper.FIELD_DATE + ">=\'%s\' AND " + AccountingDBHelper.FIELD_DATE + "<=\'%s\'", account, year + "-01-01", year + "-12-31");
        Cursor cursor = db.query(AccountingDBHelper.TABLE_NAME, null, where, null, null, null, null, null);
        while (cursor.moveToNext()){
            records.add(dbFunction.getRecord(cursor));
        }
        cursor.close();
        return records;
    }

    @Override
    public List<Record> getDataByMonth(String account, String year, String month) {
        List<Record> records = new ArrayList<Record>();
        String where = String.format(AccountingDBHelper.FIELD_ACCOUNT + "=\'%s\' AND " + AccountingDBHelper.FIELD_DATE + ">=\'%s\' AND " + AccountingDBHelper.FIELD_DATE + "<=\'%s\'", account, year + "-" + month + "-01", year + "-" + month + "-31");
        Cursor cursor = db.query(AccountingDBHelper.TABLE_NAME, null, where, null, null, null, null, null);
        while (cursor.moveToNext()){
            records.add(dbFunction.getRecord(cursor));
        }
        cursor.close();
        return records;
    }

    @Override
    public List<Record> getDataByDay(String account, String year, String month, String day) {
        List<Record> records = new ArrayList<Record>();
        String where = String.format(AccountingDBHelper.FIELD_ACCOUNT + "=\'%s\' AND " + AccountingDBHelper.FIELD_DATE + ">=\'%s\' AND " + AccountingDBHelper.FIELD_DATE + "<=\'%s\'", account, year + "-" + month + "-" + day, year + "-" + month + "-" + day);
        Cursor cursor = db.query(AccountingDBHelper.TABLE_NAME, null, where, null, null, null, null, null);
        while (cursor.moveToNext()){
            records.add(dbFunction.getRecord(cursor));
        }
        cursor.close();
        return records;
    }
}
