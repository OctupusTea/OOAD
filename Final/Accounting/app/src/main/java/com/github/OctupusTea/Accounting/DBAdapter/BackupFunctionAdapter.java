package com.github.OctupusTea.Accounting.DBAdapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.github.OctupusTea.Accounting.Backup.BackupFunction;
import com.github.OctupusTea.Accounting.Data.Record;
import com.github.OctupusTea.Accounting.SQLite.AccountingDBHelper;
import com.github.OctupusTea.Accounting.SQLite.DBFunction;

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
