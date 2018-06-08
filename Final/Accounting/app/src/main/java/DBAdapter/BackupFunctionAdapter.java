package com.example.myfirstapp.DBAdapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myfirstapp.Backup.BackupFunction;
import com.example.myfirstapp.Data.Record;
import com.example.myfirstapp.SQLite.AccountingDBHelper;
import com.example.myfirstapp.SQLite.DBFunction;

import java.util.ArrayList;
import java.util.List;

public class BackupFunctionAdapter extends BackupFunction {

    private DBFunction dbFunction;
    private SQLiteDatabase db;

    public BackupFunctionAdapter(Context context) {
        dbFunction = new DBFunction(context);
        db = dbFunction.getDatabase();
    }

    @Override
    public List<Record> getAllRecord() {
        List<Record> records = new ArrayList<>();
        Cursor cursor = db.query(AccountingDBHelper.TABLE_NAME, null, null, null, null, null, null, null);
        while (cursor.moveToNext()){
            records.add(dbFunction.getRecord(cursor));
        }
        cursor.close();
        return records;
    }
}
