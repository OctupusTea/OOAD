package com.github.OctupusTea.Accounting.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.github.OctupusTea.Accounting.Data.Record;

public class DBFunction {
    private SQLiteDatabase db;

    public DBFunction(Context context) {
        db = AccountingDBHelper.getDatabase(context);
    }

    public SQLiteDatabase getDatabase() {
        return db;
    }

    public long insert(Record record) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AccountingDBHelper.FIELD_ACCOUNT, record.getAccountName());
        contentValues.put(AccountingDBHelper.FIELD_CATEGORY, record.getCategoryName());
        contentValues.put(AccountingDBHelper.FIELD_DATE, record.getDate());
        contentValues.put(AccountingDBHelper.FIELD_CURRENCY_TYPE, record.getCurrencyType());
        contentValues.put(AccountingDBHelper.FIELD_COST, record.getCost());
        return db.insert(AccountingDBHelper.TABLE_NAME, null, contentValues);
    }

    public boolean update(Record record) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(AccountingDBHelper.FIELD_ACCOUNT, record.getAccountName());
        contentValues.put(AccountingDBHelper.FIELD_CATEGORY, record.getCategoryName());
        contentValues.put(AccountingDBHelper.FIELD_DATE, record.getDate());
        contentValues.put(AccountingDBHelper.FIELD_CURRENCY_TYPE, record.getCurrencyType());
        contentValues.put(AccountingDBHelper.FIELD_COST, record.getCost());

        String where = AccountingDBHelper.KEY_ID + "=" + Integer.toString(record.getId());

        return db.update(AccountingDBHelper.TABLE_NAME, contentValues, where, null) > 0;
    }

    public boolean delete(Record record) {
        String where = AccountingDBHelper.KEY_ID + "=" + Integer.toString(record.getId());
        return db.delete(AccountingDBHelper.TABLE_NAME, where, null) > 0;
    }

    public Record getById(int id) {
            Record record = new Record();
            String where = AccountingDBHelper.KEY_ID + "=" + id;
            Cursor result = db.query(AccountingDBHelper.TABLE_NAME, null, where, null, null, null, null, null);
            if (result.moveToNext()) {
                record = getRecord(result);
            }
            result.close();
            return record;
    }

    public Record getByAccount(String accountName) {
        Record record = new Record();
        String where = String.format(AccountingDBHelper.FIELD_ACCOUNT + "=\'%s\'", accountName);
        Cursor result = db.query(AccountingDBHelper.TABLE_NAME, null, where, null, null, null, null, null);
        //Cursor result = db.query(AccountingDBHelper.TABLE_NAME, null, where, new String[] {accountName}, null, null, null, null);
        if (result.moveToNext()) {
            record = getRecord(result);
        }
        result.close();
        return record;
    }

    public Record getByCategory(String category) {
        Record record = new Record();
        String where = AccountingDBHelper.FIELD_CATEGORY + "=" + category;
        Cursor result = db.query(AccountingDBHelper.TABLE_NAME, null, where, null, null, null, null, null);
        if (result.moveToNext()) {
            record = getRecord(result);
        }
        result.close();
        return record;
    }

    public Record getByDate(String dateStart, String dateEnd) {
        Record record = new Record();
        String where = String.format(AccountingDBHelper.FIELD_DATE + ">=\'%s\' AND " + AccountingDBHelper.FIELD_DATE + "<=\'%s\'", dateStart, dateEnd);
        Cursor result = db.query(AccountingDBHelper.TABLE_NAME, null, where, null, null, null, null, null);
        if (result.moveToNext()) {
            record = getRecord((result));
        }
        result.close();
        return record;
    }

    public Record getRecord(Cursor cursor) {
        Record record = new Record();
        record.setId(cursor.getInt(0));
        record.setAccountName(cursor.getString(1));
        record.setCategoryName(cursor.getString(2));
        record.setDate(cursor.getString(3));
        record.setCurrencyType(cursor.getString(4));
        record.setCost(cursor.getFloat(5));
        return record;
    }

}
