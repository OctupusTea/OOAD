package com.example.myfirstapp.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AccountingDBHelper extends SQLiteOpenHelper {

    public static AccountingDBHelper getDBHelper(Context context) {
        if (DBHelper == null) {
            DBHelper = new AccountingDBHelper(context, "Testing4.db", null, 1);
        }
        return DBHelper;
    }

    public static SQLiteDatabase getDatabase(Context context) {
            return getDBHelper(context).getWritableDatabase();
    }

    private static AccountingDBHelper DBHelper = null;

    public static String TABLE_NAME = "record";
    public static String KEY_ID = "_id";
    public static String FIELD_ACCOUNT = "accountName";
    public static String FIELD_CATEGORY = "categoryName";
    public static String FIELD_DATE = "date";
    public static String FIELD_CURRENCY_TYPE = "currencyType";
    public static String FIELD_COST = "cost";

    public AccountingDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FIELD_ACCOUNT + " TEXT NOT NULL, " + FIELD_CATEGORY + " TEXT NOT NULL, " + FIELD_DATE +
                " TEXT NOT NULL, " + FIELD_CURRENCY_TYPE + " TEXT NOT NULL, " + FIELD_COST + " FLOAT NOT NULL)";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
