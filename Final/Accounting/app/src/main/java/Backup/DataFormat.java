package com.example.myfirstapp.Backup;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.example.myfirstapp.DBAdapter.BackupFunctionAdapter;
import com.example.myfirstapp.Data.Record;

import java.io.File;
import java.util.List;

public abstract class DataFormat {

    private BackupFunction backupFunction;
    protected List<Record> recordList;
    protected File backupFile;
    protected Context context;

    public DataFormat(Context context, String date) {
        this.context = context;
        backupFunction = new BackupFunctionAdapter(context);
        recordList = backupFunction.getAllRecord();
        backupFile = createBackupFile(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), date);
    }

    public File createBackupFile(File path, String date) {
            String backupFileName = "Accounting backup " + date + ".txt";
            File backupFile = new File(path, backupFileName);
            return backupFile;
    }

    public abstract void processBackup();

}
